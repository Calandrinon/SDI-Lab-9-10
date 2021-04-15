package ro.ubb.catalog.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.client.service.RecordService;
import ro.ubb.catalog.client.service.TransactionService;
import ro.ubb.catalog.client.service.UserService;
import ro.ubb.catalog.client.ui.AdminUI;
import ro.ubb.catalog.client.ui.ClientUI;
import ro.ubb.catalog.client.ui.UI;
import ro.ubb.catalog.core.config.CoreConfig;
import ro.ubb.catalog.core.config.JPAConfig;
import ro.ubb.catalog.web.controller.RecordController;
import ro.ubb.catalog.web.controller.TransactionController;
import ro.ubb.catalog.web.controller.UserController;

@Configuration
@Import({JPAConfig.class, CoreConfig.class})
@ComponentScan({"ro.ubb.catalog.client.ui", "ro.ubb.catalog.client.service"})
public class ClientConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    UI createUI() {
        RecordService recordService = recordService();
        UserService userService = userService();
        TransactionService transactionService = transactionService();
        AdminUI adminUI = new AdminUI(userService, recordService, transactionService);
        ClientUI clientUI = new ClientUI(userService, recordService, transactionService);
        return new UI(clientUI, adminUI);
    }

    @Bean
    RecordService recordService() {
        return new RecordService();
    }

    @Bean
    UserService userService() {
        return new UserService();
    }

    @Bean
    TransactionService transactionService() {
        return new TransactionService();
    }
}
