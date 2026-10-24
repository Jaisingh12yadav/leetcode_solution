
class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {
        int start = Math.max(day(arriveAlice), day(arriveBob));
        int end = Math.min(day(leaveAlice), day(leaveBob));

        return Math.max(0, end - start + 1);
    }

    private int day(String date) {
        int[] months = {31, 28, 31, 30, 31, 30,
                        31, 31, 30, 31, 30, 31};

        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));

        for (int i = 0; i < month - 1; i++) {
            day += months[i];
        }

        return day;
    }
}
