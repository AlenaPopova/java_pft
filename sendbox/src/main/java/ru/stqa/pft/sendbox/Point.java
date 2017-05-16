package ru.stqa.pft.sendbox;

/**
 * Created by Алёна on 15.05.2017.
 */
public class Point {
double x;
double y;

public Point (double x, double y){ //создаем конструктор, имя конструктора = имени класса
  this.x = x;
  this.y = y;

}

  public double distance( Point p2){ // формула рассчета расстояния между точками
  return Math.sqrt((Math.pow(p2.x-this.x,2))+(Math.pow(p2.y-this.y,2)));
  }
}
