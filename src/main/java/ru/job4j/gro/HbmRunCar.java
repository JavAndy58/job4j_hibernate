package ru.job4j.gro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.gro.model.BrandCar;
import ru.job4j.gro.model.ModelCar;

import java.util.ArrayList;
import java.util.List;

public class HbmRunCar {

    public static void main(String[] args) {
        List<BrandCar> list;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            /*
            BrandCar ford = BrandCar.of("Ford");
            BrandCar mazda = BrandCar.of("Mazda");
            ModelCar one = ModelCar.of("Focus", ford);
            ModelCar two = ModelCar.of("Mondeo", ford);
            ModelCar four = ModelCar.of("3", mazda);
            ModelCar five = ModelCar.of("6", mazda);
            session.save(ford);
            session.save(mazda);
            session.save(one);
            session.save(two);
            session.save(four);
            session.save(five);
             */

            list = session.createQuery("from BrandCar").list();
            for (BrandCar brandCar : list) {
                for (ModelCar modelCar : brandCar.getModelCarList()) {
                    System.out.println(modelCar);
                }
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
