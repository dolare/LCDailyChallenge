public class LC850RectangleAreaII {
    private final long MOD = 1000000007;
    public int rectangleArea(int[][] rectangles) {
        // Solution: sweep line from y direction
        long area = 0;
        List<int[]> interval = new ArrayList(); //int[0]: y1/y2  int[1]: x1 int[2]: x2, int[3]: high/low

        for (int[] rectangle: rectangles) {
            interval.add(new int[]{rectangle[1], rectangle[0], rectangle[2], 1});
            interval.add(new int[]{rectangle[3], rectangle[0], rectangle[2], -1});
        }

        Collections.sort(interval, (a, b) -> a[0] - b[0]);

        List<int[]> curr = new ArrayList();
        long lastY = 0;
        for (int[] bar: interval) {
            long maxWidth = 0, start = 0, end = 0;
            for (int[] c: curr) {
                start = Math.max(end, c[0]);
                maxWidth += Math.max(0, c[1] - start);
                end = Math.max(end, c[1]);
            }
            area += maxWidth * (bar[0] - lastY);

            if (bar[3] == 1) { // new rectangle start
                curr.add(new int[]{bar[1], bar[2]});
            } else {
                for (int i = 0; i < curr.size(); i++) {
                    if (curr.get(i)[0] == bar[1] && curr.get(i)[1] == bar[2]) {
                        curr.remove(i);
                    }
                }
            }
            Collections.sort(curr, (a, b) -> a[0] - b[0]);
            lastY = bar[0];
        }

        return (int)(area % MOD);
    }
}