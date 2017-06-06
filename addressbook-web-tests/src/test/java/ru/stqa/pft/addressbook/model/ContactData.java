package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id;
  private final String name;
  private final String surname;
  private final String adres;
  private final String mail;
  private String group;

  public ContactData(String name, String surname, String adres, String mail, String group) {
    this.id = 0;
    this.name = name;
    this.surname = surname;
    this.adres = adres;
    this.mail = mail;
    this.group = group;
  }

  public ContactData(int id, String name, String surname, String adres, String mail, String group) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.adres = adres;
    this.mail = mail;
    this.group = group;
  }

  public int getId() {
    return id;
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

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return surname != null ? surname.equals(that.surname) : that.surname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    return result;
  }

  public void setId(int id) {
    this.id = id;
  }
}
