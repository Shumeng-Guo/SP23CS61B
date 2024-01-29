import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int res = 0;
        for (int num : L) {
            res += num;
        }
        return res;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> res = new ArrayList<>();
        for (int num : L) {
            if (num % 2 == 0) {
                res.add(num);
            }
        }
        return res;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> res = new ArrayList<>();
        for (int num : L1) {
            if (L2.contains(num)) {
                res.add(num);
            }
        }
        return res;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int res = 0;
        for (String w : words) {
            for (char s : w.toCharArray()) {
                if (s == c) {
                    res += 1;
                }
            }
        }
        return res;
    }
}
