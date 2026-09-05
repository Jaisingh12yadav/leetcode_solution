
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int length = j - i + 1;
                if (length % 2 == 1) {
                    for (int k = i; k <= j; k++) {
                        sum += arr[k];
                    }
                }
            }
        }

        return sum;
    }
}

