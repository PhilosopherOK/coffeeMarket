package ua.coffee.coffeemarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@NoArgsConstructor
@Data
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameNew;
    private String numberNew;
    private String streetNew;
    private String buildNew;
    private String apartNew;
    private String commentsNew;

    public DeliveryOrder(String nameNew, String numberNew, String streetNew, String buildNew, String apartNew, String commentsNew) {
        this.nameNew = nameNew;
        this.numberNew = numberNew;
        this.streetNew = streetNew;
        this.buildNew = buildNew;
        this.apartNew = apartNew;
        this.commentsNew = commentsNew;
    }
}
