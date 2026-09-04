class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        int n=nums.size();
        if(nums.empty()) return 0;
        sort(nums.begin(),nums.end());
        int x=nums[0];
        int cnt=1;
        int maxlen=1;
        for(int i=1;i<n;i++)
        {
            if(nums[i]==x+1){
                x+=1;
                cnt++;
                maxlen=max(maxlen,cnt);
            }
            else if(nums[i]!=x){
                x=nums[i];
                cnt=1;
            }
        }
        return maxlen;
    }
};