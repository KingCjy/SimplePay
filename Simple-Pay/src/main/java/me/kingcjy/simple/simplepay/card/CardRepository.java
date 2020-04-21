package me.kingcjy.simple.simplepay.card;

import me.kingcjy.simple.simplepay.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUser(User user);
}
