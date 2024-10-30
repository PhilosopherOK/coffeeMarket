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
public class EmailList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String subject;
    private String text;

    public EmailList(String email, String subject, String text) {
        this.email = email;
        this.subject = subject;
        this.text = text;
    }
}
