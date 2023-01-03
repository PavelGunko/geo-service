package ru.netology;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.i18n.*;

/*Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
Проверить работу метода public String locale(Country country)*/
public class LocalizationServiceTest {


    private LocalizationServiceImpl localizationService;

    @BeforeEach
    void setUp() {
        localizationService = new LocalizationServiceImpl();
    }


    @Test
    public void localeRus() {
        String country = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", country);
    }

    @Test
    public void localeUsa() {
        String country = localizationService.locale(Country.USA);
        Assertions.assertEquals("Welcome", country);
    }

    @Test
    public void localeGermany() {
        String country = localizationService.locale(Country.GERMANY);
        Assertions.assertEquals("Welcome", country);
    }
}
