package mykytenko.car;

import java.io.Serializable;

public class Engine implements Serializable {
    private String name;
    private int maxRPM;

    public Engine(String name, int maxRPM) {
        this.name = name;
        this.maxRPM = maxRPM;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                ", maxRPM=" + maxRPM +
                '}';
    }
}
