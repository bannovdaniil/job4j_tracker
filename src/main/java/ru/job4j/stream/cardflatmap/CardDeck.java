package ru.job4j.stream.cardflatmap;

import ru.job4j.stream.cardflatmap.model.Card;
import ru.job4j.stream.cardflatmap.model.CardValue;
import ru.job4j.stream.cardflatmap.model.Suit;

import java.util.List;
import java.util.stream.Stream;

public class CardDeck {
    public static void main(String[] args) {
        List<Card> cards = Stream.of(Suit.values())
                .flatMap(
                        suit -> Stream.of(CardValue.values())
                                .map(cardValue -> new Card(suit, cardValue))
                ).toList();

        cards.forEach(System.out::println);
    }
}
