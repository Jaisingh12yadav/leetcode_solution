class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
       int n= nums.length;
       boolean [] hash = new boolean[n+1];
       for(int num:nums){
        hash[num] = true;
       }
       for(int i=1;i<=n;i++){
        if(!hash[i]){
            ans.add(i);
        }
       }
       return ans;
    }
}