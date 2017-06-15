package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by popovaa on 15.06.2017.
 */
public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]); //кол-во контактов
        File file = new File(args[1]); // путь к файлу

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file); //открываем файл для записи
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s\n", contact.getName(), contact.getSurname()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {

        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withName(String.format("Harry %s", i))
                    .withSurname(String.format("Potter %s", i)));
        }
        return contacts;
    }

}

