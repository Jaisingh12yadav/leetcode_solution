import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // 1. Store indices
        Integer[] idx = new Integer[n];

        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        // 2. Sort indices according to nums values
        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        // 3. Find groups
        for (int i = 0; i < n; ) {

            int j = i + 1;

            while (j < n &&
                   (long) nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }

            // 4. Get original indices of this group
            Integer[] positions = Arrays.copyOfRange(idx, i, j);

            // 5. Sort positions
            Arrays.sort(positions);

            // 6. Put smallest values into smallest positions
            for (int k = i; k < j; k++) {
                ans[positions[k - i]] = nums[idx[k]];
            }

            i = j;
        }

        return ans;
    }
}