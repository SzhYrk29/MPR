package pl.eu.pjatk.Spring_Boot;

import org.junit.jupiter.api.Test;
import pl.eu.pjatk.Spring_Boot.service.StringUtilsService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsServiceTest {

    @Test
    public void toUpperCase() {
        StringUtilsService service = new StringUtilsService();

        String result = service.toUpperCase("hello");
        assertEquals("HELLO", result);
    }

    @Test
    public void toLowerCaseExceptFirstLetter() {
        StringUtilsService service = new StringUtilsService();

        String result = service.toLowerCaseExceptFirstLetter("hELLO");
        assertEquals("Hello", result);
    }
}