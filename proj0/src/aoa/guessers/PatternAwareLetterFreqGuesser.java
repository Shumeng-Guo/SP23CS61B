package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List<String> wordsList = LFGHelper.getWordsListThatMatchesPattern(words, pattern);
        Map<Character, Integer> freqMap = LFGHelper.getFreqMap(wordsList);
        return LFGHelper.getGuess(guesses, freqMap);
    }


    public static void main(String[] args) {
//        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/sorted_scrabble.txt");
//        System.out.println(palfg.words);
//        System.out.println(palfg.getGuess("-o--a-", List.of('o', 'a', 's', 'l')));

        PatternAwareLetterFreqGuesser palfg2 = new PatternAwareLetterFreqGuesser("data/example.txt");
        List<String> wordsList = LFGHelper.getWordsListThatMatchesPattern(palfg2.words, "-o--");
        System.out.println(wordsList);
        System.out.println(LFGHelper.getFreqMap(wordsList));
        System.out.println(palfg2.getGuess("-o--", List.of('o')));

    }
}