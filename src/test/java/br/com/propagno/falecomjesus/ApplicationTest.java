package br.com.propagno.falecomjesus;

import org.junit.jupiter.api.Test;

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
            Application.class.getDeclaredMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            fail("Método main não encontrado");
        }
    }
}

