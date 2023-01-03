package ru.netology;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;


/*Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
Проверить работу метода public Location byIp(String ip)*/

public class GeoServiceImplTest {
    public GeoServiceImpl geoService;


    @BeforeEach
    private void setUp() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void byIpMoscow() {
        geoService = new GeoServiceImpl();
        Location expectedLocation = new Location("", Country.RUSSIA, "", 0);
        Country expectedCountry = expectedLocation.getCountry();
        Country resultTestCountry = geoService.byIp(GeoServiceImpl.MOSCOW_IP).getCountry();
        Assertions.assertEquals(expectedCountry, resultTestCountry);
    }

    @Test
    public void byIpNewYork() {
        geoService = new GeoServiceImpl();
        Location location = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location geo = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Assertions.assertEquals(geo.getCity(), location.getCity());
        Assertions.assertEquals(geo.getCountry(), location.getCountry());

    }


}

