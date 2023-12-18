package io.bayrktlihn.jasperreports.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class City {
    private String cityNo;
    private String cityName;

    public City(String cityNo, String cityName) {
        this.cityNo = cityNo;
        this.cityName = cityName;
    }
}
