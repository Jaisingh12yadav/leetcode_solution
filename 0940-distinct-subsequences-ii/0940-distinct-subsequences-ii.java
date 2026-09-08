// class Solution {
//     public int distinctSubseqII(String s) {

//         int MOD = 1_000_000_007;

//         int[] dp = new int[26];

//         for (char ch : s.toCharArray()) {

//             int index = ch - 'a';

//             int sum = 0;

//             for (int x : dp) {
//                 sum = (sum + x) % MOD;
//             }

//             dp[index] = (sum + 1) % MOD;
//         }

//         int ans = 0;

//         for (int x : dp) {
//             ans = (ans + x) % MOD;
//         }

//         return ans;
//     }
// }

class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1_000_000_007;

        int[] dp = new int[26];

        for (char ch : s.toCharArray()) {

            int index = ch - 'a';

            int sum = 0;

            for (int x : dp) {
                sum = (sum + x) % MOD;
            }

            dp[index] = (sum + 1) % MOD;
        }

        int ans = 0;

        for (int x : dp) {
            ans = (ans + x) % MOD;
        }

        return ans;
    }
}