class Solution {

    public int totalNumbers(int[] digits) {

        int[] freq = new int[10];

        // Count frequency of every digit
        for (int digit : digits) {
            freq[digit]++;
        }

        int count = 0;

        for (int i = 1; i <= 9; i++) {

            if (freq[i] == 0) {
                continue;
            }

            // Use i as hundreds digit
            freq[i]--;

            for (int j = 0; j <= 9; j++) {

                if (freq[j] == 0) {
                    continue;
                }

                // Use j as tens digit
                freq[j]--;

                for (int k = 0; k <= 8; k += 2) {

                    if (freq[k] > 0) {
                        count++;
                    }
                }

                // Restore j
                freq[j]++;
            }

            // Restore i
            freq[i]++;
        }

        return count;
    }
}