package co.simplon.bakerdelivery.mappers;
import co.simplon.bakerdelivery.dto.MatrixDto;
import co.simplon.bakerdelivery.model.Matrix;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatrixMapper  {


    @Mapping(source = "restaurant.id", target = "restaurantId")
        //@Mapping(source = "restaurant.name", target = "name")
    MatrixDto toDto(Matrix matrix);

    List<MatrixDto> toDto(List<Matrix> matrixList);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    Matrix toEntity(MatrixDto matrixDto);

    List<Matrix> toEntity(List<MatrixDto> matrixDto);
}
