// class Solution {
//     public int addDigits(int num) {
//         int sum =0;
//         int ans = 0;
//         while(num>0){
//             int digit = num%10;
//             sum+=digit;           
//            num = num/10;        
//     }
//         while(sum>0){
//             int digit = sum%10;
//             ans = ans + digit;
//             sum = sum/10;
//         }
//         return ans;
//     }
// }

class Solution {
    public int addDigits(int num) {
        if (num == 0) return 0;
        return 1 + (num - 1) % 9;
    }
}