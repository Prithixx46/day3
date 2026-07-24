import java.util.Arrays;

class Solution {
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        long mod = 1000000007L;
        long ans = 0;
        long sum = 0;

        for (int num : nums) {
            ans = (ans + (long) num * num % mod * (num + sum) % mod) % mod;
            sum = (sum * 2 + num) % mod;
        }

        return (int) ans;
    }
}