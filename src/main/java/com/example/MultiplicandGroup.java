package com.example;

public interface MultiplicandGroup<E, M> {

    M multiply(E e1, E e2);

    E unit();
}
