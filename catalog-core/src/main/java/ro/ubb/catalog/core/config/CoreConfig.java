package ro.ubb.catalog.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan({"ro.ubb.catalog.core.service"})
public class CoreConfig {
    @Bean
    ExecutorService executor(){
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }
}
