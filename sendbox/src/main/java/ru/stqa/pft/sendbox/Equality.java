package ru.stqa.pft.sendbox;

/**
 * Created by Алёна on 29.05.2017.
 */
public class Equality {
  public static void main(String[] args){
    String s1 = "firefox";
    String s2 = new String(s1);
    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
  }
}
