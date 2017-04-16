package com.example;

public abstract class VectorGroup<ELEM, M_RES, N extends Vectors> implements Group<Vector<ELEM, M_RES, N>> {

    private final N size;

    public VectorGroup(N size) {
        this.size = size;
    }

    public N getSize() {
        return size;
    }

    @Override
    public Vector<ELEM, M_RES, N> add(Vector<ELEM, M_RES, N> e1, Vector<ELEM, M_RES, N> e2) {
        return e1.add(e2);
    }
}
