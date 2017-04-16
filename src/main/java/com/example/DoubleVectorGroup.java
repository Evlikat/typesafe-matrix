package com.example;

public class DoubleVectorGroup<N extends Vectors> extends VectorGroup<Double, Double, N> {

    public DoubleVectorGroup(N size) {
        super(size);
    }

    @Override
    public Vector<Double, Double, N> add(Vector<Double, Double, N> e1, Vector<Double, Double, N> e2) {
        return e1.add(e2);
    }

    @Override
    public Vector<Double, Double, N> zero() {
        return new DoubleZeroVector<>(getSize());
    }
}
