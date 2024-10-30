package ua.coffee.coffeemarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@Data
public class TakeAwayOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameNew;
    private String numberNew;
    private String  timeNew;
    private String commentsNew;

    public TakeAwayOrder(String nameNew, String numberNew, String timeNew, String commentsNew) {
        this.nameNew = nameNew;
        this.numberNew = numberNew;
        this.timeNew = timeNew;
        this.commentsNew = commentsNew;
    }
}
