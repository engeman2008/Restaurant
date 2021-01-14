package com.company;

import com.company.domain.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

  public static void main(String[] args) {
    List<Dish> menu = Arrays.asList(
      new Dish("pork", false, 800, Dish.Type.MEAT),
      new Dish("beef", false, 700, Dish.Type.MEAT),
      new Dish("chicken", false, 400, Dish.Type.MEAT),
      new Dish("french fries", true, 530, Dish.Type.OTHER),
      new Dish("rice", true, 350, Dish.Type.OTHER),
      new Dish("season fruit", true, 120, Dish.Type.OTHER),
      new Dish("pizza", true, 550, Dish.Type.OTHER),
      new Dish("prawns", false, 300, Dish.Type.FISH),
      new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    List<String> threeHighCaloriesNames =
      menu.stream()
        .filter(dish -> dish.getCalories() > 300)
        .map(Dish::getName)
        .limit(3)
        .collect(toList());

    System.out.println(threeHighCaloriesNames);

    //Traversable only once
    List<String> title = Arrays.asList("Modern", "Java", "In", "Action");
    Stream<String> s = title.stream();
    s.forEach(System.out::println);
//      s.forEach(System.out::println); will generate compilation error

    //External vs Internal iterations
    List<String> names1 = new ArrayList<>();
    /* 1 - External iteration with for each */
    for(Dish dish: menu){
      names1.add(dish.getName());
    }
    /* 2 - External iteration using an iterator */
//    List<String> names2 = new ArrayList<>();
//    Iterator<String> iterator = menu.iterator();
//    while (iterator.hasNext()){
//      Dish dish = iterator.next();
//      names2.addAll(dish.getName());
//    }
//
    /* 3 - Stream internal iteration */
    List<String> names3 = menu.stream()
      .map(Dish::getName)
      .collect(toList());

    //intermediate operations try
    List<String> names4 = menu.stream()
      .filter(dish -> {
        System.out.println("Filter " + dish.getName());
        return dish.getCalories() > 300;
      })
      .map(dish -> {
        System.out.println("Map " + dish.getName() );
        return dish.getName();
      })
      .limit(3)
      .collect(toList());
  }
}
