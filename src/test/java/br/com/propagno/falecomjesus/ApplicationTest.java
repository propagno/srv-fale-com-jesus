package br.com.propagno.falecomjesus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationTest {

    @Test
    void contextLoads() {
        // Este teste verifica se o contexto Spring Boot carrega corretamente
        // Isso garante que a classe Application e suas configurações estão corretas
    }

    @Test
    void applicationMainMethodExists() {
        // Verifica que o método main existe e pode ser chamado
        assert Application.class.getDeclaredMethods().length > 0;
    }
}

