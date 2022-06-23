package ru.job4j.gro;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import ru.job4j.gro.model.BrandAuto;
import ru.job4j.gro.model.ModelAuto;

public class HbmRunAuto {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            ModelAuto one = ModelAuto.of("Focus");
            ModelAuto two = ModelAuto.of("Mondeo");
            ModelAuto three = ModelAuto.of("Transit");
            ModelAuto four = ModelAuto.of("Mustang");
            ModelAuto five = ModelAuto.of("Fusion");
            BrandAuto ford = BrandAuto.of("Ford");
            ford.addModel(one);
            ford.addModel(two);
            ford.addModel(three);
            ford.addModel(four);
            ford.addModel(five);
            session.save(ford);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
