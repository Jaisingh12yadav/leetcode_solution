class Solution {
    public int findNumbers(int[] nums) {
       int count =0;
       for(int num:nums){
        int digit =0;
        int temp= num;
       
       while(temp>0){
        digit++;
        temp/=10;
       }
       if(digit%2==0){
        count++;
       }
       }
       return count;
    }
}