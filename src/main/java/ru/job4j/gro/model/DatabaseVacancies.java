package ru.job4j.gro.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "databaseVacancies")
public class DatabaseVacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancyList = new ArrayList<>();

    public void addVacancy(Vacancy vacancy) {
        this.vacancyList.add(vacancy);
    }

    public DatabaseVacancies() {
    }

    public static DatabaseVacancies of(String name) {
        DatabaseVacancies databaseVacancies = new DatabaseVacancies();
        databaseVacancies.name = name;
        databaseVacancies.active = true;
        return databaseVacancies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DatabaseVacancies that = (DatabaseVacancies) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DatabaseVacancies{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", active=" + active
                + ", vacancyList=" + vacancyList
                + '}';
    }
}
