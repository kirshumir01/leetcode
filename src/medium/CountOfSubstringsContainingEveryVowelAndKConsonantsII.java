package medium;

import java.util.HashMap;

/*
You are given a string word and a non-negative integer k.
Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants.

Example 1:
Input: word = "aeioqq", k = 1
Output: 0

Explanation:
There is no substring with every vowel.

Example 2:
Input: word = "aeiou", k = 0
Output: 1

Explanation:
The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".

Example 3:
Input: word = "ieaouqqieaouqq", k = 1
Output: 3

Explanation:
The substrings with every vowel and one consonant are:
word[0..5], which is "ieaouq".
word[6..11], which is "qieaou".
word[7..12], which is "ieaouq".

 */
public class CountOfSubstringsContainingEveryVowelAndKConsonantsII {
    public long countOfSubstrings(String word, int k) {
        long numValidSubStrings = 0;
        int start = 0;
        int end = 0;
        HashMap<Character, Integer> vowelCount = new HashMap<>();
        int consonantCount = 0;

        int[] nextCononant = new int[word.length()];
        int nextConsonantIndex = word.length();
        for (int i = word.length() - 1; i >= 0; i--) {
            nextCononant[i] = nextConsonantIndex;
            if (!isVowel(word.charAt(i))) {
                nextConsonantIndex = i;
            }
        }

        while (end < word.length()) {
            char newLetter = word.charAt(end);

            if (isVowel(newLetter)) {
                vowelCount.put(newLetter, vowelCount.getOrDefault(newLetter, 0) + 1);
            } else {
                consonantCount++;
            }

            while (consonantCount > k) {
                char startLetter = word.charAt(start);
                if (isVowel(startLetter)) {
                    vowelCount.put(startLetter, vowelCount.get(startLetter) - 1);
                    if (vowelCount.get(startLetter) == 0) {
                        vowelCount.remove(startLetter);
                    }
                } else {
                    consonantCount--;
                }
                start++;
            }

            while (start < word.length() && vowelCount.keySet().size() == 5 && consonantCount == k) {
                numValidSubStrings += nextCononant[end] - end;
                char startLetter = word.charAt(start);
                if (isVowel(startLetter)) {
                    vowelCount.put(startLetter, vowelCount.get(startLetter) - 1);
                    if (vowelCount.get(startLetter) == 0) {
                        vowelCount.remove(startLetter);
                    }
                } else {
                    consonantCount--;
                }
                start++;
            }
            end++;
        }
        return numValidSubStrings;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e'|| c == 'i' || c == 'o' || c == 'u';
    }
}
