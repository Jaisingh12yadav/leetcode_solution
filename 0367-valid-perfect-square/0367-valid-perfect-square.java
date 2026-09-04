class Solution {
    public boolean isPerfectSquare(int num) {
        if(num<0) return false;
        long i =1;
        while(i*i<num){
            i++;
        }
        return i*i == num;
    }
}