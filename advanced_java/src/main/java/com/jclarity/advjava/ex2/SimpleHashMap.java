/**
 * This class is a simplified HashMap - it is supposed to show how the 
 * implementation works. This implementation (like the real one) is driven 
 * by the need to be able to resize the table as keys are added.
 * 
 * NOTE:
 * 1) It does not support null keys.
 * 2) It does not implement the Map interface
 * 3) Real implementations have an auxiliary hash function to defend 
 * against bad hashCode() functions on keys
 * 4) It is not generic
 * 5) It does not deal with very large maps of the order of the size of 
 * Integer.MAX_VALUE
 */
package com.jclarity.advjava.ex2;

/**
 *
 * @author boxcat
 */
public class SimpleHashMap {
    
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // Table holds our linked lists for each bucket
    private Entry[] table;
    private int size = 0;
    private int threshold;
        
    public SimpleHashMap() {
        threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
    }
    
    public Object put(Object key, Object value) {
        // Null keys are not supported
        if (key == null) return null;

        int hash = key.hashCode();
        int i = indexFor(hash, table.length);
        for (Entry e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                // Overwrite an existing value
                Object oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        // Add a new entry
        addEntry(hash, key, value, i);
        return null;
    }
    

    public Object get(Object key) {
        // SIMPLIFY: Null keys are not supported
        if (key == null) return null;
        
        int hash = key.hashCode();
        int i = indexFor(hash, table.length);
        for (Entry e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && 
                ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        
        return null;
    }
    
    private int indexFor(int h, int length) {
        return h & (length-1);
    }

    // This is a linked list node
    private static class Entry {
        final Object key;
        Object value;
        Entry next;
        final int hash;
    
        Entry(int h, Object k, Object v, Entry n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }
    }
        
    private void addEntry(int hash, Object key, Object value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }
    
    private void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * DEFAULT_LOAD_FACTOR);
    }
        
    // The heart of the resize operation - which is O(table.length)
    private void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }
}
