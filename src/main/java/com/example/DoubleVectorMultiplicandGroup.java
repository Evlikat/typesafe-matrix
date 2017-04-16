package com.example;

public class DoubleVectorMultiplicandGroup<N extends Vectors> implements MultiplicandGroup<Vector<Double, Double, N>, Double> {

    private final N size;

    public DoubleVectorMultiplicandGroup(N size) {
        this.size = size;
    }

    @Override
    public Double multiply(Vector<Double, Double, N> e1, Vector<Double, Double, N> e2) {
        return e1.multiply(e2);
    }

    @Override
    public Vector<Double, Double, N> unit() {
        return new DoubleZeroVector<>(size);
    }
}
