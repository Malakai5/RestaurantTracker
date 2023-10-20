package spring.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:63342")
@RestController()
@RequestMapping("/filter")
public class FilterOptionsController {

        @RequestMapping(value = "/foodType", method = RequestMethod.GET)
        public List<String> getFoodTypeOptions()
        {
            List<String> results = new ArrayList<>();
            results.add("Italian");
            results.add("Southern");
            results.add("Cajun");
            results.add("Chinese");
            return results;
        }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public List<String> getCityOptions(@RequestParam(value = "state", defaultValue = "EMPTY") String state)
    {
        List<String> results = new ArrayList<>();
        System.out.println(state);
        if (state.equals("FL"))
        {
            System.out.println("OKAYYYY");
            results.add("Tampa");
            results.add("Orlando");
            results.add("Jacksonville");
            results.add("Miami");
            return results;
        }
        return results;
    }
}
