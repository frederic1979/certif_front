package co.simplon.bakerdelivery.dto;


import co.simplon.bakerdelivery.model.Etat;
import co.simplon.bakerdelivery.model.Restaurant;

import java.time.LocalDate;

public class CommandDto {


    private Long id;

    private LocalDate date;

    private Long quantity;

    private Etat etat;

    private Long restaurantId;

    public CommandDto() {
    }

    public CommandDto(LocalDate date, Long quantity, Etat etat, Long restaurantId) {
        this.date = date;
        this.quantity = quantity;
        this.etat = etat;
        this.restaurantId = restaurantId;
    }


    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
