package io.bayrktlihn.jasperreports.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bayrktlihn.jasperreports.model.City;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    private final ObjectMapper objectMapper;

    public DataService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<City> getTurkishCities() {

        ClassPathResource classPathResource = new ClassPathResource("iller.json");

        List<City> cityList = new ArrayList<>();

        try (InputStream inputStream = classPathResource.getInputStream()) {
            Map<String, Object> cities = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            for (Map.Entry<String, Object> keyValue : cities.entrySet()) {
                String cityNo = String.format("%2s", keyValue.getKey()).replace(" ", "0");
                String cityName = (String) keyValue.getValue();
                cityList.add(new City(cityNo, cityName));
            }
            return cityList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
