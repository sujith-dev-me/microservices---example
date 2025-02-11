package com.sujith.department_service.config;

import com.sujith.department_service.client.EmpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig
{
    //Add a filter for load balancer so that web-client process data accordingly
    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient empWebClient()
    {
        return WebClient.builder()
                .baseUrl("http://employee-service")//Looks for service registry for any instance of  the service available.
                .filter(filterFunction)
                .build();
    }

    //Using this web client, tell the serve to connect to employee-service
    @Bean
    public EmpClient empClient()
    {
        WebClientAdapter adapter    =   WebClientAdapter.create(empWebClient());

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(adapter)
                .build();

        return httpServiceProxyFactory.createClient(EmpClient.class);
    }

}
