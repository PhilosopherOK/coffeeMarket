package ua.coffee.coffeemarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoffeeMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeMarketApplication.class, args);
    }

}
