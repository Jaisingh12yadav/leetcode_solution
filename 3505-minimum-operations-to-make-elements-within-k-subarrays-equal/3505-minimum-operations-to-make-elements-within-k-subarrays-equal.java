import java.util.*;

class Solution {
    static final long INF = Long.MAX_VALUE / 2;
    
    // Max-heap (smaller half) and min-heap (larger half)
    private PriorityQueue<Integer> left;   // max-heap
    private PriorityQueue<Integer> right;  // min-heap
    private Map<Integer, Integer> delayed; // lazy deletion
    private long sumLeft, sumRight;
    private int sizeLeft, sizeRight;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        
        // Initialize two heaps
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
        delayed = new HashMap<>();
        sumLeft = sumRight = 0;
        sizeLeft = sizeRight = 0;
        
        // Sliding window to calculate cost for each window
        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= x) {
                remove(nums[i - x]);
            }
            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }
        
        // DP: dp[i][j] = minimum cost of selecting j windows from first i elements
        long[][] dp = new long[n + 1][k + 1];
        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (dp[i][j] == INF) continue;
                // Skip current position
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                // Select a window starting at i
                if (j < k && i + x <= n) {
                    dp[i + x][j + 1] = Math.min(dp[i + x][j + 1], 
                                                dp[i][j] + cost[i]);
                }
            }
        }
        
        return dp[n][k];
    }
    
    private void add(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.offer(num);
            sumLeft += num;
            sizeLeft++;
        } else {
            right.offer(num);
            sumRight += num;
            sizeRight++;
        }
        balance();
    }
    
    private void remove(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (!left.isEmpty() && num <= left.peek()) {
            sumLeft -= num;
            sizeLeft--;
        } else {
            sumRight -= num;
            sizeRight--;
        }
        balance();
    }
    
    private void balance() {
        // Ensure left size >= right size, and difference <= 1
        while (sizeLeft > sizeRight + 1) {
            int moved = left.poll();
            sumLeft -= moved;
            sizeLeft--;
            right.offer(moved);
            sumRight += moved;
            sizeRight++;
            prune(left);
        }
        while (sizeRight > sizeLeft) {
            int moved = right.poll();
            sumRight -= moved;
            sizeRight--;
            left.offer(moved);
            sumLeft += moved;
            sizeLeft++;
            prune(right);
        }
    }
    
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
        }
    }
    
    private long getCost() {
        prune(left);
        prune(right);
        int median = left.peek();
        long costLeft = (long) median * sizeLeft - sumLeft;
        long costRight = sumRight - (long) median * sizeRight;
        return costLeft + costRight;
    }
}