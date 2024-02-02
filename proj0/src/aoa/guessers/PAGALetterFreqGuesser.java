package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List<String> wordsList = getWordsList(pattern, guesses);
        Map<Character, Integer> freqMap = LFGHelper.getFreqMap(wordsList);
        return LFGHelper.getGuess(guesses, freqMap);
    }

    public List<String> getWordsList(String pattern, List<Character> guesses) {
//        List<String> wordsThatMatchesPattern = LFGHelper.getWordsListThatMatchesPattern(words, pattern);
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
                } else {
                  if (pattern.indexOf(wordChar) != -1) {
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
        List<String> wordsListCopy = new ArrayList<>(wordsList);
        for (Character g : guesses) {
            // check if pattern has g, if not, meaning need to remove any word with g in wordsList
            if (pattern.indexOf(g) == -1) {
                for (String w : wordsListCopy) {
                    // to find the word that contains g, and to remove it
                    if (w.indexOf(g) != -1) {
                        wordsList.remove(w);
                    }
                }
            }
        }
        return wordsList;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.words);
        List<String> wordsList = pagalfg.getWordsList("-o--", List.of('o'));
        System.out.println(wordsList);
        System.out.println(LFGHelper.getFreqMap(wordsList));
        System.out.println(pagalfg.getGuess("-o--", List.of('o')));
    }
}
