package aoa.choosers;

public class EvilChooserHelper {

    public static String getPatternGivenLetter(String word, String currPattern, char letter, int wordLength) {
        char[] patternChar = currPattern.toCharArray();
        for (int i = 0; i < wordLength; i++) {
            if (word.charAt(i) == letter) {
                // everytime call makeGuess, change the pattern
                patternChar[i] = letter;
            }
        }
        return String.valueOf(patternChar);
    }

    public static int numberOfOccurrences(char letter, String pattern) {
        int occurrences = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == letter) {
                occurrences += 1;
            }
        }
        return occurrences;
    }
}
