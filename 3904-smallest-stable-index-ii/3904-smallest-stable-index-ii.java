class Solution {
    public int firstStableIndex(int[] nums, int k) {
        // long min =Long.MAX_VALUES;
        // long max =Long.MIN_VALUES;
        int[] min = new int [nums.length];
        int n = nums.length;
        min[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--){
            min[i] = Math.min(min[i+1],nums[i]);
        }
        int max = nums[0];
        for(int j=0;j<n;j++){
            max = Math.max(nums[j],max);
            if(max-min[j]<=k){
                return j;
            }
        }
        return -1;
    }
}