package LinkedList;

public class ReverseLinkedList {

    class ListNode {
        int value;
        ListNode next;

        ListNode(int value, ListNode node) {
            this.value = value;
            this.next = node;
        }
    }

    //Reversing a linked list
    public ListNode reverseLinkedList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return head;
    }
}
