class Solution {
    public boolean canAliceWin(int n) {
        int take = 10;
        int count = 0;

        while (n >= take) {
            n -= take;
            take--;
            count++;
        }

        return count % 2 == 1;
    }
}