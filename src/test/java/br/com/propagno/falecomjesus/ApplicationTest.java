package br.com.propagno.falecomjesus;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void applicationClassExists() {
        // Verifica que a classe Application existe
        assertNotNull(Application.class);
    }

    @Test
    void applicationMainMethodExists() {
        // Verifica que o método main existe
        try {
            Method mainMethod = Application.class.getDeclaredMethod("main", String[].class);
            assertNotNull(mainMethod);
            assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
            assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("Método main não encontrado");
        }
    }

    @Test
    void shouldHaveCorrectAnnotations() {
        // Verifica que a classe tem as anotações corretas
        assertTrue(Application.class.isAnnotationPresent(org.springframework.boot.autoconfigure.SpringBootApplication.class));
        assertTrue(Application.class.isAnnotationPresent(org.springframework.boot.autoconfigure.domain.EntityScan.class));
        assertTrue(Application.class.isAnnotationPresent(org.springframework.data.jpa.repository.config.EnableJpaRepositories.class));
    }

    @Test
    void shouldHaveMainMethodWithCorrectSignature() {
        // Verifica assinatura do método main
        try {
            Method mainMethod = Application.class.getDeclaredMethod("main", String[].class);
            assertEquals(void.class, mainMethod.getReturnType());
            assertEquals(1, mainMethod.getParameterCount());
            assertEquals(String[].class, mainMethod.getParameterTypes()[0]);
        } catch (NoSuchMethodException e) {
            fail("Método main não encontrado");
        }
    }
}

