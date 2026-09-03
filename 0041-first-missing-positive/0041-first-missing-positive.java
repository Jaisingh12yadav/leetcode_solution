class Solution {
    public int firstMissingPositive(int[] a) {
       HashSet<Integer> set = new HashSet<>();
       long max = 0;
       for(int x :a){
        set.add(x);
        max = Math.max(max,x);
       }
       int x = 1;
       while(x<=max+1){
        if(!set.contains(x)){
            return x;
        }
        x++;
       }
       return -1;
    }
}