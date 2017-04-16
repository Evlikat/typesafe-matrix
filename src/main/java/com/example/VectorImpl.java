package com.example;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class VectorImpl<ELEM, M_RES, N extends Vectors> implements Vector<ELEM, M_RES, N> {

    // multiply elements
    protected final MultiplicandGroup<ELEM, M_RES> env;
    // add elements
    protected final Group<ELEM> elementGroup;
    protected final Group<M_RES> multipliedElementGroup;
    protected final List<ELEM> elements;
    protected final N size;

    public VectorImpl(MultiplicandGroup<ELEM, M_RES> env,
                      Group<ELEM> elementGroup,
                      Group<M_RES> multipliedElementGroup,
                      N size,
                      List<ELEM> elements) {
        this.env = env;
        this.elementGroup = elementGroup;
        this.multipliedElementGroup = multipliedElementGroup;
        this.size = size;
        this.elements = elements;
    }

    @Override
    public N getSize() {
        return size;
    }

    @Override
    public MultiplicandGroup<ELEM, M_RES> getEnv() {
        return env;
    }

    @Override
    public ELEM get(int index) {
        return elements.get(index);
    }

    @Override
    public Vector<ELEM, M_RES, N> add(Vector<ELEM, M_RES, N> otherVector) {
        List<ELEM> elements = IntStream.range(0, size.size())
                .mapToObj(i -> elementGroup.add(get(i), otherVector.get(i)))
                .collect(toList());
        return new VectorImpl<>(env, elementGroup, multipliedElementGroup, size, elements);
    }

    @Override
    public M_RES multiply(Vector<ELEM, M_RES, N> otherVector) {
        return IntStream.range(0, size.size())
                .mapToObj(i -> env.multiply(get(i), otherVector.get(i)))
                .reduce(multipliedElementGroup.zero(), multipliedElementGroup::add);
    }

    @Override
    public void forEach(Consumer<ELEM> consumer) {
        elements.forEach(consumer);
    }

    @Override
    public Stream<ELEM> elements() {
        return elements.stream();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VectorImpl<?, ?, ?> vector = (VectorImpl<?, ?, ?>) o;

        return elements.equals(vector.elements);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }
}
