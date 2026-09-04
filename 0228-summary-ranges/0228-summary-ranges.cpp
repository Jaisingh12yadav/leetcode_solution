class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        int n=nums.size();
        vector<string> ans;
        for(int i=0;i<n;i++)
        {
            int a,b;
            a=nums[i];
            while(i+1<n && nums[i+1]==nums[i]+1)
            {
                i++;
            }
            b=nums[i];
            if(a!=b)
            {
                ans.push_back(to_string(a)+"->"+to_string(b));
            }
            else if(a==b)
            {
                ans.push_back(to_string(a));
            }
        }
        return ans;
    }
};