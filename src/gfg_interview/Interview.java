package gfg_interview;

public class Interview {
   public static void main(String[] args) {
      System.out.println(power(2, 0));
   }

   private static int power(int co, int po) {
      int res = 1;
      for (int i = 1; i <= po; i++) {
         res *= co;
      }
      return res;
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
