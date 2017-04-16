package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class MatrixImpl<ELEM, M_RES, M extends Vectors, N extends Vectors> implements Matrix<ELEM, M_RES, M, N> {

    // v[v[e]] * v[v[e]]
    private final MultiplicandGroup<Vector<ELEM, ELEM, N>, M_RES> vectorMultiEnv;
    // v[e] + v[e]
    private final VectorGroup<ELEM, ELEM, N> vectorGroup;
    // v[e]*v[e] + v[e]*v[e]
    private final Group<M_RES> multipliedElementGroup;
    // e + e
    private final Group<ELEM> elementGroup;
    private final N cols;
    // matrix
    private final Vector<Vector<ELEM, ELEM, N>, M_RES, M> m;
    // rows in matrix
    private final M rows;

    public MatrixImpl(MultiplicandGroup<Vector<ELEM, ELEM, N>, M_RES> vectorMultiEnv,
                      VectorGroup<ELEM, ELEM, N> vectorGroup,
                      Group<M_RES> multipliedElementGroup,
                      Group<ELEM> elementGroup,
                      M rows,
                      N cols,
                      Vector<Vector<ELEM, ELEM, N>, M_RES, M> m) {
        this.vectorMultiEnv = vectorMultiEnv;
        this.vectorGroup = vectorGroup;
        this.multipliedElementGroup = multipliedElementGroup;
        this.elementGroup = elementGroup;
        this.cols = cols;
        this.m = m;
        this.rows = rows;
    }

    private <K extends Vectors> Vector<Vector<ELEM, ELEM, N>, M_RES, K> fromList(
            List<Vector<ELEM, ELEM, N>> elements,
            K vectors
    ) {
        return new VectorImpl<>(
                vectorMultiEnv,
                vectorGroup,
                multipliedElementGroup,
                vectors,
                elements
        );
    }

    @Override
    public M getRows() {
        return rows;
    }

    @Override
    public N getColumns() {
        return cols;
    }

    @Override
    public VectorGroup<ELEM, ELEM, N> getVectorGroup() {
        return vectorGroup;
    }

    @Override
    public MultiplicandGroup<Vector<ELEM, ELEM, N>, M_RES> getVectorMultiEnv() {
        return vectorMultiEnv;
    }

    @Override
    public Vector<ELEM, ELEM, N> getRow(int i) {
        return m.get(i);
    }

    @Override
    public Vector<ELEM, ELEM, M> getCol(int i) {
        List<ELEM> columnElements = m.elements()
                .map(e -> e.get(i))
                .collect(Collectors.toList());
        return new VectorImpl<>(
                m.get(0).getEnv(),
                elementGroup,
                elementGroup,
                rows,
                columnElements
        );
    }

    @Override
    public Matrix<ELEM, M_RES, M, N> plus(Matrix<ELEM, M_RES, M, N> otherMatrix) {
        List<Vector<ELEM, ELEM, N>> elements = IntStream.range(0, rows.size())
                .mapToObj(i -> vectorGroup.add(getRow(i), otherMatrix.getRow(i)))
                .collect(toList());
        return new MatrixImpl<>(
                vectorMultiEnv,
                vectorGroup,
                multipliedElementGroup,
                elementGroup,
                rows,
                cols,
                fromList(elements, rows)
        );
    }

    @Override
    public <K extends Vectors> Matrix<ELEM, M_RES, M, K> multiply(Matrix<ELEM, M_RES, N, K> otherMatrix) {
        List<Vector<ELEM, ELEM, K>> collected = IntStream.range(0, this.getRows().size())
                .mapToObj(i -> {
                    Vector<ELEM, ELEM, N> row = this.getRow(i);
                    List<ELEM> newRowElements = IntStream.range(0, otherMatrix.getColumns().size())
                            .mapToObj(j -> row.multiply(otherMatrix.getCol(j)))
                            .collect(toList());
                    return new VectorImpl<>(
                            row.getEnv(),
                            elementGroup,
                            elementGroup,
                            otherMatrix.getColumns(),
                            newRowElements
                    );
                })
                .collect(toList());
        Vector<Vector<ELEM, ELEM, K>, M_RES, M> vectors = new VectorImpl<>(
                otherMatrix.getVectorMultiEnv(),
                otherMatrix.getVectorGroup(),
                multipliedElementGroup,
                rows,
                collected
        );
        return new MatrixImpl<>(
                otherMatrix.getVectorMultiEnv(),
                otherMatrix.getVectorGroup(),
                multipliedElementGroup,
                elementGroup,
                this.rows,
                otherMatrix.getColumns(),
                vectors
        );
    }

    @Override
    public Vector<ELEM, M_RES, M> multiply(Vector<ELEM, M_RES, N> vector) {
        return null;
    }

    @Override
    public Matrix<ELEM, M_RES, N, M> transpose() {
        return null;
    }

    @Override
    public String toString() {
        return m.toString();
    }
}
