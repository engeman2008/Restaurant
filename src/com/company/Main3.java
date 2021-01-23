package com.company;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main3 {
  public static void main(String args[]) {

    //Pythagorean
    Stream<int[]> pythagoreanTriple = IntStream.rangeClosed(1, 100).boxed()
      .flatMap(a ->
        IntStream.rangeClosed(1, 100)
          .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
          .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
      );

    pythagoreanTriple.limit(5)
      .forEach(t -> System.out.println(t[0] + " , " + t[1] + " , " + t[2]));

    /* Enhancing the solution, generate all triples then filter */
    Stream<double[]> pythagoreanTriple2 = IntStream.rangeClosed(1, 100).boxed()
      .flatMap(a ->
        IntStream.rangeClosed(1, 100)
          .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
          .filter(t -> t[2] % 1 == 0)
      );

    pythagoreanTriple2.limit(5)
      .forEach(t -> System.out.println(t[0] + " , " + t[1] + " , " + t[2]));

    /**********Stream from values*********/
    Stream<String> stream = Stream.of("Moden", "Java", "In", "Action");
    stream.map(String::toUpperCase).forEach(System.out::println);

    /**********Get EMpty Stream*********/
    Stream<String> emptyStream = Stream.empty();

    /**********Stream from nullable*********/
    String homeValue = System.getProperty("home");
    Stream<String> homeValueString = homeValue == null ? Stream.empty() : Stream.of(homeValue);
    /*OR better*/
    Stream<String> homeValueString2 = Stream.ofNullable(System.getProperty("home"));

    /**********Stream from arrays*********/
    int[] numbers = {2, 3, 5, 7, 11, 13};
    int sum = Arrays
      .stream(numbers)
      .sum();
    /**********Stream from files*********/
    try (Stream<String> lines = Files.lines(
      Paths.get("com/company/data.txt", String.valueOf(Charset.defaultCharset()))
    )) {
      lines.flatMap(line -> Arrays.stream(line.split(" ")))
        .distinct()
        .count();
    } catch (IOException e) {
      System.out.println("error");
    }
    /**********Stream from functions - create infinite stream*********/
    //stream create all even numbers
    Stream.iterate(0, n -> n + 2)
      .limit(10)
      .forEach(System.out::println);

    //Fibonacci tuples series
    Stream.iterate(
      new int[]{0, 1},
      t -> new int[]{t[1], t[0] + t[1]}
    )
      .limit(20)
      .forEach(t -> System.out.println("{" + t[0] + "," + t[1] + "}"));

    //to print normal Fibonacci series
    Stream.iterate(
      new int[]{0, 1},
      t -> new int[]{t[1], t[0] + t[1]}
    )
      .map(t -> t[0])
      .limit(20)
      .forEach(System.out::println);

  }
}
