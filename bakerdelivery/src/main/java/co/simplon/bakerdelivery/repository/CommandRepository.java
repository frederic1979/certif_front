package co.simplon.bakerdelivery.repository;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.model.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandRepository extends JpaRepository<Command,Long> {


     Optional<Command> findCommandByRestaurantIdAndDate( Long restaurantId, LocalDate date);



     List<Command> findCommandsByRestaurantId(Long restaurantId); //Derriere il y a du code SQL Spring va interpreter tt seul et sait qu 'il doit chercher via le restaurantId et pas restaurant !!



     @Query("select command from Command command join command.restaurant restaurant where restaurant.id = :restaurantId and command.date >= :start and command.date <= :end")
     List<Command> findCommandsBetweenDatesAndAndRestaurantId(Long restaurantId, LocalDate start, LocalDate end);

     @Query("select command from Command command  where command.date >= :start and command.date <= :end")
     List<Command> findCommandsBetweenDates(LocalDate start, LocalDate end);


     List<Command> findCommandsByEtatAndDate(Etat etat, LocalDate date);




     //@Query pas nécessaire, juste si je veux implémenter avec du sql ensuite
     List<Command> findCommandsByDate(LocalDate date); // idem spring écrit tt seul la methode

     Optional<Command> findCommandByRestaurantIdAndDateAndEtat(Long restaurantId, LocalDate date, Etat etat);

     }




