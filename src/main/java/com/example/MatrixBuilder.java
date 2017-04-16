package com.example;

import java.util.Arrays;

public class MatrixBuilder<ELEM, M_RES, M extends Vectors, N extends Vectors> {

    // v[v[e]] * v[v[e]]
    private MultiplicandGroup<Vector<ELEM, ELEM, N>, M_RES> vectorMultiEnv;
    // v[e] + v[e]
    private VectorGroup<ELEM, ELEM, N> vectorGroup;
    // v[e]*v[e] + v[e]*v[e]
    private Group<M_RES> multipliedElementGroup;
    // e + e
    private Group<ELEM> elementGroup;
    // v[e]*v[e]
    private MultiplicandGroup<ELEM, M_RES> env;

    private final M rowNum;
    private final N colNum;

    public MatrixBuilder(M rowNum, N colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    public void setVectorMultiEnv(MultiplicandGroup<Vector<ELEM, ELEM, N>, M_RES> vectorMultiEnv) {
        this.vectorMultiEnv = vectorMultiEnv;
    }

    public void setVectorGroup(VectorGroup<ELEM, ELEM, N> vectorGroup) {
        this.vectorGroup = vectorGroup;
    }

    public void setMultipliedElementGroup(Group<M_RES> multipliedElementGroup) {
        this.multipliedElementGroup = multipliedElementGroup;
    }

    public void setElementGroup(Group<ELEM> elementGroup) {
        this.elementGroup = elementGroup;
    }

    public void setEnv(MultiplicandGroup<ELEM, M_RES> env) {
        this.env = env;
    }

//    public Matrix<ELEM, M_RES, M, N> build() {
//        return new MatrixImpl<ELEM, M_RES, M, N>(
//                vectorMultiEnv,
//                vectorGroup,
//                elementGroup,
//                elementGroup,
//                rowNum,
//                colNum,
//                new VectorImpl<Vector<ELEM, ELEM, N>, M_RES, M>(
//                        vectorMultiEnv,
//                        vectorGroup,
//                        elementGroup,
//                        rowNum,
//                        Arrays.asList(
//                                new VectorImpl<ELEM, ELEM, N>(env, elementGroup, elementGroup, colNum, Arrays.asList(3.0, 1.0, 2.0)),
//                                new VectorImpl<ELEM, ELEM, N>(env, elementGroup, elementGroup, colNum, Arrays.asList(0.0, -1.0, 4.0))
//                        )
//                )
//        );
//    }
}
