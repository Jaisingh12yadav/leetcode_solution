// class Solution {
//     public boolean isUgly(int n) {
//         if(n<=0){
//             return false;
//         }
        
//       for(int i =7;i<n;i++){
//         if(n%i!=0){
//             return true;
            
//         }
//         else{
//             return false;
            
//         }
//       }
//       return true;
//     }
// }

class Solution {
    public boolean isUgly(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        
        // If n becomes 1, it's ugly (only factors were 2, 3, 5)
        return n == 1;
    }
}