package ch.ergonomics;

@FunctionalInterface
public interface Mapper6<A, B, C, D, E, F, G> {
    public G invoke(A a, B b, C c, D d, E e, F f) throws Exception;
}
