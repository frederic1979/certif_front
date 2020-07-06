package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.model.Week;
import co.simplon.bakerdelivery.service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/bakerdelivery/weeks")
@CrossOrigin("http://localhost:4200")
public class WeekController {

    @Autowired
    WeekService weekService;

    @GetMapping
    public List<Week> getWeeks() {
        return weekService.getWeeks();
    }


}
