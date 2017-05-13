package ru.stqa.pft.sendbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    System.out.println("Hello World!");
    double l = 5.5;
    System.out.println("Площадь квадрата" + "=" + area(l));

    double a = 4.1;
    double b = 3.2;
    System.out.println("Площадь прямоугольника" + "=" + area(a,b));
  }

  public static double area(double l) {
    return l * l;
  }

  public static double area(double a, double b) {
    return a*b;
  }

}