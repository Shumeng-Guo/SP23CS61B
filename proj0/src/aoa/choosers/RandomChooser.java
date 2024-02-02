package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        if (wordLength < 1) {
            throw new IllegalArgumentException();
        }
        List<String> wordsOfLength = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (wordsOfLength.isEmpty()) {
            throw new IllegalStateException();
        }

//        StdRandom.setSeed(100);
        int randomNum = StdRandom.uniform(wordsOfLength.size());
        chosenWord = wordsOfLength.get(randomNum);

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
        int occurrences = 0;
        int wordLength = chosenWord.length();
        char[] patternChar = pattern.toCharArray();
        for (int i = 0; i < wordLength; i++) {
            if (chosenWord.charAt(i) == letter) {
                occurrences += 1;
                // everytime call makeGuess, change the pattern
                patternChar[i] = letter;
            }
        }
        pattern = String.valueOf(patternChar);
        return occurrences;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}
