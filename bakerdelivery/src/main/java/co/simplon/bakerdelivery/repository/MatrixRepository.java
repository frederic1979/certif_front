package co.simplon.bakerdelivery.repository;


import co.simplon.bakerdelivery.model.Matrix;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatrixRepository extends JpaRepository<Matrix, Long> {


    // @Query("select m from Matrix m where m.restaurant.id = :restaurantId and m.day = :day
    // and m.startDate < :date order by m.startDate desc")
    Optional<Matrix> findFirstMatrixByRestaurantIdAndDayAndStartDateLessThanEqualOrderByStartDateDesc(
            Long restaurantId,
            Integer day,
            LocalDate date
    );


    List<Matrix> findFirstMatrixByRestaurantIdAndStartDateIsBeforeOrderByStartDateDesc(Long restaurantId, LocalDate date);



    List<Matrix> findMatrixByRestaurantId(Long restaurantId);
/*Matrix findMatrixByRestaurantIdAndEndDateAndDay(Long restaurantId, LocalDate date, Integer day);*/

Matrix findMatrixByRestaurantIdAndEndDate(Long restaurantId, LocalDate date);

/*    @Query("select m from Matrix m where  and m.startDate < :date order by m.startDate desc")
    List<Matrix> findFirstMatrixBetweenTwoDatesAndStartDateLessThanEqualOrderByStartDateDesc(LocalDate start,LocalDate end);*/

/*    List<Matrix> findAllMatrixByRestaurantIdAndStartDateIsBeforeOrderByStartDateDesc(
            Long restaurantId,
             LocalDate date
    );*/

    Optional<Matrix> findMatrixByRestaurantIdAndDayAndStartDate(Long restaurantId, Integer day, LocalDate date);




    @Query("select m from Matrix m where m.restaurant.id = :restaurantId and m.day = :day and m.startDate = :date")
    Optional<Matrix> findMatrixByRestaurantIdAndDayAndStartDateEqualsDate(
            Long restaurantId,
            Integer day,
            LocalDate date
    );



}
