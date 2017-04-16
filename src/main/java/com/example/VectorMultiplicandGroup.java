package com.example;

public abstract class VectorMultiplicandGroup<E, EM, VM, N extends Vectors> implements MultiplicandGroup<Vector<E, EM, N>, VM> {

    private final N size;

    public VectorMultiplicandGroup(N size) {
        this.size = size;
    }

    public abstract <M extends Vectors> VectorMultiplicandGroup<E, EM, VM, M> resize(M newSize);

    public N getSize() {
        return size;
    }
}
