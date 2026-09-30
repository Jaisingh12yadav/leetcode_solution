class Solution {
    public int lengthOfLongestSubstring(String s) {

        boolean[] seen = new boolean[128];

        int left = 0;
        int count = 0;
        int max = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            while (seen[ch]) {
                seen[s.charAt(left)] = false;
                left++;
                count--;
            }
            seen[ch] = true;
            count++;
            max = Math.max(max, count);
        }

        return max;
    }
}
    
