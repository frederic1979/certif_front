package co.simplon.bakerdelivery.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Week {


    @Id
    private Long id;

    @Column
    private LocalDate start;

    @Column
    private LocalDate end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }


}
