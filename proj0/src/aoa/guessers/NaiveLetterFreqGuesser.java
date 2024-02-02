package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
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
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = this.getFrequencyMap();
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

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
