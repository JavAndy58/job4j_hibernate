package ru.job4j.gro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import javax.persistence.Query;

public class HbmRun {

    public static void main(String[] args) {
        SpringApplication.run(HbmRun.class, args);


        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            /*
            Candidate one = new Candidate("Alex", 2.5, 150000);
            Candidate two = new Candidate("Nikolay", 1.5, 100000);
            Candidate three = new Candidate("Nikita", 5, 350000);
            session.save(one);
            session.save(two);
            session.save(three);
             */

            Query query = session.createQuery("from Candidate");
            for (Object candidate : ((org.hibernate.query.Query<?>) query).list()) {
                System.out.println(candidate);
            }

            /*
            Query query = session.createQuery("from Candidate c where c.id = :fId");
            query.setParameter("fId", 1);
            System.out.println(((org.hibernate.query.Query<?>) query).uniqueResult());
            */
            /*
            Query query = session.createQuery("from Candidate c where c.name = :fName");
            query.setParameter("fName", "Nikita");
            System.out.println(((org.hibernate.query.Query<?>) query).uniqueResult());
            */
            /*
            session.createQuery("update Candidate c set c.name = :newName, c.experience = :newExperience, c.salary =:newSalary where c.id = :fId")
                            .setParameter("newName", "Andrey")
                            .setParameter("newExperience", 0)
                            .setParameter("newSalary", 50000)
                            .setParameter("fId", 2)
                            .executeUpdate();
            */

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }
}
