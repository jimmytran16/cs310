package cs310; //This package is not in use for cs310

/**
 * This interface specifies the API for the basic symbol table data structure.
 */
public interface BasicST<Key, Value> {
    /**
     * Returns {@code true} if this symbol table is empty, and {@code false} otherwise.
     *
     * @return {@code true} if this symbol table is empty, and {@code false} otherwise.
     */
    public boolean isEmpty();

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table.
     */
    public int size();

    /**
     * Inserts the {@code key} and {@code value} pair into this symbol table.
     *
     * @param key   the key.
     * @param value the value.
     */
    public void put(Key key, Value value);

    /**
     * Returns the value associated with {@code key} in this symbol table, or {@code null}.
     *
     * @param key the key.
     * @return the value associated with {@code key} in this symbol table, or {@code null}.
     */
    public Value get(Key key);

    /**
     * Returns {@code true} if this symbol table contains {@code key}, and {@code false} otherwise.
     *
     * @param key the key.
     * @return {@code true} if this symbol table contains {@code key}, and {@code false} otherwise.
     */
    public boolean contains(Key key);

    /**
     * Deletes {@code key} and the associated value from this symbol table.
     *
     * @param key the key.
     */
    public void delete(Key key);

    /**
     * Returns all the keys in this symbol table.
     *
     * @return all the keys in this symbol table.
     */
    public Iterable<Key> keys();
}