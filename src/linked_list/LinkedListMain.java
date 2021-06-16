package linked_list;

import linked_list.Linked.Node;

public class LinkedListMain {
   public static void main(String[] args) {
      Linked linked = new Linked();
      linked.add(1).add(2).add(3).add(4).add(5).add(6).add(7).add(8).add(9);
      Node head = linked.head;

      System.out.println(middleOfLL(head));
      linked.print();
   }

   private static void reverse(Node head) {
      if (head == null) {
         return;
      }
      Node forward;
      Node prev = null;
      Node current = head;



   }


   private static int middleOfLL(Node head) {
      if (head == null) {
         return -1;
      }

      Node slow = head;
      Node fast = head;
      while (fast.next != null && fast.next.next != null) {
         fast = fast.next.next;
         slow = slow.next;
      }
      return slow.data;
   }
}
