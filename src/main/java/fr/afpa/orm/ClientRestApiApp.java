package fr.afpa.orm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class ClientRestApiApp {
    public static void main(String[] args) {
        SpringApplication.run(fr.afpa.orm.AccountRestApiApp.class, args);
    }
}
