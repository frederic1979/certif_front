package co.simplon.bakerdelivery.service;


import co.simplon.bakerdelivery.model.Week;
import co.simplon.bakerdelivery.repository.WeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeekServiceImpl implements WeekService{

    @Autowired
    WeekRepository weekRepository;

    @Override
   public List<Week> getWeeks(){
        return weekRepository.findAll();
    }

}
