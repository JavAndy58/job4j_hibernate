package ru.job4j.gro.model;

import org.dom4j.rule.Mode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "brands")
public class BrandAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelAuto> models = new ArrayList<>();

    public BrandAuto() {
    }

    public BrandAuto(int id, String name) {
        this.id = id;
        this.name = name;
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

    public void addModel(ModelAuto modelAuto) {
        this.models.add(modelAuto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BrandAuto brandAuto = (BrandAuto) o;
        return id == brandAuto.id && Objects.equals(name, brandAuto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "BrandAuto{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
