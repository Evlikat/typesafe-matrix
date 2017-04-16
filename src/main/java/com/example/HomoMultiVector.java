package com.example;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Vector with homogeneous element multiplication.
 *
 * @param <ELEM> element type
 * @param <N>    size
 */
public class HomoMultiVector<ELEM, N extends Vectors> extends VectorImpl<ELEM, ELEM, N> {

    public HomoMultiVector(
            MultiplicandGroup<ELEM, ELEM> env,
            Group<ELEM> elementGroup,
            N size,
            List<ELEM> elements) {
        super(env, elementGroup, elementGroup, size, elements);
    }

    public <M_RES, K extends Vectors> HomoMultiVector<ELEM, K> multiply(Matrix<ELEM, M_RES, N, K> matrix) {
        List<ELEM> newElements = IntStream.range(0, this.getSize().size())
                .mapToObj(i -> this.multiply(matrix.getCol(i)))
                .collect(toList());
        return new HomoMultiVector<>(
                env,
                elementGroup,
                matrix.getColumns(),
                newElements
        );
    }
}
