package by.smertex.core.mapper;

public interface Mapper<T, F> {
    T map(F from);
}
