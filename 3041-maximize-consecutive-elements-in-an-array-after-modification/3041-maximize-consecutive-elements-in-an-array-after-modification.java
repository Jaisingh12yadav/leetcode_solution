import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int keepX1 = dp.getOrDefault(x, 0) + 1;
            int prevX1 = dp.getOrDefault(x + 1, 0);
            dp.put(x + 1, Math.max(prevX1, keepX1));

            int keepX = dp.getOrDefault(x - 1, 0) + 1;
            int prevX = dp.getOrDefault(x, 0);
            dp.put(x, Math.max(prevX, keepX));

            ans =  Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }
        return ans;
    }
}