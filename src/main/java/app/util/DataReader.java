package app.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataReader
{
    private  ObjectMapper objectMapper = new ObjectMapper();

    public  String getDataFromUrl(String url)
    {
        try
        {
            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .version(HttpClient.Version.HTTP_1_1) // added for DAWA API
                .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            // Check the status code and print the response
            if (response.statusCode() == 200)
            {
                String resp = response.body();
                System.out.println(resp);
                // check th ejson node type
                JsonNode jsonNode = objectMapper.readTree(resp);

                // if its an array
                if (jsonNode.isArray())
                {
                    //List<WeatherInfoDTO> weatherDTO = objectMapper.readValue(resp, new TypeReference<List<WeatherInfoDTO>>() {} );
                    //System.out.println(weatherDTO);
                } else if (jsonNode.isObject())
                {
                    //WeatherInfoDTO weatherInfoDTO = objectMapper.readValue(resp, WeatherInfoDTO.class);
                    //System.out.println(weatherInfoDTO);
                }
                return resp;
            } else
            {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";

    }
}
