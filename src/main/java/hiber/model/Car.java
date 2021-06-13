package hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Car {

    @Id
    int series;

    @Column
    String model;

    @OneToOne(mappedBy = "car")
    private User user;

    public Car() {}

    public void setUser(User user) {
        this.user = user;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public String getModel() {
        return model;
    }

    public User getUser() {
        return user;
    }

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, model);
    }

    @Override
    public String toString() {
        return "Car: {" +
                "series=" + series +
                ", model='" + model + '\'' +
                '}';
    }
}
