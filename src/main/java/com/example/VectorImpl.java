package com.example;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class VectorImpl<E, R, N extends Vectors> implements Vector<E, R, N> {

    // multiply elements
    private final MultiplicandGroup<E, R> env;
    // add elements
    private final Group<E> elementGroup;
    private final Group<R> multipliedElementGroup;
    private final List<E> v;
    private final N vectorChar;

    public VectorImpl(MultiplicandGroup<E, R> env,
                      Group<E> elementGroup,
                      Group<R> multipliedElementGroup,
                      N vectorChar,
                      List<E> v) {
        this.env = env;
        this.elementGroup = elementGroup;
        this.multipliedElementGroup = multipliedElementGroup;
        this.vectorChar = vectorChar;
        this.v = v;
    }

    @Override
    public N getVectorChar() {
        return vectorChar;
    }

    @Override
    public MultiplicandGroup<E, R> getEnv() {
        return env;
    }

    @Override
    public E get(int index) {
        return v.get(index);
    }

    @Override
    public Vector<E, R, N> add(Vector<E, R, N> otherVector) {
        List<E> elements = IntStream.range(0, vectorChar.size())
                .mapToObj(i -> elementGroup.add(get(i), otherVector.get(i)))
                .collect(toList());
        return new VectorImpl<>(env, elementGroup, multipliedElementGroup, vectorChar, elements);
    }

    @Override
    public R multiply(Vector<E, R, N> otherVector) {
        return IntStream.range(0, vectorChar.size())
                .mapToObj(i -> env.multiply(get(i), otherVector.get(i)))
                .reduce(multipliedElementGroup.zero(), multipliedElementGroup::add);
    }

    @Override
    public <K extends Vectors> Vector<E, R, K> multiply(Matrix<E, R, N, K> matrix) {
        return null;
    }

    @Override
    public void forEach(Consumer<E> consumer) {
        v.forEach(consumer);
    }

    @Override
    public Stream<E> elements() {
        return v.stream();
    }

    @Override
    public String toString() {
        return v.toString();
    }
}
