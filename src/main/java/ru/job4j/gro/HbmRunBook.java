package ru.job4j.gro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.gro.model.Author;
import ru.job4j.gro.model.Book;

public class HbmRunBook {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*
            Book book1 = Book.of("The Auto");
            Book book2 = Book.of("Lake");
            Book book3 = Book.of("Travel");

            Author one = Author.of("Andrey");
            one.getBooks().add(book1);

            Author two = Author.of("Ivan");
            two.getBooks().add(book2);
            two.getBooks().add(book3);

            Author three = Author.of("Petr");
            three.getBooks().add(book2);
            three.getBooks().add(book3);

            session.save(one);
            session.save(two);
            session.save(three);

             */

            Author author = session.get(Author.class, 2);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
