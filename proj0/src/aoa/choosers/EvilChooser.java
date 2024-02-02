package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        if (wordLength < 1) {
            throw new IllegalArgumentException();
        }
        wordPool = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (wordPool.isEmpty()) {
            throw new IllegalStateException();
        }

        // set the original pattern wordLength of "-"
        char[] patternChar = new char[wordLength];
        for (int i = 0; i < wordLength; i++) {
            patternChar[i] = '-';
        }
        pattern = String.valueOf(patternChar);
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        // Return the number of occurrences of the guessed letter in the new pattern
        // update pattern and wordPool

        Map<String, List<String>> family = new TreeMap<>();
        int wordLength = wordPool.get(0).length();

        // Fill the family treemap (pattern : {words})
        for (String word : wordPool) {
            String wordPattern = EvilChooserHelper.getPatternGivenLetter(word, pattern, letter, wordLength);
            if (! family.containsKey(wordPattern)) {
                List<String> wordsWithPattern = new ArrayList<>();
                family.put(wordPattern, wordsWithPattern);
            }
            family.get(wordPattern).add(word);
        }
        System.out.println(family);

        // get the pattern with the biggest family, update wordPool
        int biggestSize = 0;
        String selectedPattern = "";
        for (String p : family.keySet()) {
            int curSize = family.get(p).size();
            if ( curSize > biggestSize) {
                selectedPattern = p;
                biggestSize = curSize;
            }
        }
        wordPool = family.get(selectedPattern);
        pattern = String.valueOf(selectedPattern);
        return EvilChooserHelper.numberOfOccurrences(letter, pattern);
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return "";
    }


    public static void main(String[] args) {
        EvilChooser ec = new EvilChooser(4, "data/example.txt");
        System.out.println(ec.wordPool);
        System.out.println(ec.pattern);
        System.out.println(ec.makeGuess('e'));
        System.out.println(ec.wordPool);
        System.out.println(ec.pattern);
        System.out.println(ec.makeGuess('o'));
        System.out.println(ec.wordPool);
        System.out.println(ec.pattern);
        System.out.println(ec.makeGuess('t'));
        System.out.println(ec.wordPool);
        System.out.println(ec.pattern);
    }
}
