public class Vector<T> {
    private int capacity;
    private T[] array;
    private int size;

    public Vector() {
        capacity = 16;
        array = (T[]) new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T at(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void push(T item) {
        if (size >= capacity) {
            resize(capacity * 2);
        }
        array[size] = item;
        size++;
    }

    public boolean insert(int index, T item) {
        if (index > size)
            return false;

        if (size >= capacity) {
            resize(capacity * 2);
        }

        for (int i = size; i > index; --i) {
            array[i] = array[i - 1];
        }

        array[index] = item;
        size++;
        return true;
    }

    public void prepend(T item) {
        insert(0, item);
    }

    public T pop() {
        if (size == capacity / 4) {
            resize(capacity / 2);
        }
        T value = array[size - 1];
        delete(size - 1);
        return value;
    }

    public boolean delete(int index) {
        if (index >= size)
            return false;
        for (int i = index; i < size; ++i) {
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    public void remove(T item) {
        int index = find(item);
        if (index != -1) {
            delete(index);
        }
    }

    public int find(T item) {
        for (int i = 0; i < size; ++i) {
            if (array[i] == item)
                return i;
        }

        return -1;
    }

    private void resize(int newCapacity) {
        capacity = newCapacity;
        T[] newArray = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; ++i) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector();
        vector.push(1);
        vector.prepend(5);
        vector.insert(2, 3);
        vector.push(8);
        vector.resize(8);
    }
}