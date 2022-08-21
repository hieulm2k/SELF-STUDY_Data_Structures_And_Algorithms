/*

 */
public class DoublyLinkedList<T> {

    Node<T> head;
    Node<T> tail;

    static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T d) {
            data = d;
            next = null;
            prev = null;
        }
    }

    public int size() {
        if (head == null)
            return 0;

        int size = 1;
        Node<T> currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
            size++;
        }

        return size;
    }

    public boolean empty() {
        return head == tail;
    }

    public T valueAt(int index) throws Exception {
        return nodeAt(index).data;
    }

    public Node<T> nodeAt(int index) throws Exception {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Invalid index " + index);

        Node<T> currNode = head;
        int i = 0;

        while (i != index && currNode != null) {
            currNode = currNode.next;
            i++;
        }

        return currNode;
    }

    public void pushFront(T value) {
        Node<T> newNode = new Node<T>(value);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public T popFront() {
        T popValue = head.data;
        head.next.prev = null;
        head = head.next;
        return popValue;
    }

    public void pushBack(T value) {
        Node<T> newNode = new Node<T>(value);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public T popBack() {
        T popValue = tail.data;
        tail.prev.next = null;
        tail = tail.prev;
        return popValue;
    }

    public T front() {
        return head.data;
    }

    public T back() {
        return tail.data;
    }

    public void insert(int index, T value) throws Exception {
        Node<T> currNode = nodeAt(index);
        Node<T> prevNode = currNode.prev;
        Node<T> newNode = new Node<>(value);
        prevNode.next = newNode;
        newNode.prev = currNode.prev;
        newNode.next = currNode;
        currNode.prev = newNode;
    }

    public void erase(int index) throws Exception {
        Node<T> currNode = nodeAt(index);
        Node<T> prevNode = currNode.prev;
        Node<T> nextNode = currNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public T valueNFromEnd(int n) {
        if (n < 0 || n >= size())
            throw new IndexOutOfBoundsException("Invalid index from end" + n);

        Node<T> currNode = tail;
        int i = size() - 1;

        while (currNode != null && i != size() - n - 1) {
            currNode = currNode.prev;
            i--;
        }

        return currNode.data;
    }

    public void reverse() {
        Node<T> currNode = head;
        while (currNode != null) {
            Node<T> preNode = currNode.prev;
            Node<T> nextNode = currNode.next;
            currNode.next = preNode;
            currNode.prev = nextNode;
            currNode = nextNode;
        }
        currNode = head;
        head = tail;
        tail = currNode;
    }

    public void removeValue(T value) throws Exception {
        Node<T> currNode = head;
        int i = 0;
        while (currNode != null && currNode.data != value) {
            currNode = currNode.next;
            i++;
        }

        if (i == size()) {
            throw new Exception("Not found value " + value);
        } else {
            erase(i);
        }
    }

    public void printListFromHead() {
        Node<T> currNode = head;

        System.out.print("\nLinkedList: ");

        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
    }

    public void printListFromTail() {
        Node<T> currNode = tail;

        System.out.print("\nLinkedList: ");

        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.prev;
        }
    }

    public static void main(String[] args) throws Exception {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.pushFront(1);
        doublyLinkedList.pushFront(2);
        doublyLinkedList.pushBack(3);
        doublyLinkedList.pushBack(5);
        doublyLinkedList.insert(2, 0);
        doublyLinkedList.reverse();
        doublyLinkedList.printListFromHead();
        doublyLinkedList.printListFromTail();
    }
}