package br.com.propagno.falecomjesus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Classe principal da aplicação Fale com Jesus.
 * 
 * @author Propagno
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = "br.com.propagno.falecomjesus")
@EntityScan("br.com.propagno.falecomjesus.infrastructure.persistence.entity")
@EnableJpaRepositories("br.com.propagno.falecomjesus.infrastructure.persistence.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
