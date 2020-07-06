package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.dto.MatrixDto;
import co.simplon.bakerdelivery.model.Matrix;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface MatrixService {

    List<MatrixDto> getMatrix();

    Optional<Matrix> getMatrixById(Long id);

    List<MatrixDto> getMatrixListByRestaurantId(Long restaurantId);

    List<MatrixDto> getMatrixListByRestaurantIdAndStartDateBefore(Long restaurantId, LocalDate date);

    MatrixDto getFirstMatrixByRestaurantIdAndDayAndStartDateIsBeforeOrderByStartDateDesc(Long restaurantId, Integer day, LocalDate date);

    MatrixDto getMatrixByRestaurantIdAndEndDate(Long restaurantId, LocalDate date);

    MatrixDto getMatrixByRestaurantIdAndDayAndStartDateEqualsDate(Long restaurantId, Integer day, LocalDate date);


    MatrixDto updateMatrix(MatrixDto matrixDto, Long matrixId);

    MatrixDto createMatrix(MatrixDto matrixDto);



}
