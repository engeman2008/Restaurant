package com.company;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main3 {
  public static void main(String args[]) {

    //Pythagorean
   Stream<int []> pythagoreanTriple = IntStream.rangeClosed(1,100).boxed()
      .flatMap(a ->
        IntStream.rangeClosed(1, 100)
        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
      );

   pythagoreanTriple.limit(5)
     .forEach(t -> System.out.println(t[0] + " , " + t[1] + " , " + t[2]));
  }
}
