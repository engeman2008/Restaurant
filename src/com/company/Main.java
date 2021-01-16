package com.company;

import com.company.domain.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
    for (Dish dish : menu) {
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
        System.out.println("Map " + dish.getName());
        return dish.getName();
      })
      .limit(3)
      .collect(toList());

    //filter
    List<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(toList());

    //filter unique elements
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

    //Slicing a stream
    //takeWhile -- dropWhile
    List<Dish> specialMenu = Arrays.asList(
      new Dish("season fruit", true, 120, Dish.Type.OTHER),
      new Dish("prawns", false, 300, Dish.Type.FISH),
      new Dish("rice", true, 350, Dish.Type.OTHER),
      new Dish("chicken", false, 400, Dish.Type.MEAT),
      new Dish("french fries", true, 530, Dish.Type.OTHER)
    );

    /* using filter will iterate through the whole stream
     * dishes with calories less than 320 */
    List<Dish> filteredMenu = specialMenu.stream()
      .filter(dish -> dish.getCalories() < 320)
      .collect(toList());

    /* use takeWhile the condition is true, it stops once it has found an element that fails to match
     * dishes with calories less than 320 */
    List<Dish> slicedMenu = specialMenu.stream()
      .takeWhile(dish -> dish.getCalories() < 320)
      .collect(toList());

    /* dropWhile
     * dishes that have greater than 320 calories  */
    List<Dish> slicedMenu2 = specialMenu.stream()
      .dropWhile(dish -> dish.getCalories() < 320)
      .collect(toList());

    //truncate
    // Limit(3) -- first 3 dishes that haev calories greater than 300
    List<Dish> dishes = specialMenu.stream()
      .filter(dish -> dish.getCalories() > 300)
      .limit(3)
      .collect(toList());

    //Skip(2) - discard first 2 elements
    List<Dish> dishes2 = specialMenu.stream()
      .filter(dish -> dish.getCalories() > 300)
      .skip(2)
      .collect(toList());
    //filter first two meat dishes
    List<Dish> meatDishes = menu.stream()
      .filter(dish -> dish.getType() == Dish.Type.MEAT)
      .limit(2)
      .collect(toList());


    /* Mapping - transforming -- creating new version of
     * Extract the name of dishes*/
    List<String> dishNames = menu.stream()
      .map(Dish::getName)
      .collect(toList());

    //given list of words, return a list of the number of chars for each word
    List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
    List<Integer> wordsLength = words.stream()
      .map(String::length)
      .collect(toList());

    //return a list of the number of chars for each dishname, Chaining
    List<Integer> dishNamesLengths = menu.stream()
      .map(Dish::getName)
      .map(String::length)
      .collect(toList());

    //Flattening streams
    /* the stream returned is Stream<String[]> , output is List<String[]>
     * but we need Stream<String> - List<String>*/
    List<String[]> fl1 = words.stream()
      .map(word -> word.split(""))
      .distinct()
      .collect(toList());

    //attempt using map and Arrays.Stream
    /* not work as well */
//    List<String> fl2= words.stream()
//      .map(word -> word.split(""))
//      .map(Arrays::stream)
//      .distinct()
//      .collect(toList());

    //use flatmap
    /* flatmap map each array with the xontent of that stream
    all streams to single stream
    * from Stream<String[]> to Stream<String> */
    List<String> fm = words.stream()
      .map(word -> word.split(""))
      .flatMap(Arrays::stream)
      .distinct()
      .collect(toList());

    /* given numbers -> get square for each number*/
    List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> squared = nums.stream()
      .map(n -> n * n)
      .collect(toList());

    /* given two lists > return all pairs of numbers*/
    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4);
    List<int[]> pairs = numbers1.stream()
      .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
      .collect(toList());


    //extend prev example - get pairs whose sum is divisable by 3
    numbers1.stream()
      .flatMap(i ->
        numbers2.stream()
          .filter(j -> i + j % 3 == 0).
          map(j -> new int[]{i, j})
      )
      .collect(toList());

    /* Matching - anyMatch - allMatch - noneMatch */

    menu.stream().anyMatch(Dish::isVegetarian); //check whether the menu has vegetarian option
    menu.stream().allMatch(dish -> dish.getCalories() < 1000); //check whether the menu is healthy, all calo less than 1000
    menu.stream().noneMatch(dish -> dish.getCalories() >= 1000); //the same as the previous example

    /* find -
    findAny return Optional<T> */
    menu.stream()
      .filter(Dish::isVegetarian)
      .findAny()
      .ifPresent(dish -> System.out.println(dish.getName()));

    /* findFirst -- the same as findAny , findFirst is used in parallel*/
    List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
    Optional<Integer> firstElem = someNumbers.stream()
      .map(n -> n*n)
      .filter(n -> n%3 == 0)
      .findFirst();

  }


}
