package ch.ergonomics;

public interface Mapper3<A, B, C, D> {
    public D invoke(A a, B b, C c) throws Exception;
}
