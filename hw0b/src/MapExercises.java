import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> res = new HashMap<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (char c : alphabet.toCharArray()) {
            int val = (int)c - 96;
            res.put(c, val);
        }
        return res;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int val = num * num;
            map.put(num, val);
        }
        return map;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            if (! map.containsKey(w)) {
                map.put(w, 1);
            } else {
                int val = map.get(w);
                val += 1;
                map.put(w, val);
            }
        }
        return map;
    }
}
