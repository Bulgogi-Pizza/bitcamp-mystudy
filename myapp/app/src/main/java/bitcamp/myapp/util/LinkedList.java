package bitcamp.myapp.util;

public class LinkedList extends AbstractList {

    private Node first;
    private Node last;

    @Override
    public void add(Object value) {
        Node newNode = new Node(value);

        if (first == null) {
            last = first = newNode;
        } else {
            // 새로운 노드를 마지막 노드에 추가
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node cursor = first;
        int currentIndex = 0;

        while (cursor != null) {
            if (currentIndex == index) {
                return cursor.value;
            }
            cursor = cursor.next;
            currentIndex++;
        }
        return null;
    }

    @Override
    public int indexOf(Object value) {
        Node cursor = first;
        int currentIndex = 0;
        while (!(cursor.value.equals(value))) {
            cursor = cursor.next;
            ++currentIndex;
            if (cursor.next == null) {
                return -1;
            }
        }
        return currentIndex;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];

        Node cursor = first;
        for (int i = 0; i < size; i++) {
            arr[i] = cursor.value;
            cursor = cursor.next;
        }

        return arr;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Object removedValue = getNode(index).value;

        // 요소가 하나밖에 없는데 삭제할 경우 모두 null로 초기화 후 삭제할 값 반환
        if (size == 1) {
            getNode(index).value = null;
            getNode(index).next = null;
            first = null;
            last = null;
            size = 0;

            return removedValue;
        }

        if (index == 0 && first.next != null) {
            Node temp = first.next;
            first.value = null;
            first.next = null;
            first = temp;
        } else if (index == size - 1) {
            Node temp = getNode(index - 1);
            last.value = null;
            last = temp;
            last.next = null;
        } else {
            Node temp = getNode(index + 1);
            getNode(index).value = null;
            getNode(index).next = null;
            getNode(index - 1).next = temp;
        }
        size--;

        return removedValue;
    }

    public Node getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node cursor = first;

        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }

        return cursor;
    }

    // 1) 스태틱 중첩 클래스
    // 바깥 패키지에 노출이 되지 않는다는 장점
    private static class Node {

        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }
    }
}
