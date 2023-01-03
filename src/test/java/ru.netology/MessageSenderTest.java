package ru.netology;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

/* Написать тесты для проверки языка отправляемого сообщения (класс MessageSender)
   с использованием Mockito*/
public class MessageSenderTest {
    private MessageSender messageSender;
    @Mock
    GeoService geoService;
    @Mock
    LocalizationServiceImpl localizationService;
    Map<String, String> header = new HashMap<>();


    @BeforeEach
    public void setup() {

        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<String, String>();

    }

    @Test
    public void sendsOnlyEnglishTest() {

       /* Проверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится
        к американскому сегменту адресов. */
        header.put("x-real-ip", "96.44.183.149");

        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("Boston", Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        Assertions.assertEquals(messageSender.send(header), "Welcome");

    }
    /*Проверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
     */

    @Test
    public void sendsOnlyRuslishTest() {

        header.put("x-real-ip", "172.44.183.149");

        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        Assertions.assertEquals(messageSender.send(header), "Добро пожаловать");

    }

}