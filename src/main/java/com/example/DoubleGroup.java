package com.example;

public class DoubleGroup implements Group<Double> {
    @Override
    public Double add(Double e1, Double e2) {
        return e1 + e2;
    }

    @Override
    public Double zero() {
        return 0.0;
    }
}
