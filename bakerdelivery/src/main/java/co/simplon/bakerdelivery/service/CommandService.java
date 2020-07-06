package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.model.Etat;
import co.simplon.bakerdelivery.repository.CommandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface CommandService {

    List<Command> getCommands();

    Optional<Command> getCommandById(Long commandId);

    CommandDto createCommand(CommandDto command);

    CommandDto updateCommand(CommandDto command, Long commandId) throws CommandNotFoundException;

    Boolean deleteCommand(Long commandId);

    List<Command> getCommandsByRestaurant(Long restaurantId);


    Command getCommandByRestaurantIdAndDate( Long restaurantId,LocalDate date) throws CommandNotFoundException;

    List<Command> getCommandsByRestaurantIdAndBetweenTwoDates(Long restaurantId, LocalDate start, LocalDate end);

    List<CommandDto> getCommandsByDate(LocalDate date);

    List<CommandDto> getCommandsByEtatAndDate(Etat etat, LocalDate date);


    List<CommandDto> getCommandsBetweenTwoDates( LocalDate start, LocalDate end);
}
