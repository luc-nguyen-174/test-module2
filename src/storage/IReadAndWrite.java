package storage;

public interface IReadAndWrite<T> {
    void writeFile(T file);
    T readFile();
}
