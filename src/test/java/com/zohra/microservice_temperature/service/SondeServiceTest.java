package com.zohra.microservice_temperature.service;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class SondeServiceTest {
/***
    @Mock
    private SondeUnRepository sondeUnRepository;

    @InjectMocks
    private SondeService sondeService;

    @Test
    public void testGetTemperatureByPeriodForSonde1() {
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 15, 40);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16 , 17, 20);

        SondeUn sonde1 = new SondeUn();
        sonde1.setDate(LocalDateTime.of(2024, 5, 16, 15, 49));
        sonde1.setTemperature(21.80);
        sonde1.setTensionBatterie(2.900);
        sonde1.setRssi(-67.00);
        sonde1.setConcentrateur("7231");
        sonde1.setHeureReception(LocalDateTime.of(2024, 5, 16, 15, 55));
        sonde1.setArchivage(false);
        sonde1.setIntervalleMesure(600);

        List<SondeUn> expectedTemperatures = Arrays.asList(sonde1);

        when(sondeUnRepository.findByDateBetween(startDate, endDate)).thenReturn(expectedTemperatures);

        List<SondeUn> actualTemperatures = sondeService.getTemperaturesByPeriodForSondeUn(startDate, endDate);

        assertFalse(actualTemperatures.isEmpty());
        assertEquals(expectedTemperatures, actualTemperatures);
    }

    @Test
    public void testGetTemperatureByPeriodForSonde2() {
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 16, 16, 2);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 16 , 16, 33);

        SondeDeux sonde2 = new SondeDeux();
        sonde2.setDate(LocalDateTime.of(2024, 5, 16, 16, 2));
        sonde2.setTemperature(18.40);
        sonde2.setTensionBatterie(3.100);
        sonde2.setRssi(-81.00);
        sonde2.setConcentrateur("0000A5AD");
        sonde2.setHeureReception(LocalDateTime.of(2024, 5, 16, 16, 8));
        sonde2.setArchivage(false);
        sonde2.setIntervalleMesure(600);

        List<SondeDeux> expectedTemperaturesDeux = Arrays.asList(sonde2);

        when(sondeDeuxRepository.findByDateBetweenDeux(startDate, endDate)).thenReturn(expectedTemperaturesDeux);

        List<SondeDeux> actualTemperatures = sondeService.getTemperaturesByPeriodForSondeDeux(startDate, endDate);

        assertFalse(actualTemperatures.isEmpty());
        assertEquals(expectedTemperaturesDeux, actualTemperatures);
    }***/
}