package medium;

/*
#187 REPEATED DNA SEQUENCES

The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings)
that occur more than once in a DNA molecule.
You may return the answer in any order.

Example 1:
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]

Example 2:
Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]

Constraints:
1 <= s.length <= 105
s[i] is either 'A', 'C', 'G', or 'T'.
 */

/* Approach: Rabin-Karp: Constant-time Slice Using Rolling Hash

Algorithm
Iterate over the start position of the sequence: from 1 to N−L.
 - If start == 0, compute the hash of the first sequence s[0: L].
 - Otherwise, compute the rolling hash from the previous hash value.
 - If the hash is in the hashset, one met a repeated sequence, time to update the output.
 - Otherwise, add hash in the hashset.
 - Return output list.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RepeatedDNASequences {
    public static List<String> findRepeatedDnaSequences(String s) {
        int L = 10, n = s.length();
        if (n < L) return new ArrayList<>();

        int a = 4, aL = (int) Math.pow(a, L);

        Map<Character, Integer> toInt = new HashMap<>() {
            {
                put('A', 0);
                put('C', 1);
                put('G', 2);
                put('T', 3);
            }
        };

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = toInt.get(s.charAt(i));
        }

        int h = 0;
        Set<Integer> seen = new HashSet<>();
        Set<String> output = new HashSet<>();

        for (int start = 0; start < n - L + 1; ++start) {
            if (start!=0) {
                h = h * a - nums[start - 1] * aL + nums[start + L - 1];
            } else for (int i = 0; i < L; ++i) {
                h = h * a + nums[i];
            }
            if (seen.contains(h)) output.add(s.substring(start, start + L));
            seen.add(h);
        }
        return new ArrayList<String>(output);
    }

    public static void main(String[] args) {
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        String s2 = "AAAAAAAAAAAAA";

        List<String> result_1 = findRepeatedDnaSequences(s1);
        List<String> result_2 = findRepeatedDnaSequences(s2);

        System.out.println(result_1);
        System.out.println(result_2);
    }
}
