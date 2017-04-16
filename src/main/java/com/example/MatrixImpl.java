package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class MatrixImpl<ELEM, M_RES, M extends Vectors, N extends Vectors> implements Matrix<ELEM, M_RES, M, N> {

    // v[v[e]] * v[v[e]]
    private final VectorMultiplicandGroup<ELEM, ELEM, M_RES, N> vectorMultiEnv;
    // v[e] + v[e]
    private final VectorGroup<ELEM, ELEM, N> vectorGroup;
    // v[e]*v[e] + v[e]*v[e]
    private final Group<M_RES> multipliedElementGroup;
    // e + e
    private final Group<ELEM> elementGroup;
    // matrix
    private final Vector<Vector<ELEM, ELEM, N>, M_RES, M> rows;
    // rowNumber in matrix
    private final M rowNumber;
    // columns in each vector
    private final N colNumber;

    public MatrixImpl(VectorMultiplicandGroup<ELEM, ELEM, M_RES, N> vectorMultiEnv,
                      VectorGroup<ELEM, ELEM, N> vectorGroup,
                      Group<M_RES> multipliedElementGroup,
                      Group<ELEM> elementGroup,
                      M rowNumber,
                      N colNumber,
                      Vector<Vector<ELEM, ELEM, N>, M_RES, M> rows) {
        this.vectorMultiEnv = vectorMultiEnv;
        this.vectorGroup = vectorGroup;
        this.multipliedElementGroup = multipliedElementGroup;
        this.elementGroup = elementGroup;
        this.colNumber = colNumber;
        this.rows = rows;
        this.rowNumber = rowNumber;
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
        return rowNumber;
    }

    @Override
    public N getColumns() {
        return colNumber;
    }

    @Override
    public VectorGroup<ELEM, ELEM, N> getVectorGroup() {
        return vectorGroup;
    }

    @Override
    public VectorMultiplicandGroup<ELEM, ELEM, M_RES, N> getVectorMultiEnv() {
        return vectorMultiEnv;
    }

    @Override
    public Vector<ELEM, ELEM, N> getRow(int i) {
        return rows.get(i);
    }

    @Override
    public Vector<ELEM, ELEM, M> getCol(int i) {
        List<ELEM> columnElements = rows.elements()
                .map(e -> e.get(i))
                .collect(Collectors.toList());
        return new VectorImpl<>(
                rows.get(0).getEnv(),
                elementGroup,
                elementGroup,
                rowNumber,
                columnElements
        );
    }

    @Override
    public Matrix<ELEM, M_RES, M, N> plus(Matrix<ELEM, M_RES, M, N> otherMatrix) {
        List<Vector<ELEM, ELEM, N>> elements = IntStream.range(0, rowNumber.size())
                .mapToObj(i -> vectorGroup.add(getRow(i), otherMatrix.getRow(i)))
                .collect(toList());
        return new MatrixImpl<>(
                vectorMultiEnv,
                vectorGroup,
                multipliedElementGroup,
                elementGroup,
                rowNumber,
                colNumber,
                fromList(elements, rowNumber)
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
                rowNumber,
                collected
        );
        return new MatrixImpl<>(
                otherMatrix.getVectorMultiEnv(),
                otherMatrix.getVectorGroup(),
                multipliedElementGroup,
                elementGroup,
                this.rowNumber,
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
        List<Vector<ELEM, ELEM, M>> elements = IntStream.range(0, this.getColumns().size())
                .mapToObj(this::getCol)
                .collect(toList());
        VectorMultiplicandGroup<ELEM, ELEM, M_RES, M> newVectorMultiEnv = vectorMultiEnv.resize(getRows());
        VectorGroup<ELEM, ELEM, M> newVectorGroup = vectorGroup.resize(getRows());
        Vector<Vector<ELEM, ELEM, M>, M_RES, N> vectors = new VectorImpl<>(
                newVectorMultiEnv,
                newVectorGroup,
                multipliedElementGroup,
                colNumber,
                elements
        );
        return new MatrixImpl<>(
                newVectorMultiEnv,
                newVectorGroup,
                multipliedElementGroup,
                elementGroup,
                colNumber,
                rowNumber,
                vectors
        );
    }

    @Override
    public String toString() {
        return rows.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatrixImpl<?, ?, ?, ?> matrix = (MatrixImpl<?, ?, ?, ?>) o;

        return rows.equals(matrix.rows);
    }

    @Override
    public int hashCode() {
        return rows.hashCode();
    }
}
