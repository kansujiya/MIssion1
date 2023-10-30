package LinkedList;


public class SlowFastPointer {

    class ListNode {
        int value;
        ListNode next;

        ListNode(int value, ListNode node) {
            this.value = value;
            this.next = node;
        }
    }

    //Linked list: fast and slow pointer
    public int fastSlowPointer(ListNode head) {
        int ans = 0;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && slow.next != null) {
            //do logic
            slow = slow.next;
            fast = fast.next.next;
        }

        return ans;
    }
}
