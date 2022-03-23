public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        public Node prev;
        public T item;
        public Node next;
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Node() {
            this.next = null;
            this.prev = null;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i += 1)
        {
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        Node firstnode = new Node(item, sentinel, sentinel.next);
        sentinel.next = firstnode;
        firstnode.next.prev = firstnode;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0)
        {
            System.out.println("empty deque");
        }
        Node p = sentinel.next;
        System.out.print(p.item);
        while (p.next != sentinel)
        {
            p = p.next;
            System.out.print(" " + p.item);
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (isEmpty())
        {
            return null;
        }
        Node p1 = sentinel.next;
        size--;

        if (p1.next != null)
        {
            Node p2 = p1.next;
            sentinel.next = p2;
            p2.prev = sentinel;
        }
        else
        {
            sentinel.next = null;
        }
        return p1.item;
    }

    public T removeLast() {
        if (isEmpty())
        {
            return null;
        }
        Node p1 = sentinel.prev;
        size--;
        if (p1.prev != null)
        {
            Node p2 = p1.prev;
            p2.next = sentinel;
            sentinel.prev = p2;

        }
        else
        {
            sentinel.prev = null;
        }
        return p1.item;
    }

    public T get(Integer index) {
        if (isEmpty())
        {
            return null;
        }
        if (index >= size)
        {
            return null;
        }
        Node pindex = sentinel.next;
        while ((index--) > 0)
        {
            pindex = pindex.next;
        }
        return pindex.item;
    }

    public T getRecrusive(Integer index) {
        if (isEmpty())
        {
            return null;
        }
        if (index >= size || index < 0)
        {
            return null;
        }
        return getRecrusivehelper(index, sentinel.next);
    }

    public T getRecrusivehelper(Integer index, Node pindex) {
        if (index == 0){
            return pindex.item;
        }
        return getRecrusivehelper(index--, pindex.next);
    }
}
