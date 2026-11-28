
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 1: Count item types for each factor
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Step 2: Count items whose factors are multiples of f
        int[] divisibleCount = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            for (int multiple = f;
                 multiple <= maxFactor;
                 multiple += f) {

                divisibleCount[f] += freq[multiple];
            }
        }

        // Step 3: Group boosted copies by price
        TreeMap<Long, Long> boostedByPrice = new TreeMap<>();

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];

            long gain = divisibleCount[factor] - 1;

            if (gain > 0 && price <= 2 * minPrice) {
                boostedByPrice.merge(price, gain, Long::sum);
            }
        }

        long answer = 0;
        long remainingBudget = budget;

        // Step 4: Buy cheapest boosted copies first
        for (Map.Entry<Long, Long> entry
                : boostedByPrice.entrySet()) {

            long price = entry.getKey();
            long available = entry.getValue();

            long canBuy = remainingBudget / price;
            long take = Math.min(available, canBuy);

            remainingBudget -= take * price;
            answer += take * 2;
        }

        // Step 5: Buy normal copies of the cheapest item
        answer += remainingBudget / minPrice;

        return (int) answer;
    }
}
