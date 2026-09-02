class Solution {
    public boolean uniformArray(int[] nums1) {
        int n= nums1.length;
        boolean ans = false;
        int count1 =0;
        int count2 = 0;
        int [] nums2 = new int [n];
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n;j++){
                nums2[j] = nums1[i] - nums1[i+1];
            }
        }
         for(int j=0;j<n;j++){
            if(nums2[j]%2==0){
                count1++;
            }
            else{
                count2++;
            }
         }
         if(count1 == n || count2 == n){
            ans = true;
         }
         else{
            ans = false;
         }
         return ans;
    }
}