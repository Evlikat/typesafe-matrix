package com.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Of3 of3 = new Of3();
        Of2 of2 = new Of2();
        DoubleMultiplicandGroup d = new DoubleMultiplicandGroup();
        DoubleVectorMultiplicandGroup<Of3> dv3me = new DoubleVectorMultiplicandGroup<>(of3);
        DoubleVectorMultiplicandGroup<Of2> dv2me = new DoubleVectorMultiplicandGroup<>(of2);
        DoubleGroup doubleGroup = new DoubleGroup();
        DoubleVectorGroup<Of3> dv3g = new DoubleVectorGroup<>(of3);
        DoubleVectorGroup<Of2> dv2g = new DoubleVectorGroup<>(of2);

        Matrix<Double, Double, Of2, Of3> m2x3 = new MatrixImpl<>(
                dv3me,
                dv3g,
                doubleGroup,
                doubleGroup,
                of2,
                of3,
                new VectorImpl<>(
                        dv3me,
                        dv3g,
                        doubleGroup,
                        of2,
                        Arrays.asList(
                                new VectorImpl<>(d, doubleGroup, doubleGroup, of3, Arrays.asList(3.0, 1.0, 2.0)),
                                new VectorImpl<>(d, doubleGroup, doubleGroup, of3, Arrays.asList(0.0, -1.0, 4.0))
                        )
                )
        );

        Matrix<Double, Double, Of3, Of2> m3x2 = new MatrixImpl<>(
                dv2me,
                dv2g,
                doubleGroup,
                doubleGroup,
                of3,
                of2,
                new VectorImpl<>(
                        dv2me,
                        dv2g,
                        doubleGroup,
                        of3,
                        Arrays.asList(
                                new VectorImpl<>(d, doubleGroup, doubleGroup, of2, Arrays.asList(-1.0, 1.0)),
                                new VectorImpl<>(d, doubleGroup, doubleGroup, of2, Arrays.asList(2.0, 0.0)),
                                new VectorImpl<>(d, doubleGroup, doubleGroup, of2, Arrays.asList(0.0, 3.0))
                        )
                )
        );

        Matrix<Double, Double, Of3, Of3> multiply = m3x2.multiply(m2x3);
        System.out.println(multiply);
    }
}
