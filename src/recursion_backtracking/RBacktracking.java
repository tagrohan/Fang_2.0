package recursion_backtracking;

import java.util.ArrayList;
import java.util.List;

public class RBacktracking {

   public static void main(String[] args) {


   }



   private static void printAbbreviation(String str) {

   }


   private static List<String> listOfPermutaionInString(String str, String ans) {
//      System.out.println(Arrays.toString(listOfPermutaionInString("abc", "").toArray()));
      if (str.length() == 0) {
         return List.of(ans);
      }

      List<String> list = new ArrayList<>();
      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         List<String> sub = listOfPermutaionInString(str.substring(0, i) + str.substring(i + 1), ans + ch);
         list.addAll(sub);
      }
      return list;
   }
}
