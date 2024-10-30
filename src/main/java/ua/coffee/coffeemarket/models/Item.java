package ua.coffee.coffeemarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String localName;
    private Double price;
    private String imagePath;
    private Integer amount;

    public Item(String name, String localName,
                Double price, String imagePath,
                Integer amount) {
        this.name = name;
        this.localName = localName;
        this.price = price;
        this.imagePath = imagePath;
        this.amount = amount;
    }
}
