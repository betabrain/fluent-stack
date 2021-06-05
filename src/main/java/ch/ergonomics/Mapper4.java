package ch.ergonomics;

public interface Mapper4<A, B, C, D, E> {
    public E invoke(A a, B b, C c, D d) throws Exception;
}
