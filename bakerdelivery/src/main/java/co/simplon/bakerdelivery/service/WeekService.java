package co.simplon.bakerdelivery.service;


import co.simplon.bakerdelivery.model.Week;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeekService {

    List<Week> getWeeks();

}
