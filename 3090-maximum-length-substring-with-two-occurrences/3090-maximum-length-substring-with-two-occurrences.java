class Solution {
    public int maximumLengthSubstring(String s) {

        int[] freq = new int[26];

        int left = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {

            int r = s.charAt(right) - 'a';
            freq[r]++;

            while (freq[r] > 2) {
                int l = s.charAt(left) - 'a';
                freq[l]--;
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}