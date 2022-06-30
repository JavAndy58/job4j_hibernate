package ru.job4j.gro.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "model_cars")
public class ModelCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_cars_id")
    private BrandCar brandCar;

    public ModelCar() {
    }

    public static ModelCar of(String name, BrandCar brandCar) {
        ModelCar modelCar = new ModelCar();
        modelCar.name = name;
        modelCar.brandCar = brandCar;
        return modelCar;
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

    public BrandCar getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(BrandCar brandCar) {
        this.brandCar = brandCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelCar modelCar = (ModelCar) o;
        return id == modelCar.id && Objects.equals(name, modelCar.name)
                && Objects.equals(brandCar, modelCar.brandCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brandCar);
    }

    @Override
    public String toString() {
        return "ModelCar{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", brandCar=" + brandCar
                + '}';
    }
}
