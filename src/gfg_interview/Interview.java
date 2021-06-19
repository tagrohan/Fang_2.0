package gfg_interview;

public class Interview {
   public static void main(String[] args) {
      System.out.println(squareRoot(9));
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
