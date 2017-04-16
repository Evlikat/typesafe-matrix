package com.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HomoMultiVectorTest {
    private Of3 of3 = new Of3();
    private Of2 of2 = new Of2();
    private DoubleMultiplicandGroup d = new DoubleMultiplicandGroup();
    private DoubleVectorMultiplicandGroup<Of3> dv3me = new DoubleVectorMultiplicandGroup<>(of3);
    private DoubleVectorMultiplicandGroup<Of2> dv2me = new DoubleVectorMultiplicandGroup<>(of2);
    private DoubleGroup dg = new DoubleGroup();
    private DoubleVectorGroup<Of3> dv3g = new DoubleVectorGroup<>(of3);
    private DoubleVectorGroup<Of2> dv2g = new DoubleVectorGroup<>(of2);

    @Test
    public void shouldMultiplyMatrix() throws Exception {
        Matrix<Double, Double, Of3, Of3> m3x3 = new MatrixImpl<>(
                dv3me,
                dv3g,
                dg,
                dg,
                of3,
                of3,
                new VectorImpl<>(
                        dv3me,
                        dv3g,
                        dg,
                        of3,
                        Arrays.asList(
                                new VectorImpl<>(d, dg, dg, of3, Arrays.asList(2.0, 4.0, 0.0)),
                                new VectorImpl<>(d, dg, dg, of3, Arrays.asList(-2.0, 1.0, 3.0)),
                                new VectorImpl<>(d, dg, dg, of3, Arrays.asList(-1.0, 0.0, 1.0))
                        )
                )
        );
        HomoMultiVector<Double, Of3> v3 = new HomoMultiVector<>(d, dg, of3, Arrays.asList(1.0, 2.0, -1.0));

        Vector<Double, Double, Of3> actualV3 = v3.multiply(m3x3);
        HomoMultiVector<Double, Of3> expectedV3 = new HomoMultiVector<>(d, dg, of3, Arrays.asList(-1.0, 6.0, 5.0));
        assertEquals(expectedV3, actualV3);
    }
}