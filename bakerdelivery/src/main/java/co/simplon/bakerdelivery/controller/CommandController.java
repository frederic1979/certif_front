package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.mappers.CommandMapper;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.model.Etat;
import co.simplon.bakerdelivery.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;


/**
 * This controller is in charge of responding to http calls on /api/bakerdelivery/commands.
 * It can provide commands
 */
@RestController
@RequestMapping("api/bakerdelivery/commands")
@CrossOrigin("http://localhost:4200") //j'autorise le navigateur a venir taper sur le controller
public class CommandController {


    CommandService commandService;
    public CommandController(CommandService commandService) {
        this.commandService = commandService;}

    @Autowired
    CommandMapper commandMapper;

    /* Method to return a command with restaurantId and date .*/

    @GetMapping("restaurant/{restaurantId}/{dateString}")
    public ResponseEntity<CommandDto> getCommandByRestaurantIdAndDate
            (@PathVariable Long restaurantId, @PathVariable String dateString) {
        LocalDate date = LocalDate.parse(dateString);
      try{  return ResponseEntity.ok(commandMapper.toDto(commandService.getCommandByRestaurantIdAndDate(restaurantId, date)));}
      catch (CommandNotFoundException e){return ResponseEntity.badRequest().build();}
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping
    public List<CommandDto> getCommands() {
        return commandMapper.toDto(commandService.getCommands());
    }



    @GetMapping("/{commandId}")
    public ResponseEntity<CommandDto> getCommandById(@PathVariable Long commandId) {
        Optional<Command> command = commandService.getCommandById(commandId);
        if (command.isPresent()) {
            return ResponseEntity.ok(commandMapper.toDto(command.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("restaurant/{restaurantId}/datesbetween")
    public ResponseEntity<List<Command>> getCommandsByRestaurantAndOptionalDateAndBetweenTwoDates(
            @PathVariable Long restaurantId,
            @RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        try {

            return ResponseEntity.ok(commandService.getCommandsByRestaurantIdAndBetweenTwoDates(restaurantId, start, end));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("date/{stringDate}")
    public List<CommandDto> getCommandsByDate(@PathVariable String stringDate) {
        LocalDate date = LocalDate.parse(stringDate);
        return commandService.getCommandsByDate(date);

    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("etat/{etat}/{stringDate}")
    public List<CommandDto> getCommandsByEtatAndDate(@PathVariable Etat etat, @PathVariable String stringDate) {
        LocalDate date = LocalDate.parse(stringDate);
        return commandService.getCommandsByEtatAndDate(etat,date);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("/week/{id}/{stringStart}/{stringEnd}")
    public List<CommandDto> getCommandsBetweenTwoDates(@PathVariable Long id,@PathVariable String stringStart,@PathVariable String stringEnd)
    {

        LocalDate start = LocalDate.parse(stringStart);
        LocalDate end = LocalDate.parse(stringEnd);
        return this.commandService.getCommandsBetweenTwoDates(start,end);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @PostMapping
    public ResponseEntity<?> createCommand(@RequestBody CommandDto command) {
        try {
            return ResponseEntity.ok(commandService.createCommand(command));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PutMapping("/{commandId}")
    public ResponseEntity<CommandDto> updateCommand(@RequestBody CommandDto commandDto, @PathVariable Long commandId) {

        try {


            return ResponseEntity.ok(commandService.updateCommand(commandDto, commandId));
        } catch (CommandNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{commandId}")
    public ResponseEntity<Command> deleteCommand(@PathVariable Long commandId) {
        System.out.println("here !");
        if (commandService.deleteCommand(commandId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
