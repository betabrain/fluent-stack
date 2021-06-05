package ch.ergonomics;

@FunctionalInterface
public interface Mapper2<A, B, C> {
    public C invoke(A a, B b) throws Exception;
}
