package app.services;

import app.dtos.CityInfoDTO;
import app.dtos.WeatherInfoDTO;
import app.util.DataReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class WeatherService
{
    private DataReader dataReader;
    private final ObjectMapper objectMapper;
    private static EntityManagerFactory emf;

    public WeatherService(DataReader reader, EntityManagerFactory emf)
    {
        this.dataReader = reader;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.emf = emf;
    }

    public List<WeatherInfoDTO> getWeatherInfo()
    {
        String resp = dataReader.getDataFromUrl("");



        return null;

    }
    public void getHumidity()
    {

    }

    public void getTemperature()
    {

    }

    public void getWindText()
    {

    }

}
