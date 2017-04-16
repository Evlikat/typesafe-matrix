package com.example;

public interface Matrix<ELEM, M_RES, M extends Vectors, N extends Vectors> {

    M getRows();

    N getColumns();

    VectorGroup<ELEM, ELEM, N> getVectorGroup();

    MultiplicandGroup<Vector<ELEM, ELEM, N>, M_RES> getVectorMultiEnv();

    Vector<ELEM, ELEM, N> getRow(int i);

    Vector<ELEM, ELEM, M> getCol(int i);

    Matrix<ELEM, M_RES, M, N> plus(Matrix<ELEM, M_RES, M, N> otherMatrix);

    <K extends Vectors> Matrix<ELEM, M_RES, M, K> multiply(Matrix<ELEM, M_RES, N, K> otherMatrix);

    Vector<ELEM, M_RES, M> multiply(Vector<ELEM, M_RES, N> vector);

    Matrix<ELEM, M_RES, N, M> transpose();
}
