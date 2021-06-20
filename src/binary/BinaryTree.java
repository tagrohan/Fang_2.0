package binary;

import java.util.*;

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

      Integer[] arrSmall = new Integer[]{5, 3, 2, null, null, 1, 1,
              null, null, null, 2, 1, null, 1, null, null, 1, null, null};

      Integer[] bst = new Integer[]{50, 40, 20, null, null, 45, 43
              , null, null, null, 60, 55, null, 56, null, null, 70, null, null};
      Integer[] another = new Integer[]{1, 2, null, 5, 8, null, null, null,
              3, 6, null, 7, null, null, null};

      createTree(another);

      for (Integer i : getBoundaries(root)) {
         System.out.print(i + " ");
      }

   }

   private static List<Integer> getBoundaries(Node root) {
      List<Integer> list = new ArrayList<>();
      list.add(root.data);
      getBoundariesHelperRightLeft(root.left, true, list);
      getBoundariesHelperLeaf(root, list);
      getBoundariesHelperRightLeft(root.right, false, list);
      return list;
   }

   private static void getBoundariesHelperLeaf(Node node, List<Integer> list) {
      if (node == null) {
         return;
      }
      if (node.left == null && node.right == null) list.add(node.data);
      getBoundariesHelperLeaf(node.left, list);
      getBoundariesHelperLeaf(node.right, list);
   }

   private static void getBoundariesHelperRightLeft(Node node, boolean isLeft, List<Integer> list) {
      if (node == null) {
         return;
      }
      if (isLeft) {
         if (node.left != null || node.right != null) list.add(node.data);
         if (node.left == null && node.right != null) {
            getBoundariesHelperRightLeft(node.right, isLeft, list);
         }
         getBoundariesHelperRightLeft(node.left, isLeft, list);

      } else {
         if (node.right == null && node.left != null) {
            getBoundariesHelperRightLeft(node.left, isLeft, list);
         }
         getBoundariesHelperRightLeft(node.right, isLeft, list);
         if (node.left != null || node.right != null) list.add(node.data);
      }

   }


   // basically outer boundaries left then right
   private static List<Integer> getPyramidOfTree(Node root) {
//      for (Integer i : getPyramidOfTree(root)) {
//         System.out.print(i + " ");
//      }
      List<Integer> list = new ArrayList<>();
      if (root == null) {
         return list;
      }

      getPyramidOfTreeHelper(root.left, true, list);
      list.add(root.data);
      getPyramidOfTreeHelper(root.right, false, list);
      return list;
   }

   private static void getPyramidOfTreeHelper(Node node, boolean isLeft, List<Integer> list) {
      if (node == null) {
         return;
      }

      if (isLeft) {
         getPyramidOfTreeHelper(node.left, isLeft, list);
         list.add(node.data);
      } else {
         list.add(node.data);
         getPyramidOfTreeHelper(node.right, isLeft, list);
      }
   }


   private static void printKLevelNodesV2AtAGivenNode(Node root, int data) {
      if (root.data == data) {
         System.out.println(root.data);
         return;
      }

      Queue<Node> queue = new ArrayDeque<>();
      boolean trigger = false;
      boolean toTrigger = false;
      queue.add(root);
      while (!queue.isEmpty()) {
         int size = queue.size();
         if (toTrigger) {
            trigger = true;
         }
         for (int i = 0; i < size; i++) {
            Node node = queue.remove();
            if (trigger) {
               System.out.print(node.data + " ");
            }

            if (node.left != null) {
               queue.add(node.left);
               if (node.left.data == data) toTrigger = true;
            }
            if (node.right != null) {
               queue.add(node.right);
               if (node.right.data == data) toTrigger = true;
            }
         }
         if (trigger) {
            return;
         }
      }
   }

   private static void printKLevelNodes(Node root, int k) {
      Queue<Node> queue = new ArrayDeque<>();
      queue.add(root);
      while (!queue.isEmpty()) {
         int size = queue.size();
         k--;
         for (int i = 0; i < size; i++) {
            Node node = queue.remove();
            if (k == 0) {
               System.out.print(node.data + " ");
            }

            if (node.left != null) {
               queue.add(node.left);
            }
            if (node.right != null) {
               queue.add(node.right);
            }
         }
      }
   }

