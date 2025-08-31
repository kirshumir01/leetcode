package medium;

/*
#91 DECODE WAYS

You have intercepted a secret message encoded as a string of numbers.
The message is decoded via the following mapping:
"1" -> 'A'
"2" -> 'B'
...
"25" -> 'Y'
"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode
the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:
"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)

The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).

Note: there may be strings that are impossible to decode.
Given a string s containing only digits, return the number of ways to decode it.
If the entire string cannot be decoded in any valid way, return 0.
The test cases are generated so that the answer fits in a 32-bit integer.
 */

public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.isBlank() || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // В ASCI-II содержатся следующие коды для цифр
            // '0' = 48
            // '1' = 49
            // '2' = 50
            // т.е. '9' - '0' = 57 - 48 = 9
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit != 0) {
                dp[i] += dp[i - 1];
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        String s = "226";
        System.out.println(decodeWays.numDecodings(s)); // 3
    }
}