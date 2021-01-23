package com.company;

import com.company.domain.Trader;
import com.company.domain.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Main2 {
  public static void main(String[] args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brain = new Trader("Brain", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
      new Transaction(brain, 2011, 300),
      new Transaction(raoul, 2012, 1000),
      new Transaction(raoul, 2011, 400),
      new Transaction(mario, 2012, 710),
      new Transaction(mario, 2012, 700),
      new Transaction(alan, 2012, 950)
    );
    /* 1- find all transactions in 2011 , sort them by value small to high*/
//    List<Transaction> q1 = transactions.stream()
//      .filter(t -> t.getYear() == 2011)
//      .sorted(comparing(Transaction::getValue))
//      .collect(toList());

    /* 2- what are the unique cities where the traders work*/
    List<String> uniqueCities = transactions.stream()
      .map(t -> t.getTrader().getCity())
      .distinct()
      .collect(toList());

    /* 3- Find all traders from Cambridge and sort them by name*/
    List<Trader> q3 = transactions.stream()
      .map(Transaction::getTrader)
      .filter(trader -> trader.getCity().equals("Cambridge"))
      .distinct()
      .sorted(comparing(Trader::getName))
      .collect(toList());
    /* 4- Return a String of all traders names sorted alphabetically*/
    String q4 = transactions.stream()
      .map(transaction -> transaction.getTrader().getName())
      .distinct()
      .sorted()
      .reduce("", (n1, n2) -> n1 + " " + n2); // or collect(joining())

    //or map(t -> t.getTrader.getName)
    /* 5- Are any traders based in Milan*/
    boolean q5 = transactions.stream()
      .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    /* 6- Print the values of all transactions from the traders living in Cambridge*/
    transactions.stream()
      .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
      .map(Transaction::getValue)
      .forEach(System.out::println);
    /* 7- what is the highest value of all the transactions*/
    Optional<Integer> maxValue = transactions.stream()
      .map(Transaction::getValue)
      .reduce(Integer::max);
    /* 8- Find the transaction with the smallest value*/
    Optional<Transaction> tr= transactions.stream().reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
  }
}
