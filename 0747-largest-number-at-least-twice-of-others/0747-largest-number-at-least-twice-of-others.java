class Solution {
    public int dominantIndex(int[] nums) {
        int max =-1;
        int smax =-1;
        int maxIndex = 0;
        for(int i=0;i<nums.length;i++){
            if(max<nums[i]){
                smax = max;
                max = nums[i];
                maxIndex = i;
            }
            else if (smax < nums[i]){
                smax = nums[i];
            }
        }
        if(smax*2<=max){
            return maxIndex;
        }
        else {
            return -1;
        }
    }
}