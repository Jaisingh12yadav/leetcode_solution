class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] factorCount = new int[maxFactor + 1];
        for (int[] item : items) {
            factorCount[item[0]]++;
        }

        int[] multipleCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (factorCount[f] == 0) continue;
            for (int m = f; m <= maxFactor; m += f) {
                multipleCount[f] += factorCount[m];
            }
        }

        int[] dp = new int[budget + 1];
        for (int[] item : items) {
            int price = item[1];
            int gain = multipleCount[item[0]];
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + gain);
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b] + (budget - b) / minPrice);
        }
        return ans;
    }
}