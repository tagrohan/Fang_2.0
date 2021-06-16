package binary;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
   public static Node root;

   public static class Node {
      int data;
      Node left;
      Node right;

      public Node(int data) {
         this.data = data;
         this.left = this.right = null;
      }
   }

   public static class Pair {
      Node node;
      int state;

      public Pair(Node node, int state) {
         this.node = node;
         this.state = state;
      }
   }

   public static void main(String[] args) {

      Integer[] arr = new Integer[]{50, 25, 12, null, null, 37, 30,
              null, null, null, 75, 62, null, 70, null, null, 57, null, null};

      Integer[] arrSmall = new Integer[]{1, 1, 1, null, null, 1, 1,
              null, null, null, 2, 2, null, 2, null, null, 2, null, null};

      Integer[] bst = new Integer[]{50, 40, 20, null, null, 45, 43
              , null, null, null, 60, 55, null, 56, null, null, 70, null, null};
      createTree(arrSmall);
      System.out.println(mirrorBTMulSumV2(root.left, root.right, 0) + (root.data * root.data));
   }

   private static int mirrorBTMulSumV2(Node left, Node right, int sum) {
//      System.out.println(mirrorSum(root.left, root.right, 0) + (root.data * root.data));
      if (left == null && right == null) {
         return 0;
      }

      int leftS = mirrorBTMulSumV2(left.left, right.right, sum);
      int rightS = mirrorBTMulSumV2(left.right, right.left, sum);
      return leftS + rightS + (left.data * right.data);
   }

   // working good but we can use better approach with two pointer in inorder traversal
   private static int mirrorBTMulSum(Node root) {
      if (root == null) return -1;
      LinkedList<Node> list = new LinkedList<>();
      list.add(root);
      int sum = root.data * root.data;
      while (list.size() > 0) {
         int size = list.size();
         for (int i = 0; i < size / 2; i++) {
            int mul = list.get(i).data * list.get(list.size() - i - 1).data;
            System.out.println(list.get(list.size() - i - 1).data);
            sum += mul;
         }
         for (int i = 0; i < size; i++) {
            Node node = list.removeFirst();
            if (node.left != null) list.add(node.left);
            if (node.right != null) list.add(node.right);
         }
      }
      return sum;
   }

   private static void leftViewOfBinaryTreeV2(Node root) {
      if (root == null) return;
      Queue<Node> queue = new ArrayDeque<>();
      queue.add(root);
      while (!queue.isEmpty()) {
         int size = queue.size(); // each for size changes
         for (int i = 0; i < size; i++) {
            Node node = queue.remove();
            if (i == 0) {
               System.out.print(node.data + " ");
            }
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
         }
      }
   }


   // working fine but sometimes can five TLE
   private static void leftViewOfBinaryTree(Node root) {
      Queue<Node> queue = new LinkedList<>();
      boolean isFirst = true;
      queue.add(root);
      queue.add(null);
      while (queue.size() > 1 || queue.peek() != null) {
         Node node = queue.remove();
         if (node != null) {
            if (isFirst) {
               System.out.print(node.data + " ");
               isFirst = false;
            }
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
         } else {
            queue.add(null);
            isFirst = true;
         }
      }
   }


   // todo: from above fang 2.0 started

   // using iterative working fine
   private static void preInPostOrderIterative(Node node) {
      Stack<Pair> stack = new Stack<>();
      stack.add(new Pair(node, -1));
      StringBuilder pre = new StringBuilder();
      StringBuilder in = new StringBuilder();
      StringBuilder post = new StringBuilder();

      while (!stack.isEmpty()) {
         Pair pair = stack.peek();
         if (pair.state == -1) {
            Node temp = pair.node;
            pre.append(temp.data).append(" ");
            if (temp.left != null) {
               stack.push(new Pair(temp.left, -1));
            }
            pair.state += 1;
         } else if (pair.state == 0) {
            Node temp = pair.node;
            in.append(temp.data).append(" ");
            if (temp.right != null) {
               stack.push(new Pair(temp.right, -1));
            }
            pair.state += 1;
         } else {
            post.append(pair.node.data).append(" ");
            stack.pop();
         }
      }
      System.out.println(pre);
      System.out.println(in);
      System.out.println(post);
   }

   // level order using for loop
   private static void levelOrderTraversalForLoop(Node root) {
      Queue<Node> queue = new ArrayDeque<>();
      queue.add(root);

      while (!queue.isEmpty()) {
         int size = queue.size();
         for (int i = 0; i < size; i++) {
            Node node = queue.remove();
            System.out.print(node.data + " ");
            if (node.left != null) {
               queue.add(node.left);
            }
            if (node.right != null) {
               queue.add(node.right);
            }
         }
         System.out.println();
      }
   }

   // working fine but we can also use for loop to do this type as well check above
   private static void levelOrderTraversal(Node root) {
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);
      queue.add(null);

      while (queue.size() > 1 || queue.peek() != null) {

         Node node = queue.remove();
         if (node != null) {
            System.out.print(node.data + " ");
            if (node.left != null) {
               queue.add(node.left);
            }
            if (node.right != null) {
               queue.add(node.right);
            }
         } else {
            System.out.println();
            queue.add(null);
         }
      }
   }

   // working fine
   private static void prePostInOrderTraversal(Node root) {
      if (root == null) {
         return;
      }
      System.out.println("pre -> " + root.data);
      prePostInOrderTraversal(root.left);
      System.out.println("In -> " + root.data);
      prePostInOrderTraversal(root.right);
      System.out.println("post -> " + root.data);

   }


   private static int size(Node root) {
      if (root == null) {
         return 0;
      }
      int left = size(root.left);
      int right = size(root.right);
      return left + right + 1;
   }

   private static int sum(Node root) {
      if (root == null) {
         return 0;
      }
      int left = sum(root.left);
      int right = sum(root.right);
      return root.data + left + right;

   }

   private static int max(Node root) {
      if (root == null) {
         return Integer.MIN_VALUE;
      }
      int left = max(root.left);
      int right = max((root.right));

      return Integer.max(Integer.max(left, right), root.data);
   }

   private static int height(Node root) {
      if (root == null) {
         return -1; // -1m bcz we want edges, if we want nodes height it would be 0
      }
      int left = height((root.left));
      int right = height((root.right));

      return Integer.max(left, right) + 1;
   }

   // working fine it's preorder traversal
   private static void printUsingStack(Node root) {
      Stack<Node> stack = new Stack<>();
      stack.push(root);
      while (!stack.isEmpty()) {
         Node node = stack.pop();
         if (node.right != null) {
            stack.push(node.right);
         }
         if (node.left != null) {
            stack.push(node.left);
         }
         System.out.println(node.data);
      }
   }

   private static void printRecursive(Node root) {
      if (root == null) {
         return;
      }
      String builder =
              (root.left == null ? "" : root.left.data) + " <- " +
                      root.data + " -> " +
                      (root.right == null ? "" : root.right.data);
      System.out.println(builder);

      printRecursive(root.left);
      printRecursive(root.right);

   }


   private static Node createTree(Integer[] arr) {
      Stack<Pair> stack = new Stack<>();
      Node node = new Node(arr[0]);
      root = node;
      stack.push(new Pair(node, -1));

      int i = 0;
      while (!stack.isEmpty()) {
         Pair pair = stack.peek();
         if (pair.state == -1) {
            i += 1;
            if (arr[i] != null) {
               Node tem = new Node(arr[i]);
               pair.node.left = tem;
               stack.push(new Pair(tem, -1));
            } else {
               pair.node.left = null;
            }
            pair.state += 1;
         } else if (pair.state == 0) {
            i += 1;
            if (arr[i] != null) {
               Node tem = new Node(arr[i]);
               pair.node.right = tem;
               stack.push(new Pair(tem, -1));
            } else {
               pair.node.right = null;
            }
            pair.state += 1;
         } else {
            stack.pop();
         }
      }
      return root;
   }

   // any sum path available
   private static boolean pathAvail(Node node, int sum, int cSum) {
//      System.out.println(pathAvail(root, 50 + 25 + 37 + 30, 0)); // it's node itself actually
      if (node == null) {
         return false;
      }
      if (node.left == null && node.right == null) {
         if (cSum + node.data == sum) {
            return true;
         }
      }
      cSum += node.data;
      boolean left = pathAvail(node.left, sum, cSum);
      boolean right = pathAvail(node.right, sum, cSum);
      return left || right;
   }
}
