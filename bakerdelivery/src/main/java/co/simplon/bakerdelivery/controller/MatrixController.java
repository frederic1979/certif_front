package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.MatrixDto;
import co.simplon.bakerdelivery.exception.MatrixNotFoundException;
import co.simplon.bakerdelivery.mappers.MatrixMapper;
import co.simplon.bakerdelivery.model.Matrix;
import co.simplon.bakerdelivery.service.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bakerdelivery/matrix")
@CrossOrigin("http://localhost:4200")
public class MatrixController {

    @Autowired
    MatrixService matrixService;

    @Autowired
    MatrixMapper matrixMapper;


    @GetMapping()
    public List<MatrixDto> getMatrix() {
        return matrixService.getMatrix();

    }



    @GetMapping("/{matrixId}")
    public ResponseEntity<MatrixDto> getMatrixById(@PathVariable Long matrixId) {
        Optional<Matrix> matrix = matrixService.getMatrixById(matrixId);
        if (matrix.isPresent()) {
            return ResponseEntity.ok(matrixMapper.toDto(matrix.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }



    @GetMapping("restaurant/{restaurantId}")
    public List<MatrixDto> getMatrixListByRestaurantId(@PathVariable Long restaurantId) {
         return matrixService.getMatrixListByRestaurantId(restaurantId);

    }


    @GetMapping("restaurants/{restaurantId}/{stringDate}")
    public List<MatrixDto> getMatrixListByRestaurantIdAndStartDateBefore(@PathVariable Long restaurantId,@PathVariable String stringDate) {
        LocalDate date = LocalDate.parse(stringDate);
        return matrixService.getMatrixListByRestaurantIdAndStartDateBefore(restaurantId, date);

    }

    @GetMapping("/restaurants/{restaurantId}")
    public MatrixDto getMatrixByRestaurantIdAndEndDate(@PathVariable Long restaurantId,
                                                       @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
                return matrixService.getMatrixByRestaurantIdAndEndDate(restaurantId, endDate);
    }



    @GetMapping("/restaurants/{restaurantId}/{day}/{stringDate}")
    public ResponseEntity<MatrixDto> getFirstMatrixByRestaurantIdAndDayAndStartDateIsBeforeOrderByStartDateDesc(@PathVariable Long restaurantId,
                                                             @PathVariable String stringDate, @PathVariable Integer day) {
        LocalDate date = LocalDate.parse(stringDate);
        try {return ResponseEntity.ok(matrixService.getFirstMatrixByRestaurantIdAndDayAndStartDateIsBeforeOrderByStartDateDesc(restaurantId, day, date));

        }
        catch (MatrixNotFoundException e){return ResponseEntity.notFound().build();}

    }



    @PutMapping("/{matrixId}")
    public ResponseEntity<MatrixDto> updateCommand(@RequestBody MatrixDto matrixDto, @PathVariable Long matrixId) {

        try {

            return ResponseEntity.ok(matrixService.updateMatrix(matrixDto, matrixId));
        } catch (MatrixNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    /* Method to create and return the new matrix  .*/
    @PostMapping()
    public MatrixDto createMatrix(@RequestBody MatrixDto matrixDto){
              return matrixService.createMatrix(matrixDto);
    }

}
