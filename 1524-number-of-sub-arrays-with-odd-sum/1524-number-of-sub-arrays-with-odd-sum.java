
class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;

        long even = 1;
        long odd = 0;
        long result = 0;
        int prefix = 0;

        for (int num : arr) {
            prefix += num;
            prefix %= 2;

            if (prefix == 1) {
                result = (result + even) % mod;
                odd++;
            } else {
                result = (result + odd) % mod;
                even++;
            }
        }

        return (int) result;
    }
}
