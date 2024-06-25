package bitcamp.myapp.command;

public class LinkedList {

    Node first;
    Node last;

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append("홍길동");
        list.append("임꺽정");
        list.append("유관순");

        list.printAll();
    }

    public void append(Object value) {
        Node newNode = new Node(value);

        if (first == null) {
            last = first = newNode;
        } else {
            // 새로운 노드를 마지막 노드에 추가
            last.next = newNode;
            last = newNode;
        }
    }

    public void printAll() {
        Node cursor = first;

        while (cursor != null) {
            System.out.println(cursor.value);
            cursor = cursor.next;
        }
    }
}
