package ru.job4j.gro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.gro.model.Candidate;
import ru.job4j.gro.model.DatabaseVacancies;
import ru.job4j.gro.model.Vacancy;

public class HbmRunVacancy {
    public static void main(String[] args) {
        Candidate rsl = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*
            Vacancy vakOne = Vacancy.of("Java", 0.5, 50);
            Vacancy vakTwo = Vacancy.of("Php", 1, 45);
            DatabaseVacancies databaseVacanciesOne = DatabaseVacancies.of("hh.ru");
            databaseVacanciesOne.addVacancy(vakOne);
            databaseVacanciesOne.addVacancy(vakTwo);
            Candidate candidateOne = Candidate.of("Andy", 0.5, 15);
            candidateOne.setDatabaseVacancies(databaseVacanciesOne);
            session.save(databaseVacanciesOne);
            session.save(candidateOne);

             */
            rsl = session.createQuery(
                    "select distinct can from Candidate can "
                            + "join fetch can.databaseVacancies d "
                            + "join fetch d.vacancyList v "
                            + "where can.id = :sId", Candidate.class
            ).setParameter("sId", 1).uniqueResult();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl);
    }
}
