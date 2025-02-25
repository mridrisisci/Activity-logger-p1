package app.services;

import app.config.HibernateConfig;
import app.daos.CityDAO;
import app.dtos.CityInfoDTO;
import app.util.DataReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CityService
{
    private DataReader dataReader;
    private final ObjectMapper objectMapper;
    private static final String BASE_URL = "https://dawa.aws.dk/steder?hovedtype=Bebyggelse&undertype=by&prim%C3%A6rtnavn=";
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final CityDAO cityDAO = new CityDAO(emf);


    public CityInfoDTO createCities()
    {
        //CityInfoDTO cityInfoDTO = CityInfoDTO.builder()
        return null;
    }

    public CityService(DataReader reader, EntityManagerFactory emf)
    {
        this.emf = emf;
        this.dataReader = reader;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public CityInfoDTO getCityInfo(String city) throws Exception
    {
        String resp = dataReader.getDataFromUrl(BASE_URL + city);
        List<CityInfoDTO> list = objectMapper.readValue(resp, objectMapper.getTypeFactory().constructCollectionType(List.class, CityInfoDTO.class));
        System.out.println(list);
        cityDAO.createList(list);
        return null;

    }

    public List<CityInfoDTO> getCityInfo2(String city) throws Exception {
        // Fetch data from external API
        String resp = dataReader.getDataFromUrl(BASE_URL + city);

        // Parse the response into a JsonNode
        JsonNode jsonNode = objectMapper.readTree(resp);

        // Debugging: Print out the entire JSON response
        System.out.println(jsonNode.toString()); // For debugging purposes

        // Get the first object from the array (since it's an array)
        JsonNode cityNode = jsonNode.get(0);  // Because the JSON starts with a list of objects

        // Deserialize the main city info
        CityInfoDTO cityInfo = objectMapper.treeToValue(cityNode, CityInfoDTO.class);
        List<CityInfoDTO> list = objectMapper.readValue(cityNode.toString(), new TypeReference<>() {});
        // Print the deserialized CityInfoDTO object (for debugging)
        System.out.println(cityInfo);

        return list;
    }


}
