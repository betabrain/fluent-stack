package ch.ergonomics;

@FunctionalInterface
public interface Supplier<T> {
    public T get() throws Exception;
}
