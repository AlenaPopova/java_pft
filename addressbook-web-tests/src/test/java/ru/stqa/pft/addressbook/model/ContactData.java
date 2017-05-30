package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String surname;
  private final String adres;
  private final String mail;
  private String group;

  public ContactData(String name, String surname, String adres, String mail, String group) {
    this.name = name;
    this.surname = surname;
    this.adres = adres;
    this.mail = mail;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getAdres() {
    return adres;
  }

  public String getMail() {
    return mail;
  }

  public String getGroup() {
    return group;
  }
}
