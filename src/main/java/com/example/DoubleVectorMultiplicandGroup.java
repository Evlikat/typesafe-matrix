package com.example;

public class DoubleVectorMultiplicandGroup<N extends Vectors> extends VectorMultiplicandGroup<Double, Double, Double, N> {

    public DoubleVectorMultiplicandGroup(N size) {
        super(size);
    }

    @Override
    public Double multiply(Vector<Double, Double, N> e1, Vector<Double, Double, N> e2) {
        return e1.multiply(e2);
    }

    @Override
    public <M extends Vectors> DoubleVectorMultiplicandGroup<M> resize(M newSize) {
        return new DoubleVectorMultiplicandGroup<M>(newSize);
    }

    @Override
    public Vector<Double, Double, N> unit() {
        return new DoubleZeroVector<>(getSize());
    }
}
