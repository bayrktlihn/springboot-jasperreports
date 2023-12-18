package io.bayrktlihn.jasperreports.controller;

import io.bayrktlihn.jasperreports.model.City;
import io.bayrktlihn.jasperreports.service.DataService;
import io.bayrktlihn.jasperreports.util.JasperReports;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final DataService dataService;

    @GetMapping(value = "demo", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> demo() throws IOException {

        List<City> turkishCities = dataService.getTurkishCities();

        ClassPathResource classPathResource = new ClassPathResource("jasper-report-designs/Blank_A4.jrxml");
        HashMap<String, Object> params = new HashMap<>();
        params.put("turkishCities", new JRBeanCollectionDataSource(turkishCities));

        byte[] pdfContent = JasperReports.createPdf(classPathResource.getInputStream(), params);
        HashMap<String, Object> response = new HashMap<>();
        response.put("pdfContent", Base64.getEncoder().encodeToString(pdfContent));
        return response;
    }
}
