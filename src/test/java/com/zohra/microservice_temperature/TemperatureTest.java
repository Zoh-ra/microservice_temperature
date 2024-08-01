package com.zohra.microservice_temperature;
import com.zohra.microservice_temperature.dto.MissingTemperatureResponse;
import com.zohra.microservice_temperature.dto.TemperatureResponse;
import com.zohra.microservice_temperature.entity.Temperature;
import com.zohra.microservice_temperature.projection.DateProjection;
import com.zohra.microservice_temperature.projection.TemperatureProjection;
import com.zohra.microservice_temperature.repository.TemperatureRepository;
import com.zohra.microservice_temperature.service.TemperatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
public class TemperatureTest {
    @Mock
    private TemperatureRepository temperatureRepository;

    @InjectMocks
    private TemperatureService temperatureService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTemperaturesBySondeAndDatePeriod() {
        String sonde = "210100A";
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 15, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16, 16, 0);

        TemperatureProjection temp1 = new TemperatureProjection(21.8, sonde, LocalDateTime.of(2024, 5, 16, 15, 0));
        TemperatureProjection temp2 = new TemperatureProjection(21.8, sonde, LocalDateTime.of(2024, 5, 16, 16, 0));
        List<TemperatureProjection> temperatures = Arrays.asList(temp1, temp2);

        when(temperatureRepository.findTemperaturesBySondeAndDateBetween(sonde, startDate, endDate)).thenReturn(temperatures);
        when(temperatureRepository.countTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate)).thenReturn(2);

        TemperatureResponse response = temperatureService.getTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        assertEquals(2, response.getTotalTemperature());
        assertEquals(2, response.getDataTemperature().size());
        assertEquals(temp1, response.getDataTemperature().get(0));
        assertEquals(temp2, response.getDataTemperature().get(1));
    }

    @Test
    public void testGetTemperaturesBySondeAndDatePeriodNoResults() {
        String sonde = "210100B";
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 15, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16, 16, 0);

        List<TemperatureProjection> temperatures = Arrays.asList();

        when(temperatureRepository.findTemperaturesBySondeAndDateBetween(sonde, startDate, endDate)).thenReturn(temperatures);
        when(temperatureRepository.countTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate)).thenReturn(0);

        TemperatureResponse response = temperatureService.getTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        assertEquals(0, response.getTotalTemperature());
        assertEquals(0, response.getDataTemperature().size());
    }

    @Test
    public void testGetTemperaturesBySondeAndDatePeriodNonContinuousResults() {
        String sonde = "210100C";
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 15, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16, 16, 0);

        TemperatureProjection temp1 = new TemperatureProjection(21.8, sonde, LocalDateTime.of(2024, 5, 16, 15, 0));
        TemperatureProjection temp2 = new TemperatureProjection(22.0, sonde, LocalDateTime.of(2024, 5, 16, 15, 30));
        List<TemperatureProjection> temperatures = Arrays.asList(temp1, temp2);

        when(temperatureRepository.findTemperaturesBySondeAndDateBetween(sonde, startDate, endDate)).thenReturn(temperatures);
        when(temperatureRepository.countTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate)).thenReturn(2);

        TemperatureResponse response = temperatureService.getTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        assertEquals(2, response.getTotalTemperature());
        assertEquals(2, response.getDataTemperature().size());
        assertEquals(temp1, response.getDataTemperature().get(0));
        assertEquals(temp2, response.getDataTemperature().get(1));
    }

    @Test
    public void testGetTemperaturesBySondeAndDatePeriodVariedResults() {
        String sonde = "210100C";
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 15, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16, 16, 0);

        TemperatureProjection temp1 = new TemperatureProjection(21.8, sonde, LocalDateTime.of(2024, 5, 16, 15, 0));
        TemperatureProjection temp2 = new TemperatureProjection(22.3, sonde, LocalDateTime.of(2024, 5, 16, 16, 0));
        List<TemperatureProjection> temperatures = Arrays.asList(temp1, temp2);

        when(temperatureRepository.findTemperaturesBySondeAndDateBetween(sonde, startDate, endDate)).thenReturn(temperatures);
        when(temperatureRepository.countTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate)).thenReturn(2);

        TemperatureResponse response = temperatureService.getTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        assertEquals(2, response.getTotalTemperature());
        assertEquals(2, response.getDataTemperature().size());
        assertEquals(temp1, response.getDataTemperature().get(0));
        assertEquals(temp2, response.getDataTemperature().get(1));
    }

    @Test
    public void testGetTemperaturesBySondeAndDatePeriodFalse() {
        String sonde = "210100B";
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 15, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16, 16, 0);

        TemperatureProjection temp1 = new TemperatureProjection(21.8, sonde, LocalDateTime.of(2024, 5, 16, 15, 0));
        TemperatureProjection temp2 = new TemperatureProjection(21.8, sonde, LocalDateTime.of(2024, 5, 16, 16, 0));
        List<TemperatureProjection> temperatures = Arrays.asList(temp1, temp2);

        when(temperatureRepository.findTemperaturesBySondeAndDateBetween(sonde, startDate, endDate)).thenReturn(temperatures);
        when(temperatureRepository.countTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate)).thenReturn(2);

        TemperatureResponse response = temperatureService.getTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        assertEquals(2, response.getTotalTemperature());
        assertEquals(2, response.getDataTemperature().size());
        assertEquals(temp1, response.getDataTemperature().get(0));
        assertEquals(temp2, response.getDataTemperature().get(1));
    }

    @Test
    public void testGetMissingTemperaturesBySondeAndDatePeriodNoMissingDates() {
        String sonde = "210100A";
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16, 10, 25);

        List<DateProjection> existingDates = Arrays.asList(
                new DateProjection(LocalDateTime.of(2024, 5, 16, 10, 2)),
                new DateProjection(LocalDateTime.of(2024, 5, 16, 10, 12)),
                new DateProjection(LocalDateTime.of(2024, 5, 16, 10, 22))
        );

        when(temperatureRepository.findMissingTemperaturesBySondeAndDateBetween(sonde, startDate, endDate))
                .thenReturn(existingDates);

        MissingTemperatureResponse response = temperatureService.getMissingTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        assertNotNull(response);
        assertEquals(0, response.getTotalMissingTemperature());
        assertEquals(0, response.getDataMissingTemperature().size());
    }

    @Test
    public void testGetMissingTemperaturesBySondeAndDatePeriodWithMissingDates() {
        String sonde = "210100B";
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16, 10, 41);

        List<DateProjection> existingDates = Arrays.asList(
                new DateProjection(LocalDateTime.of(2024, 5, 16, 10, 0)),
                new DateProjection(LocalDateTime.of(2024, 5, 16, 10, 20)),
                new DateProjection(LocalDateTime.of(2024, 5, 16, 10, 40))
        );

        when(temperatureRepository.findMissingTemperaturesBySondeAndDateBetween(sonde, startDate, endDate))
                .thenReturn(existingDates);

        MissingTemperatureResponse response = temperatureService.getMissingTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        assertNotNull(response);
        assertEquals(2, response.getTotalMissingTemperature());
        List<DateProjection> missingDates = response.getDataMissingTemperature();
        assertEquals(2, missingDates.size());
        assertEquals(LocalDateTime.of(2024, 5, 16, 10, 10), missingDates.get(0).getDate());
        assertEquals(LocalDateTime.of(2024, 5, 16, 10, 30), missingDates.get(1).getDate());
    }
    
}