package gfg_interview;

import java.util.Arrays;
import java.util.Stack;

public class Interview {
   public static void main(String[] args) {
      System.out.println(Arrays.toString(peekOfMountains(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
   }

// how from from current mountain next peek is there means index diff is stored
   private static int[] peekOfMountains(int[] arr) {
//      System.out.println(Arrays.toString(peekOfMountains(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
      int len = arr.length;
      int[] res = new int[len];
      Stack<Integer> stack = new Stack<>();
      for (int i = len - 1; i >= 0; i--) {
         while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
            stack.pop();
         }
         if (stack.isEmpty()) {
            res[i] = 0;
         } else {
            res[i] = stack.peek() - i;
         }
         stack.push(i);
      }
      return res;
   }


   private static void printReverse(String str) {
//      printReverse("0001234000");
      boolean firstNonZero = false;
      for (int i = str.length() - 1; i >= 0; i--) {
         if (str.charAt(i) != '0') firstNonZero = true;
         if (firstNonZero) {
            System.out.print(str.charAt(i));
         }
      }
   }

   // moore voting algo used here to get majority
   private static int majorityElement(int[] arr) {
//      System.out.println(majorityElement(new int[]{1, 2, 3, 4, 5, 6, 6, 6, 6, 5, 6, 6, 6}));
      int count = 0, element = -1;
      for (int i = 0; i < arr.length; i++) {
         if (count == 0) {
            count += 1;
            element = arr[i];
         } else if (arr[i] == element) {
            count += 1;
         } else {
            count -= 1;
         }
      }
      return isMajorityThenReturn(element, arr);
   }

   private static int isMajorityThenReturn(int val, int[] arr) {
      int count = 0;
      int len = arr.length;
      for (int var : arr) {
         if (var == val) {
            count += 1;
            if (count > len / 2) {
               return val;
            }
         }
      }
      return -1;
   }

   private static void swap(int firstNumber, int secondNumber) {
      firstNumber += secondNumber;
      secondNumber = firstNumber - secondNumber;
      firstNumber -= secondNumber;
      System.out.println(firstNumber + " " + secondNumber);
   }


   // square root without any function
   private static int squareRoot(int val) {
      int low = 0;
      int high = val;
      int minFlag = 1;

      while (low <= high) {
         int mid = (low + high) / 2; // h = 4 , l = 2
         if (mid * mid > val) {
            high = mid;
         } else if (mid * mid < val) {
            low = mid;
            minFlag = low;
         } else {
            return mid;
         }
      }
      return minFlag;
   }

}
