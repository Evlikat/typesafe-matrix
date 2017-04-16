package com.example;

import java.util.List;

public class DoubleVector<N extends Vectors> extends VectorImpl<Double, Double, N> {

    public DoubleVector(N vectorChar,
                        List<Double> elements) {
        super(new DoubleMultiplicandGroup(), new DoubleGroup(), new DoubleGroup(), vectorChar, elements);
    }
}
