package ch.ergonomics;

public interface Mapper1<A, B> {
    public B invoke(A a) throws Exception;
}
