package co.simplon.bakerdelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Restaurant {

    @Id
    @SequenceGenerator(name = "restaurant_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String adresse;

    @Column
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Command> commands = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.PERSIST)
    private List<Matrix> matrixList = new ArrayList<>();

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
