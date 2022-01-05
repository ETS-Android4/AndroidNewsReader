package com.example.data;

public interface ResultMapper<P, R> {

    R map(P model);
}
