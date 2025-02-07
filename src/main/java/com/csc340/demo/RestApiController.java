package com.csc340.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController

public class RestApiController {
    //HashMap used for last function of displaying the searched for Digimon
    Map<String, Digimon> searchedMon = new HashMap<>();

    //Heavy inspiration from the Brewery example we were provided as the Assignment mentioned.
    @GetMapping("/digi")
    public Object getDigimon() {
        try {
            //CONSUMING A RESTFUL WEB SERVICE (API)
            String url = "https://digimon-api.vercel.app/api/digimon";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            List<Digimon> digiList = new ArrayList<>();

            //The response from the above API is a JSON Array, which we loop through.
            for (JsonNode rt : root) {
                //Extract relevant info from the response and use it for what you want, in this case build a Digimon object
                String name = rt.get("name").asText();
                String image = rt.get("img").asText();
                String level = rt.get("level").asText();

                Digimon digimon = new Digimon(name, image, level);
                digiList.add(digimon);
            }
            return digiList;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "error in /digi";
        }
    }


//Instead of displaying the whole list, search for and display a single digimon based on the name.  Store it in HashMap.
    @GetMapping("/digi/{mon}")
    public Object getDigimons(@PathVariable String mon) {
        try {
            //CONSUMING A RESTFUL WEB SERVICE (API)
            String url = "https://digimon-api.vercel.app/api/digimon/name/" + mon;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            //Make the API request
            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            //The response from the above API is still a JSON Array, we adjust it though since we do not want more than one.
                JsonNode digimonNode = root.get(0);

                //Get information and create a Digimon object
                Digimon digimon = new Digimon(
                        digimonNode.get("name").asText(),
                        digimonNode.get("img").asText(),
                        digimonNode.get("level").asText()
                );
                //Put the digimon into the HashMap
                searchedMon.put(mon, digimon);
                return digimon;


        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "error in /digi/{mon}";
        }

    }

    //Simply displays the list of searched Digimon thus far.
    @GetMapping("/searched")
    public Map<String, Digimon> getSearchedDigimon() {
        return searchedMon;
    }

}
