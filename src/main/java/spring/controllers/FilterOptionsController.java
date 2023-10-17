package spring.controllers;

import connections.databaseOperations.SQLWriter;
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


}
