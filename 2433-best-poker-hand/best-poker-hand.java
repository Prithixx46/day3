class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        // Check Flush
        boolean flush = true;
        for (int i = 1; i < suits.length; i++) {
            if (suits[i] != suits[0]) {
                flush = false;
                break;
            }
        }
        if (flush) return "Flush";

        // Count rank frequencies
        int[] count = new int[14];
        int max = 0;
        for (int rank : ranks) {
            count[rank]++;
            max = Math.max(max, count[rank]);
        }

        if (max >= 3) return "Three of a Kind";
        if (max == 2) return "Pair";
        return "High Card";
    }
}