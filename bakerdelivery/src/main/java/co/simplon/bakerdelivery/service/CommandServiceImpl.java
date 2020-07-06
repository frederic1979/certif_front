package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.exception.CommandNotFoundException;
import co.simplon.bakerdelivery.mappers.CommandMapper;
import co.simplon.bakerdelivery.model.Command;
import co.simplon.bakerdelivery.model.Etat;
import co.simplon.bakerdelivery.model.Matrix;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.repository.CommandRepository;
import co.simplon.bakerdelivery.repository.MatrixRepository;
import co.simplon.bakerdelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    CommandRepository commandRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MatrixRepository matrixRepository;

    @Autowired
    CommandMapper commandMapper;


    //Constructeur
    /*private CommandServiceImpl(CommandRepository commandRepository, RestaurantRepository restaurantRepository) {
        this.commandRepository = commandRepository;
        this.restaurantRepository = restaurantRepository;

    }*/

    @Override
    public List<Command> getCommands() {
        return commandRepository.findAll();
    }


    @Override
    public Optional<Command> getCommandById(Long commandId) {
        // d'abord on voit si la commande correspondant à cet ID existe
        Optional<Command> command = commandRepository.findById(commandId); //comme le findById est un optional,la command n'existe pas forcemm, on aura pas un type command mais Optional<Command>
        return command;
    }

    @Override
    public CommandDto createCommand(CommandDto commandDto) {
        Command command = commandMapper.toEntity(commandDto);
        /*System.out.println("Restau ID de commandDto : "+commandDto.getRestaurantId());
        System.out.println("command : "+command.getRestaurant().getId());*/
        command = commandRepository.save(command);
        return commandMapper.toDto(command);
    }

    @Override
    public CommandDto updateCommand(CommandDto commandDto, Long commandId) throws CommandNotFoundException {

        if (!commandRepository.existsById(commandId)) {
            throw new CommandNotFoundException();
        } else {

            Command command = commandMapper.toEntity(commandDto);
            command.setId(commandId);
            command = commandRepository.save(command);
            return commandMapper.toDto(command);
        }

    }

    @Override
    public Boolean deleteCommand(Long commandId) {
        if (commandRepository.existsById((commandId))) {
            commandRepository.deleteById(commandId);
            return true;
        } else return false;

    }

    @Override
    public List<Command> getCommandsByRestaurant(Long restaurantId) {

        List<Command> commands = new ArrayList<>();
        if (restaurantRepository.existsById(restaurantId)) { //On verifie que le restauID existe bien
            return commandRepository.findCommandsByRestaurantId(restaurantId); //Si oui

        } else {
            return commands;
        }

    }

    @Transactional
    @Override
    public Command getCommandByRestaurantIdAndDate(Long restaurantId, LocalDate date) {
        Optional<Command> existingCommand = commandRepository.findCommandByRestaurantIdAndDate(restaurantId, date);
        //j'ai tagué en etat Modif les commandes qui ont été updatées
        //au cas où on change la matrice, on ne fait apparaitre que ceux qui ont été modifié et si on a deja visualisé une commande
        //celle ci n'apparait qu'avec la matrice
        if (existingCommand.isPresent() && existingCommand.get().getEtat() == Etat.Modif) {

            return existingCommand.get();
        } else {

            Optional<Matrix> matrix =
                    matrixRepository.findFirstMatrixByRestaurantIdAndDayAndStartDateLessThanEqualOrderByStartDateDesc(
                            restaurantId,
                            date.getDayOfWeek().getValue() - 1,
                            date);

            if (matrix.isPresent()) {

                if (!existingCommand.isPresent()) {

                    CommandDto commandDto = new CommandDto(date,
                            matrix.get().getQuantity(),
                            Etat.Attente,
                            restaurantId);
                    return commandRepository.save(commandMapper.toEntity(commandDto));
                } else {
                    existingCommand.get().setQuantity(matrix.get().getQuantity());
                    return existingCommand.get();
                }

            } else {
                throw new CommandNotFoundException();
            }
        }

    }

    @Transactional  /*les instructions sont soit toutes exécutées, soit toutes annulées*/
    @Override
    public List<CommandDto> getCommandsByDate(LocalDate date) {

        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<CommandDto> commandsDtoOfTheDay = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            Optional<Command> existingCommand = commandRepository.findCommandByRestaurantIdAndDate(restaurant.getId(), date);


            if (existingCommand.isPresent()) {
                commandsDtoOfTheDay.add(commandMapper.toDto(existingCommand.get()));

            } else {

                Optional<Matrix> matrix =
                        matrixRepository.findFirstMatrixByRestaurantIdAndDayAndStartDateLessThanEqualOrderByStartDateDesc(
                                restaurant.getId(),
                                date.getDayOfWeek().getValue() - 1, // Get day of week from date
                                date
                        );



                 if (matrix.isPresent()) {

                    /*sinon si la matrix existe on créé la newCommandDto*/
                    CommandDto commandDto = new CommandDto(date,
                            matrix.get().getQuantity(),
                            Etat.Attente,
                            restaurant.getId());
                    commandRepository.save(commandMapper.toEntity(commandDto));
                    commandsDtoOfTheDay.add(commandDto);

                } else {
                    throw new CommandNotFoundException();
                }
            }
        }


        return commandsDtoOfTheDay;
    }

    @Override
    public List<CommandDto> getCommandsByEtatAndDate(Etat etat, LocalDate date){
        List<CommandDto> commandDtoList = commandMapper.toDto(commandRepository.findCommandsByEtatAndDate(etat,date));
        return commandDtoList;

    }

    @Override
    public List<CommandDto> getCommandsBetweenTwoDates(LocalDate start, LocalDate end) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<CommandDto> commandsDtoBetweenTwoDates = new ArrayList<>();

        /*we go on every restaurants*/
        for (Restaurant restaurant : restaurants) {

            /*on each restaurant we go on every date of the week*/
            for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
                Optional<Matrix> matrix =
                        matrixRepository.findFirstMatrixByRestaurantIdAndDayAndStartDateLessThanEqualOrderByStartDateDesc(
                                restaurant.getId(),
                                date.getDayOfWeek().getValue() - 1, // Get day of week from date
                                date
                        );
                Optional<Command> existingCommand = commandRepository.findCommandByRestaurantIdAndDate(restaurant.getId(), date);


                if (existingCommand.isPresent() && existingCommand.get().getEtat() == Etat.Modif) {
                    commandsDtoBetweenTwoDates.add(commandMapper.toDto(existingCommand.get()));
                } else {

                    if (matrix.isPresent()) {
                        if (!existingCommand.isPresent()) {
                            CommandDto commandDto = new CommandDto(date,
                                    matrix.get().getQuantity(),
                                    Etat.Attente,
                                    restaurant.getId());
                            commandRepository.save(commandMapper.toEntity(commandDto));
                            commandsDtoBetweenTwoDates.add(commandDto);
                        } else {
                            existingCommand.get().setQuantity(matrix.get().getQuantity());
                            commandsDtoBetweenTwoDates.add(commandMapper.toDto(existingCommand.get()));
                        }


                    } else {
                        throw new CommandNotFoundException();
                    }
                }
            }

        }


        return commandsDtoBetweenTwoDates;
    }



    @Override
    public List<Command> getCommandsByRestaurantIdAndBetweenTwoDates(Long restaurantId, LocalDate start, LocalDate end) {

        if (start == null && end == null) {
            return this.getCommandsByRestaurant(restaurantId);
        } else {
            return commandRepository.findCommandsBetweenDatesAndAndRestaurantId(restaurantId, start, end);
        }
    }



}
