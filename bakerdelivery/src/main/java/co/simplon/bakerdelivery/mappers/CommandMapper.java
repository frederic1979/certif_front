package co.simplon.bakerdelivery.mappers;
import co.simplon.bakerdelivery.dto.CommandDto;
import co.simplon.bakerdelivery.model.Command;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CommandMapper {

    @Mapping(source = "restaurant.id", target = "restaurantId")
        //@Mapping(source = "restaurant.name", target = "name")
    CommandDto toDto(Command command);


    List<CommandDto> toDto(List<Command> commands);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    Command toEntity(CommandDto commandDto);

}
