package com.example;

import java.util.function.Consumer;
import java.util.stream.Stream;

public interface Vector<ELEM, MUL_RES, N extends Vectors> {

    N getVectorChar();

    MultiplicandGroup<ELEM, MUL_RES> getEnv();

    ELEM get(int index);

    Vector<ELEM, MUL_RES, N> add(Vector<ELEM, MUL_RES, N> otherVector);

    MUL_RES multiply(Vector<ELEM, MUL_RES, N> otherVector);

    <K extends Vectors> Vector<ELEM, MUL_RES, K> multiply(Matrix<ELEM, MUL_RES, N, K> matrix);

    void forEach(Consumer<ELEM> consumer);

    Stream<ELEM> elements();
}
