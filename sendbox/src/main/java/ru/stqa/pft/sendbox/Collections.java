package ru.stqa.pft.sendbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Алёна on 04.06.2017.
 */
public class Collections {

  public static void main(String [] args){

    String[]langs = new String[4]; // создание массива, указывается тип элементов, находящихся в массиве, в данном случае это String
    // или создать массив можно так: String[]langs = {"Java","PHP","C#","C++"}
    langs[0] = "Java";
    langs[1] = "PHP";
    langs[2] = "C#";
    langs[3] = "C++";

    for (String l : langs){ //перебор элементов коллекции, l принимает все значения коллекции по порядку
      System.out.println("Хочу выучить "+ l);
    }

//Создание списка
    List<String> languages = Arrays.asList("Java","PHP","C#","C++");
    for (String l : languages){ //перебор элементов коллекции, l принимает все значения коллекции по порядку
      System.out.println("Хочу выучить "+ l);
    }
  }
}