//   private static Map<Integer, List<Integer>> levelValuesToKey(Node root, int[] arr) {
//      int level = 0;
//      Map<Integer, List<Integer>> map = new HashMap<>();
//      for (int val : arr) {
//         map.put(val, new ArrayList<>());
//      }
//      Queue<Node> queue = new ArrayDeque<>();
//      queue.add(root);
//      while (!queue.isEmpty()) {
//         int size = queue.size();
//         for (int i = 0; i < size; i++) {
//            Node node = queue.remove();
//            if (node.left != null) {
//               if (map.containsKey(node.left.data)) {
//
//               }
//            }
//            if (node.right != null) {
//               if (map.containsKey(node.right.data)) {
//
//               }
//            }
//         }
//      }
//   }

   private static boolean isIdenticalV2(Node root1, Node root2) {
      if (root1 == null && root2 != null || root1 != null && root2 == null) {
         return false;
      } else if (root1 == null) {
         return true;
      }
      if (root1.data == root2.data) {
         boolean isIdeLeft = isIdentical(root1.left, root2.left);
         boolean isIdeRight = isIdentical(root1.right, root2.right);
         return isIdeLeft && isIdeRight;
      }
      return false;
   }

   private static boolean isIdentical(Node root1, Node root2) {
//      System.out.println(isIdentical(root, root));

      if (root1 == null && root2 != null || root1 != null && root2 == null) {
         return false;
      } else if (root1 == null) {
         return true;
      }

      boolean isIdeLeft = isIdentical(root1.left, root2.left);
      boolean isIdeRight = isIdentical(root1.right, root2.right);

      if (!isIdeRight && isIdeLeft) {
         return false;
      }
      return root1.data == root2.data;
   }


//                     5
//                  /     \
//                 3       2
//               /   \   /   \
//              2     1 1     1
//                   /   \
//                  1     1   ans  14

   //            2
//         5 2 3 1 1
//         sum = 2
   // look like kadane's algo for BT
   private static int pathMax(Node root) {
      if (root == null) {
         return -1;
      }
      int left = pathMaxHelper(root.left);
      int right = pathMaxHelper(root.right);
      return left + right + root.data;
   }


   private static int pathMaxHelper(Node node) {

      if (node == null) {
         return 0;
      }

      int left = pathMaxHelper(node.left);
      int right = pathMaxHelper(node.right);

      int max = Integer.max(left, right);

      return Math.max(max + node.data, 0);
   }


   // check weather sum of left and right child sub tree is equal to root or not
   private static boolean checkingSumEqualRoot(Node root) {

//      Integer[] arrSmall = new Integer[]{5, 3, 2, null, null, 1, 1,
//              null, null, null, 2, 1, null, 1, null, null, 1, null, null};


      if (root == null) return false;
      Queue<Node> queue = new ArrayDeque<>();
      queue.add(root);
      while (!queue.isEmpty()) {
         int size = queue.size();
         for (int i = 0; i < size; i++) {
            Node node = queue.remove();
            int sum = 0;
            boolean isBothNull = true;
            if (node.left != null) {
               queue.add(node.left);
               sum += node.left.data;
               isBothNull = false;
            }
            if (node.right != null) {
               queue.add(node.right);
               sum += node.right.data;
               isBothNull = false;
            }
            if (sum != node.data && !isBothNull) return false;
         }
      }
      return true;
   }

   private static int mirrorBTMulSumV2(Node left, Node right, int sum) {
//      System.out.println(mirrorSum(root.left, root.right, 0) + (root.data * root.data));
      if (left == null || right == null) {
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

   // using BFS for left view of binary tree
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
            if (node.left != null) queue.add(node.left); // node.right above and left below makes it right view
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
