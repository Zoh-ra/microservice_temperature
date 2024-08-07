package com.zohra.microservice_temperature.service;

import com.zohra.microservice_temperature.dto.MissingTemperatureResponse;
import com.zohra.microservice_temperature.entity.Temperature;
import com.zohra.microservice_temperature.projection.DateProjection;
import com.zohra.microservice_temperature.projection.TemperatureProjection;
import com.zohra.microservice_temperature.dto.TemperatureResponse;
import com.zohra.microservice_temperature.repository.TemperatureRepository;
import com.zohra.microservice_temperature.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    public TemperatureResponse getTemperaturesBySondeAndDatePeriod(String sonde, LocalDateTime startDate, LocalDateTime endDate) {
        List<TemperatureProjection> temperatures = temperatureRepository.findTemperaturesBySondeAndDateBetween(sonde, startDate, endDate);
        Integer totalTemperature = temperatureRepository.countTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        TemperatureResponse response = new TemperatureResponse();
        response.setDataTemperature(temperatures);
        response.setTotalTemperature(totalTemperature);

        return response;
    }

    public MissingTemperatureResponse getMissingTemperaturesBySondeAndDatePeriod(String sonde, LocalDateTime startDate, LocalDateTime endDate) {
        // Arrondir les dates de début et de fin vers le bas à l'intervalle de 10 minutes le plus proche
        LocalDateTime roundedStartDate = DateTimeUtils.roundMinutesDownToNearestTen(startDate);
        LocalDateTime extendedEndDate = DateTimeUtils.roundMinutesDownToNearestTen(endDate.plusMinutes(10));

        // Récupérer les températures existantes dans la plage de dates arrondies
        List<DateProjection> existingTemperatures = temperatureRepository.findMissingTemperaturesBySondeAndDateBetween(sonde, roundedStartDate, extendedEndDate);

        // Arrondir les dates existantes vers le bas à l'intervalle de 10 minutes le plus proche
        List<LocalDateTime> roundedExistingDates = existingTemperatures.stream()
                .map(DateProjection::getDate)
                .map(DateTimeUtils::roundMinutesDownToNearestTen)
                .sorted()
                .collect(Collectors.toList());

        // Générer toutes les dates possibles dans la plage spécifiée
        List<LocalDateTime> allDatesInRange = DateTimeUtils.generateDateRange(roundedStartDate, extendedEndDate, 10);

        // Trouver les dates manquantes en comparant toutes les dates possibles avec les dates existantes
        List<LocalDateTime> missingDates = allDatesInRange.stream()
                .filter(date -> !roundedExistingDates.contains(date))
                .filter(date -> !date.equals(extendedEndDate)) // Exclure la date de fin arrondie
                .collect(Collectors.toList());

        // Créer la réponse avec les dates manquantes
        List<DateProjection> missingTemperatures = missingDates.stream()
                .map(DateProjection::new)
                .collect(Collectors.toList());

        MissingTemperatureResponse response = new MissingTemperatureResponse();
        response.setDataMissingTemperature(missingTemperatures);
        response.setTotalMissingTemperature(missingTemperatures.size());

        return response;
    }
}