class LC42TrappingRainWater {
    public int trap(int[] height) {
        // idea: for each single spot, the area = height * width
        // width = 1 beacause it is single spot
        // height = Math.min(leftMax, rightMax) - heightItself
        // corner case: height < 0 -> 0
        int n = height.length;

        int[] leftMax = height.clone();
        int[] rightMax = height.clone();

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;
        for(int i = 0; i < n; i++) {
            int area = Math.min(leftMax[i], rightMax[i]) - height[i];
            res += area >= 0? area : 0;
        }

        return res;

    }
}