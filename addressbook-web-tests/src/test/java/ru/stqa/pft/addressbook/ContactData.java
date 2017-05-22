package ru.stqa.pft.addressbook;

public class ContactData {
  private final String name;
  private final String surname;
  private final String adres;
  private final String mail;

  public ContactData(String name, String surname, String adres, String mail) {
    this.name = name;
    this.surname = surname;
    this.adres = adres;
    this.mail = mail;
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
}
