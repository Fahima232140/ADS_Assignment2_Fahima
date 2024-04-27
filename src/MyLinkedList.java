import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private class MyNode {
        private T element;
        private MyNode next;
        private MyNode prev;

        public MyNode(T element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        MyNode newNode = new MyNode(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void set(int index, T item) {
        MyNode node = getNode(index);
        if (node != null) {
            node.element = item;
        }
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(item);
        } else {
            MyNode node = getNode(index);
            MyNode newNode = new MyNode(item);
            if (node == head) {
                head.prev = newNode;
                newNode.next = head;
                head = newNode;
            } else {
                MyNode prevNode = node.prev;
                prevNode.next = newNode;
                newNode.prev = prevNode;
                newNode.next = node;
                node.prev = newNode;
            }
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(size, item);
    }

    @Override
    public T get(int index) {
        MyNode node = getNode(index);
        if (node != null) {
            return node.element;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    @Override
    public void remove(int index) {
        MyNode node = getNode(index);
        if (node != null) {
            if (node == head) {
                head = node.next;
                if (head != null) {
                    head.prev = null;
                }
            } else if (node == tail) {
                tail = node.prev;
                tail.next = null;
            } else {
                MyNode prevNode = node.prev;
                MyNode nextNode = node.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
            size--;
        }
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public void sort() {
        // Convert linked list to array for sorting
        Object[] array = toArray();

        // Use bubble sort to sort the array
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (((Comparable<T>) array[j]).compareTo((T) array[j + 1]) > 0) {
                    // Swap elements at j and j+1
                    Object temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        // Update linked list with sorted elements
        MyNode current = head;
        for (int i = 0; i < size; i++) {
            current.element = (T) array[i];
            current = current.next;
        }
    }

    private MyNode getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public int indexOf(Object object) {
        MyNode current = head;
        int index = 0;
        while (current != null) {
            if (current.element.equals(object)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        MyNode current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.element.equals(object)) {
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyNode current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.element;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
