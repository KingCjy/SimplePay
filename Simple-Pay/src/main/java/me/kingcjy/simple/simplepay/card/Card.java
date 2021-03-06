package me.kingcjy.simple.simplepay.card;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kingcjy.simple.simplepay.domain.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cardNumber;
    private String cvc;

    private String password;

    private LocalDate expiredDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Card(String name, String cardNumber, String cvc, String password, LocalDate expiredDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.password = password;
        this.expiredDate = expiredDate;
    }
}
