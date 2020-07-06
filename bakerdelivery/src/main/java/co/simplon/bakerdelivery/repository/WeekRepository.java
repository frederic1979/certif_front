package co.simplon.bakerdelivery.repository;

import co.simplon.bakerdelivery.model.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekRepository extends JpaRepository<Week,Long> {
}
