package ru.stqa.pft.sendbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    //System.out.println("Hello World!");

   // Square s = new Square(7); //s - переменная, New Square - создание нового объекта, в скобках значение параметра

    //System.out.println("Площадь квадрата со стороной " + s.l + "=" + area(s));


    //Rectangle r = new Rectangle(4,6);
   // r.a = 3.2; //заполнение атрибутов значениями
   // r.b = 6;
 // System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));

    ////////////////////////////////Домашнее задание №2
    Point p1 = new Point(2,5);
    Point p2 = new Point(6,6);
    System.out.println(p1.distance(p2));
  }
///////////////////////////////////////////////////
 // public static double area(Square s) {
  //  return s.l * s.l;
  //}

 // public static double area(Rectangle r) {
  //return r.a*r.b;
 // }

}