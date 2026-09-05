class Solution {
    public int largestInteger(int n, int s) {
        if (s < 0 || s > 9 * n) return -1;
        if (s == 0) return 0;
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            int remaining = n - i - 1;
            int maxDigit = Math.min(9, s);
            
            if (s - maxDigit > 9 * remaining) {
                maxDigit = s - 9 * remaining;
            }
            
            if (i == 0) {
                maxDigit = Math.max(1, maxDigit);
            }
            
            result.append(maxDigit);
            s -= maxDigit;
        }
        
        if (s != 0 || (n > 1 && result.charAt(0) == '0')) return -1;
        
        return Integer.parseInt(result.toString());
    }
}