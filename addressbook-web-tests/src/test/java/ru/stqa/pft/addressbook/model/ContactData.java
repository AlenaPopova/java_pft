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

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", mail='" + mail + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
    return mail != null ? mail.equals(that.mail) : that.mail == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    result = 31 * result + (mail != null ? mail.hashCode() : 0);
    return result;
  }
}
