class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int give = 1;
        int i = 0;

        while (candies > 0) {
            ans[i % num_people] += Math.min(give, candies);
            candies -= give;
            give++;
            i++;
        }

        return ans;
    }
}