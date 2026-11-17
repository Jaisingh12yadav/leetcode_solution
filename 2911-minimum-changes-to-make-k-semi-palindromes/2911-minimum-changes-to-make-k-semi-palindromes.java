
import java.util.Arrays;

class Solution {

    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1000000;

        // Step 1: Precompute substring costs
        int[][] cost = new int[n][n];

        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        for (int len = 2; len <= n; len++) {

            for (int l = 0; l + len <= n; l++) {

                int r = l + len - 1;

                // Try all proper divisors
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    int groupLength = len / d;

                    // Check every group
                    for (int i = 0; i < d; i++) {

                        int left = l + i;
                        int right = l + i
                                + (groupLength - 1) * d;

                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        // Step 2: Dynamic Programming
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {

            for (int i = 2 * parts; i <= n; i++) {

                for (int p = 2 * (parts - 1);
                     p <= i - 2; p++) {

                    if (dp[p][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        dp[p][parts - 1] + cost[p][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
