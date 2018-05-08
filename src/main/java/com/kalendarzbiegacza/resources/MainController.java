package com.kalendarzbiegacza.resources;

import com.kalendarzbiegacza.domain.Race;
import com.kalendarzbiegacza.domain.RaceLinkedWebsite;
import com.kalendarzbiegacza.domain.repository.RaceLinkedWebsiteRepository;
import com.kalendarzbiegacza.domain.repository.RaceRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.sql.ordering.antlr.Factory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Łukasz on 2018-04-10.
 */

@Controller
@RequestMapping(path = "/")
public class MainController {

    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private RaceLinkedWebsiteRepository raceLinkedWebsiteRepository;

    @PostMapping (path="/addRace") // Map ONLY GET Requests
    public @ResponseBody int addNewRace (@RequestParam String name,
                                            @RequestParam String date/*(d)d.(m)m.yyyy*/ , @RequestParam String distance,
                                            @RequestParam String city, @RequestParam String fileUrl) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Race newRace = new Race();
        newRace.setName(name);
        newRace.setDistance(distance);
        newRace.setCity(city);
        String parsedDate[] = date.split("\\.");
        Calendar raceCalendar = Calendar.getInstance();
        raceCalendar.set(Integer.parseInt(parsedDate[2]),Integer.parseInt(parsedDate[1])-1,Integer.parseInt(parsedDate[0]),0,0,0);
        newRace.setDate(raceCalendar);
        newRace.setFileUrl(fileUrl);
        raceRepository.save(newRace);
        return newRace.getId();
    }

    @PostMapping (path="/addRaceLinkedWebsites")
    public @ResponseBody String addNewRaceLinkedWebsites(@RequestParam String[] websitesUrls, @RequestParam int raceId){
        for (String url: websitesUrls) {
            RaceLinkedWebsite raceLinkedWebsite = new RaceLinkedWebsite();
            raceLinkedWebsite.setUrl(url);
            raceLinkedWebsite.setRaceId(raceId);
            raceLinkedWebsiteRepository.save(raceLinkedWebsite);
        }
        return "All saved";
    }

    @GetMapping(path="/allRaces")
    public @ResponseBody Map<String, Iterable<Race>>  getAllRaces() {
        Map<String, Iterable<Race>> result = new HashMap<>();
        result.put("result", raceRepository.findAll());
        return result;
    }

    @GetMapping(path="/findWebsitesByRaceId")
    public @ResponseBody Map<String, Iterable<RaceLinkedWebsite>> findWebsitesByRaceId(@RequestParam int raceId) {
        Map<String, Iterable<RaceLinkedWebsite>> result = new HashMap<>();
        result.put("result", raceLinkedWebsiteRepository.findWebsitesByRaceId(raceId));
        return result;
    }

}
