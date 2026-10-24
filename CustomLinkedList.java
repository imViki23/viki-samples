public class CustomLinkedList<T> {

    public Node<T> head;
    public int size;

    // Space - O(1), Time - O(N)
    public void add(T data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(data);
        }
        size += 1;
    }

    // Space - O(1), Time - O(N)
    public void remove(int index) {
        checkBounds(index);
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
        }
        size -= 1;
    }

    // Space - O(1), Time - O(N)
    public void update(int index, T data) {
        checkBounds(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = data;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(",");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();
        System.out.println(customLinkedList.toString());
        customLinkedList.add(1);
        System.out.println(customLinkedList.toString());
        customLinkedList.add(2);
        customLinkedList.add(3);
        customLinkedList.add(4);
        customLinkedList.add(5);
        System.out.println(customLinkedList.toString());
        customLinkedList.update(0, 11);
        customLinkedList.update(2, 33);
        customLinkedList.update(4, 55);
        try {
            customLinkedList.update(5, 66);
        } catch (Exception e) {
            System.out.println("ArrayIndexOutOfBound");
        }
        System.out.println(customLinkedList.toString());
        customLinkedList.remove(0);
        System.out.println(customLinkedList.toString());
        try {
            customLinkedList.remove(4);
        } catch (Exception e) {
            System.out.println("ArrayIndexOutOfBound");
        }
        customLinkedList.remove(3);
        System.out.println(customLinkedList.toString());
        customLinkedList.remove(1);
        System.out.println(customLinkedList.toString());
    }
}
