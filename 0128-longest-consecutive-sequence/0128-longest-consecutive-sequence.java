class Solution {
    public int longestConsecutive(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        // Put all elements into HashSet
        for (int x : nums) {
            set.add(x);
        }

        int maxLen = 0;

        for (int x : set) {

            // x is the starting point of a sequence
            if (!set.contains(x - 1)) {

                int curr = x;
                int count = 1;

                while (set.contains(curr + 1)) {
                    curr++;
                    count++;
                }

                maxLen = Math.max(maxLen, count);
            }
        }

        return maxLen;
    }
}