package LinkedList;

import java.util.List;

class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class LinkedList {
    public static void main(String[] args) {
        //1. Start of LinkedList Cycle
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        //make cycle
        head.next.next.next.next.next = head.next.next;
        System.out.print("LinkedList cycle start at: " + findCycleStart(head).value);

    }

    public static ListNode findCycleStart(ListNode head) {
        int cycleLength = 0;
        ListNode slow = head;
        ListNode fast = head;

        while(slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                cycleLength = getCycleLength(slow);
                break;
            }
        }

        return cyclePoint(head, cycleLength);

    }

    public static int getCycleLength(ListNode slow) {
        ListNode current = slow;
        int cycleLength = 0;

        do{
            cycleLength++;
            current = current.next;
        } while(slow != current);

        return cycleLength;
    }

    public static ListNode cyclePoint(ListNode head, int cycleLength) {
        ListNode pointer1 = head;
        ListNode pointer2 = head;

        while(cycleLength > 0) {
            pointer2 = pointer2.next;
            cycleLength--;
        }

        while(pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        return pointer1;
    }

}
