class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        // Find sequential prefix sum
        for (int i = 1; i < nums.length && nums[i] == nums[i - 1] + 1; i++) {
            sum += nums[i];
        }

        // Use boolean array instead of HashSet
        boolean[] present = new boolean[51];

        for (int num : nums) {
            present[num] = true;
        }

        // Find smallest missing integer >= sum
        while (sum <= 50 && present[sum]) {
            sum++;
        }

        return sum;
    }
}