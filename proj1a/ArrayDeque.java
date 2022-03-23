public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int capacity = 8;
    private static int RFACTOR = 2;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = nextFirst + 1;
    }

    public ArrayDeque(ArrayDeque other) {
        size = 0;
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = nextFirst + 1;

        for (int i = 0; i < other.size(); i += 1)
        {
            addLast((T) other.get(i));
        }
    }

    public int addone(int x) {
        return (x + 1) % items.length;
    }

    public int backone(int x) {
        return (x - 1 + items.length) % items.length;
    }

    public void resize(int newcapacity) {
        T[] newitems = (T[]) new Object[newcapacity];
        int oldindex = addone(nextFirst);
        for (int i = 0; i < size; i++)
        {
            newitems[i] = items[oldindex];
            oldindex = addone(oldindex);
        }
        this.items = newitems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length)
        {
            resize(size * RFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = backone(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length)
        {
            resize(size * RFACTOR);
        }
        items[nextLast] = item;
        nextLast = addone(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = addone(nextFirst);
        for (int j = 0; j < size; j++)
        {
            System.out.print(items[i] + " ");
            i = addone(i);
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (isEmpty())
        {
            return null;
        }
        T firstitem = items[addone(nextFirst)];
        items[addone(nextFirst)] = null;
        nextFirst = addone(nextFirst);
        size -= 1;
        if (items.length >= (2 * capacity) && size < (items.length / 4))
        {
            resize(items.length / 2);
        }
        return firstitem;
    }

    public T get(int index) {
        if (index >= size || isEmpty())
        {
            return null;
        }
        return items[index];
    }

    public T removeLast() {
        if (isEmpty())
        {
            return null;
        }
        T lastitem = items[backone(nextLast)];
        items[backone(nextLast)] = null;
        nextLast = backone(nextLast);
        size -= 1;
        if (items.length >= (2 * capacity) && size < (items.length / 4))
        {
            resize(items.length / 2);
        }
        return lastitem;
    }
}
