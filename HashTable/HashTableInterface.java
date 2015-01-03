package HashTable;

import java.util.Random;
import java.util.function.Function;

public interface HashTableInterface<K, V> {

    public static final int INITIAL_TABLE_SIZE = 10;
    public static final double LOAD_FACTOR = 0.5;
    public static final int BIGNUMBER = 9161;
    public static final Random r = new Random();
    public static final String BLACK_CIRCLE = "\u25CF";

    /* get the current load factor */
    default float loadFactor() {
        return getSize() / (float) getTableSize();
    }

    /* get the position of key */
    int indexOf(K key);

    /* update or insert a new item to the hashtable */
    void put(K key, V val);

    /* get the value of the key*/
    V get(K key);

    /* remove key */
    void delete(K key);
    
    /* check whether the specified key exist in the hashtable */
    boolean contains(K key);

    /* remove all data from the hashtable */
    void clear();

    /* Double the size of the hashtable */
    void resize();

    /* get the number of elements in the table */
    int getSize();

    /* get the table size */
    int getTableSize();

    /* get the number of collisions */
    int getNumCollisions();

    /* get the number of resizes */
    int getNumResizes();

    /* print the table */
    void print();

    /* print the table like a "graph" */
    void printGraph();

    /* print the table curent status */
    default void printStatus() {
        System.out.println("------------- Table Status -------------------");
        System.out.println("Table size : " + getTableSize());
        System.out.println("Actual size : " + getSize());
        System.out.println("Numbre of collisions : " + getNumCollisions());
        System.out.println("Numbre of resises : " + getNumResizes());
    }

    /* return a random universal hashing function */
    default Function<K, Integer> getHashingFunction() {
        int a, b, p;
        a = r.nextInt();
        b = r.nextInt();
        p = HashTableInterface.nextPrime(BIGNUMBER);

        return (x) -> Math.abs(((a * x.hashCode() + b * x.hashCode()) * p) % getTableSize());
    }

    /* get the next prime number after a*/
    static int nextPrime(int a) {
        while (!isPrime(a)) {
            a++;
        }
        return a;
    }

    /* check if a number is prime */
    static boolean isPrime(int a) {
        if (a == 2) {
            return true;
        } else if (a % 2 == 0) {
            return false;
        } else {
            int i = 3, end = (int) Math.sqrt(a + 0.0);
            while (a % i != 0 && i <= end) {
                i = i + 2;
            }
            return i > end;
        }
    }

    /* return a random string (for testing) */
    static String randomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char c = (char) (r.nextInt((int) (Character.MAX_VALUE)));
            sb.append(c);
        }
        return sb.toString();
    }
}