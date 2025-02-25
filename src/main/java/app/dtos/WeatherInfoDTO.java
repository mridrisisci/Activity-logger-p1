package app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "weather_info")
public class WeatherInfoDTO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonProperty("LocationName")
    private String locationName;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "current_data_id")
    private CurrentData currentData;


    @Entity
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "current_data")
    public static class CurrentData
    {
        private String temperature;
        private String humidity;
        private String windText;


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        // Override toString() metode for CurrentData
        @Override
        public String toString()
        {
            return "CurrentData{" +
                "temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", windText='" + windText + '\'' +
                '}';
        }
    }

    // Override toString() metode for WeatherInfoDTO
    @Override
    public String toString()
    {
        return "WeatherInfoDTO{" +
            "locationName='" + locationName + '\'' +
            ", currentData=" + (currentData != null ? currentData.toString() : "No data available") +
            '}';

    }
}



