package mykytenko.car;

import java.io.Serializable;

public class Wheel implements Serializable {
    private String wheelPosition;
    private double diameter;

    public Wheel(String wheelPosition, double diameter) {
        this.wheelPosition = wheelPosition;
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                wheelPosition +
                " wheel, " + diameter +
                "\"}";
    }
}
