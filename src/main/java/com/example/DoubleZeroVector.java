package com.example;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DoubleZeroVector<N extends Vectors> implements Vector<Double, Double, N> {

    private final N size;

    public DoubleZeroVector(N size) {
        this.size = size;
    }

    @Override
    public N getSize() {
        return size;
    }

    @Override
    public MultiplicandGroup<Double, Double> getEnv() {
        return null;
    }

    @Override
    public Double get(int index) {
        return 0.0;
    }

    @Override
    public Vector<Double, Double, N> add(Vector<Double, Double, N> otherVector) {
        return otherVector;
    }

    @Override
    public Double multiply(Vector<Double, Double, N> otherVector) {
        return 0.0;
    }

    @Override
    public void forEach(Consumer<Double> consumer) {
        IntStream.range(0, size.size()).forEach(i -> consumer.accept(0.0));
    }

    @Override
    public Stream<Double> elements() {
        return null;
    }
}
