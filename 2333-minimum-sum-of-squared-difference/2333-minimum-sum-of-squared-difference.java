class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        int[] diffs = new int[n];
        long total = 0;
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            total += diffs[i];
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        
        if (total <= k) return 0;
        
        int lo = 0, hi = maxDiff;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long ops = 0;
            for (int d : diffs) {
                if (d > mid) ops += d - mid;
            }
            if (ops <= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        int target = lo;
        
        long opsUsed = 0;
        for (int d : diffs) {
            if (d > target) opsUsed += d - target;
        }
        long leftover = k - opsUsed;
        
        long ans = 0;
        long atTarget = 0; // count of elements that will sit at `target` after capping
        for (int d : diffs) {
            if (d >= target) {
                atTarget++;
            } else {
                ans += (long) d * d;
            }
        }
        
        // Distribute leftover among elements at `target`
        long reduced = Math.min(leftover, atTarget); // reduce this many from target to target-1
        long stayed = atTarget - reduced;
        
        ans += stayed * (long) target * target;
        if (target > 0) {
            ans += reduced * (long) (target - 1) * (target - 1);
        }
        // if target == 0, reduced elements stay at 0 (can't go negative)
        
        return ans;
    }
}