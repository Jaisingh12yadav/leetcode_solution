// class Solution {
//     public int firstStableIndex(int[] nums, int k) {
//         if (nums == null || nums.length < 2) {
//             return -1;  // Need at least 2 elements
//         }
//         long max = Long.MIN_VALUE;  
//         long min = Long.MAX_VALUE;  
        
//         // for (int i = 0; i < nums.length; i++) { 
//             max = Math.max(max, nums[i]);
//             min = Math.min(min, nums[i]);
            
//             if ( i >0 && (max - min) <= k) {
//                 return i;
//             }
//         }
//         return -1;
//     }
// }

class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }
        int prefixMax = nums[0];
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}