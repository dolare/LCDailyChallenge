import java.util.*;

public class LC90SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> res = new HashSet();
        Arrays.sort(nums);
        backtracking(nums, 0, new ArrayList(), res);
        return new ArrayList(res);
    }

    public void backtracking(int[] nums, int index, List<Integer> solution, Set<List<Integer>> res) {
        res.add(new ArrayList(solution));
        for (int i = index; i < nums.length; i++) {
            // if (i > 0 && nums[i] == nums[i - 1]) continue;
            solution.add(nums[i]);
            // System.out.println(solution);
            backtracking(nums, i + 1, solution, res);
            solution.remove(solution.size() - 1);
        }
    }
}