import java.util.*;

class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int size = n / k;

        Arrays.sort(nums);

        Map<Integer, Integer> cost = new HashMap<>();

        // Precompute valid subsets
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) != size) continue;

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            boolean valid = true;
            int last = -1;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (last != -1 && nums[last] == nums[i]) {
                        valid = false;
                        break;
                    }
                    last = i;
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
            }

            if (valid) {
                cost.put(mask, max - min);
            }
        }

        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == Integer.MAX_VALUE) continue;

            int first = 0;
            while ((mask & (1 << first)) != 0) first++;

            for (int sub : cost.keySet()) {
                if ((sub & mask) == 0 && (sub & (1 << first)) != 0) {
                    int next = mask | sub;
                    dp[next] = Math.min(dp[next], dp[mask] + cost.get(sub));
                }
            }
        }

        return dp[(1 << n) - 1] == Integer.MAX_VALUE ? -1 : dp[(1 << n) - 1];
    }
}