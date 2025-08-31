package easy;

/*
#482 LICENSE KEYS FORMATTING

You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
The string is separated into n + 1 groups by n dashes.
You are also given an integer k.

We want to reformat the string s such that each group contains exactly k characters, except for the first group,
which could be shorter than k but still must contain at least one character.
Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.

Return the reformatted license key.

Example 1:
Input: s = "5F3Z-2e-9-w", k = 4
Output: "5F3Z-2E9W"
Explanation: The string s has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.

Example 2:
Input: s = "2-5g-3-J", k = 2
Output: "2-5G-3J"
Explanation: The string s has been split into three parts,
each part has 2 characters except the first part as it could be shorter as mentioned above.
 */

public class LicenseKeyFormatting_1 {
    // VAR.1 - right to left:
    public static String licenseKeyFormatting(String s, int k) {
        int count = 0;
        int n = s.length();
        StringBuilder ans = new StringBuilder();

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                ans.append(Character.toUpperCase(s.charAt(i)));
                count++;
                if (count == k) {
                    ans.append('-');
                    count = 0;
                }
            }
        }

        if (ans.length() > 0 && ans.charAt(ans.length() - 1) == '-') {
            ans = new StringBuilder(ans.substring(0, ans.length() - 1));
        }

        ans.reverse();

        return ans.toString();
    }

    public static void main(String[] args) {
        String s1 = "5F3Z-2e-9-w";
        int k1 = 4;

        String s2 = "2-5g-3-J";
        int k2 = 2;

        System.out.println(licenseKeyFormatting(s1, k1)); // must be: "5F3Z-2E9W"
        System.out.println(licenseKeyFormatting(s2, k2)); // must be: "2-5G-3J"
    }
}
