package app;

import app.config.HibernateConfig;
import app.daos.CityDAO;
import app.daos.WeatherDAO;
import app.dtos.CityInfoDTO;
import app.services.CityService;
import app.services.WeatherService;
import app.util.DataReader;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {

        // db
        final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        // api reader
        DataReader reader = new DataReader();

        // serices
        CityService cityService = new CityService(reader, emf);
        WeatherService weatherSService = new WeatherService(reader, emf);

        // daos
        WeatherDAO weatherDAO = new WeatherDAO(emf);
        CityDAO cityDAO = new CityDAO(emf);


        try
        {
            // service ops
            List<CityInfoDTO> dto1 =  cityService.getCityInfo2("Roskilde");

            // dao ops
            cityDAO.createList(dto1);

            System.out.println(dto1);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}