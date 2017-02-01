package mykytenko.car;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Serializable{
    private Wheel[] wheels;
    private Engine engine;
    private String name;

    public Car(String name, Wheel[] wheels, Engine engine) {
        this.wheels = wheels;
        this.engine = engine;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "wheels=" + Arrays.toString(wheels) +
                ", engine=" + engine +
                ", name='" + name + '\'' +
                '}';
    }
}
