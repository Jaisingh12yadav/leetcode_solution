
import java.util.Arrays;

class Solution {

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        // Step 1: Build Suffix Array
        int[] sa = buildSuffixArray(nums);

        // Step 2: Build inverse suffix array
        int[] position = new int[n];

        for (int i = 0; i < n; i++) {
            position[sa[i]] = i;
        }

        // Step 3: Build LCP array using Kasai's algorithm
        int[] lcp = new int[n];

        int common = 0;

        for (int i = 0; i < n; i++) {

            int pos = position[i];

            if (pos == 0) {
                continue;
            }

            int j = sa[pos - 1];

            while (i + common < n &&
                   j + common < n &&
                   nums[i + common] == nums[j + common]) {

                common++;
            }

            lcp[pos] = common;

            if (common > 0) {
                common--;
            }
        }

        // Step 4: Find minimum unique subarray length
        int answer = n;

        for (int pos = 0; pos < n; pos++) {

            int start = sa[pos];

            int leftLCP = lcp[pos];

            int rightLCP = 0;

            if (pos + 1 < n) {
                rightLCP = lcp[pos + 1];
            }

            int maxLCP = Math.max(leftLCP, rightLCP);

            int uniqueLength = maxLCP + 1;

            // Unique prefix must fit inside this suffix
            if (uniqueLength <= n - start) {
                answer = Math.min(answer, uniqueLength);
            }
        }

        return answer;
    }

    // Builds suffix array using doubling + counting sort
    private int[] buildSuffixArray(int[] nums) {

        int n = nums.length;

        int[] sa = new int[n];
        int[] temp = new int[n];

        int[] rank = new int[n];
        int[] newRank = new int[n];

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] unique = new int[n];

        int classes = 0;

        for (int value : sorted) {

            if (classes == 0 || unique[classes - 1] != value) {
                unique[classes++] = value;
            }
        }

        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(unique, 0, classes, nums[i]);
            sa[i] = i;
        }

        // Doubling technique
        for (int k = 1; k < n; k <<= 1) {

            // Sort by second rank
            countingSort(sa, temp, rank, k, classes);

            // Sort by first rank
            countingSort(temp, sa, rank, 0, classes);

            int newClasses = 1;

            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {

                int current = sa[i];
                int previous = sa[i - 1];

                int currentFirst = rank[current];
                int previousFirst = rank[previous];

                int currentSecond =
                    current + k < n ? rank[current + k] : -1;

                int previousSecond =
                    previous + k < n ? rank[previous + k] : -1;

                if (currentFirst != previousFirst ||
                    currentSecond != previousSecond) {

                    newClasses++;
                }

                newRank[current] = newClasses - 1;
            }

            int[] swap = rank;
            rank = newRank;
            newRank = swap;

            classes = newClasses;

            if (classes == n) {
                break;
            }
        }

        return sa;
    }

    // Stable counting sort using rank[index + offset]
    private void countingSort(
            int[] input,
            int[] output,
            int[] rank,
            int offset,
            int classes) {

        int n = input.length;

        int[] count = new int[classes + 1];

        // Count frequency of each key
        for (int index : input) {

            int key;

            if (index + offset < n) {
                key = rank[index + offset] + 1;
            } else {
                key = 0;
            }

            count[key]++;
        }

        // Prefix sum
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Stable placement
        for (int i = n - 1; i >= 0; i--) {

            int index = input[i];

            int key;

            if (index + offset < n) {
                key = rank[index + offset] + 1;
            } else {
                key = 0;
            }

            output[--count[key]] = index;
        }
    }
}
