package aoa.guessers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LFGHelper {
    public static List<String> getWordsListThatMatchesPattern(List<String> words, String pattern) {
        List<String> wordsList = new ArrayList<>();
        Boolean matchPattern = true;
        int patternLength = pattern.length();
        for (String word : words) {
            if (word.length() != patternLength) {
                continue;
            }
            for (int i = 0; i < patternLength; i++) {
                char patternChar = pattern.charAt(i);
                char wordChar = word.charAt(i);
                if (patternChar != '-') {
                    if (wordChar != patternChar) {
                        matchPattern = false;
                        break;
                    }
                }
            }
            if (matchPattern) {
                wordsList.add(word);
            }
            matchPattern = true;
        }
        return wordsList;
    }

    /** Convert a list of words to a frequency map */
    public static Map<Character, Integer> getFreqMap(List<String> words) {
        Map<Character, Integer> freqMap = new TreeMap<>();
        for (String word : words) {
            for (Character c : word.toCharArray()) {
                if (freqMap.containsKey(c)) {
                    int val = freqMap.get(c);
                    val += 1;
                    freqMap.put(c, val);
                } else {
                    freqMap.put(c, 1);
                }
            }
        }
        return freqMap;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public static char getGuess(List<Character> guesses, Map<Character, Integer> freqMap) {
        int mostCommon = 0;
        Character mostCommonChar = null;
        for (Character key : freqMap.keySet()) {
            if (!guesses.contains(key)) {
                int val = freqMap.get(key);
                if (val > mostCommon) {
                    mostCommon = val;
                    mostCommonChar = key;
                }
            }
        }
        return mostCommonChar;
    }
}
