package medium;

/*
# 139. Word Break

Given a string s and a dictionary of strings wordDict, return true if s can be segmented
into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordBreak {
    public static class Solution1 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> words = new HashSet<>(wordDict);
            Queue<Integer> queue = new LinkedList<>();
            boolean[] seen = new boolean[s.length() + 1];
            queue.add(0);

            while (!queue.isEmpty()) {
                int start = queue.remove();
                if (start == s.length()) {
                    return true;
                }

                for (int end = start + 1; end <= s.length(); end++) {
                    if (seen[end]) {
                        continue;
                    }

                    if (words.contains(s.substring(start, end))) {
                        queue.add(end);
                        seen[end] = true;
                    }
                }
            }
            return false;
        }
    }

    public static class Solution2 {
        private String s;
        private List<String> wordDict;
        private int[] memo;

        private boolean dp(int i) {
            if (i < 0) return true;

            if (memo[i] != -1) {
                return memo[i] == 1;
            }

            for (String word : wordDict) {
                if (i - word.length() + 1 < 0) {
                    continue;
                }

                if (
                        s.substring(i - word.length() + 1, i + 1).equals(word) &&
                                dp(i - word.length())
                ) {
                    memo[i] = 1;
                    return true;
                }
            }
            memo[i] = 0;
            return false;
        }

        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            this.memo = new int[s.length()];
            Arrays.fill(this.memo, -1);
            return dp(s.length() - 1);
        }
    }

    public static class Solution4 {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (String word : wordDict) {
                    if (i < word.length() - 1) {
                        continue;
                    }

                    if (i == word.length() - 1 || dp[i - word.length()]) {
                        if (
                                s.substring(i - word.length() + 1, i + 1).equals(word)
                        ) {
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }
            return dp[s.length() - 1];
        }
    }
}
