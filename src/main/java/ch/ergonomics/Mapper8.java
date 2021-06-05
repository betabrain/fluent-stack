package ch.ergonomics;

@FunctionalInterface
public interface Mapper8<A, B, C, D, E, F, G, H, I> {
    public I invoke(A a, B b, C c, D d, E e, F f, G g, H h) throws Exception;
}
