package ru.job4j.gro.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "brand_cars")
public class BrandCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "brandCar")
    private List<ModelCar> modelCarList = new ArrayList<>();

    public BrandCar() {
    }

    public static BrandCar of(String name) {
        BrandCar brandCar = new BrandCar();
        brandCar.name = name;
        return brandCar;
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

    public List<ModelCar> getModelCarList() {
        return modelCarList;
    }

    public void setModelCarList(List<ModelCar> modelCarList) {
        this.modelCarList = modelCarList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BrandCar brandCar = (BrandCar) o;
        return id == brandCar.id && Objects.equals(name, brandCar.name)
                && Objects.equals(modelCarList, brandCar.modelCarList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, modelCarList);
    }

    @Override
    public String toString() {
        return "BrandCar{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
