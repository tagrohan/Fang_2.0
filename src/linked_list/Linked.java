package linked_list;

public class Linked {
   Node head;
   int size;

   static class Node {
      int data;
      Node next;

      public Node(int data) {
         this.data = data;
      }
   }

   public Linked add(int val) {
      Node node = new Node(val);
      if (size == 0) {
         head = node;
      } else {
         Node temp = head;
         while (temp.next != null) {
            temp = temp.next;
         }
         temp.next = node;
      }
      size++;
      return this;
   }

   public void print() {
      Node temp = this.head;
      while (temp != null) {
         System.out.print(temp.data + " -> ");
         temp = temp.next;
      }
      System.out.println("null");
   }

   public int size() {
      return size;
   }
}
