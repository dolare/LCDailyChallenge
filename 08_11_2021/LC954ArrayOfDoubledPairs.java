import java.util.Map;
import java.util.TreeMap;

public class LC954ArrayOfDoubledPairs {
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new TreeMap();
        int n = arr.length;
        int all = 0;

        for (int i: arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // handle positive/negative number
        for (int i : map.keySet()) {
            System.out.println(i);
            if (i >= 0) {
                if (map.get(i) > 0 && map.containsKey(i * 2) && map.get(i * 2) > 0) {
                    int count = i == 0 ? map.get(i)/2 : map.get(i);  // corner case i == 0
                    map.put(i, map.get(i) - count);
                    map.put(i * 2, map.get(i * 2) - count);
                    if (map.get(i * 2) < 0) return false;
                    all += count * 2;
                }  else if (map.get(i) > 0 && !map.containsKey(i * 2)){
                    return false;
                }
            }
            if (i < 0) {
                if (map.get(i) > 0 && map.containsKey(i / 2) && map.get(i / 2) > 0) {
                    if (i % 2 != 0) return false;
                    int count = map.get(i);
                    map.put(i, map.get(i) - count);
                    map.put(i / 2, map.get(i / 2) - count);
                    if (map.get(i / 2) < 0) return false;
                    all += count * 2;
                } else if (map.get(i) > 0 && !map.containsKey(i / 2)){
                    return false;
                }
            }
        }
        System.out.println(all);
        return all == n;
    }
}