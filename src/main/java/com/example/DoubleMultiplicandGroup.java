package com.example;

public class DoubleMultiplicandGroup implements MultiplicandGroup<Double, Double> {

    @Override
    public Double multiply(Double e1, Double e2) {
        return e1 * e2;
    }

    @Override
    public Double unit() {
        return 0.0;
    }
}
