public class LC49GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {

//        hashMap + reconstruct string and put them to hashMap
//
//        O(n * k) + O(n)
        List<List<String>> res = new ArrayList();

        Map<String, List<String>> map = new HashMap();

        for (String str: strs) {
            map.computeIfAbsent(hashString(str), v -> new ArrayList()).add(str);
        }

        for (List<String> list: map.values()) {
            res.add(list);
        }

        return res;

    }

    public String hashString(String str) {
        int[] map = new int[101];

        for (char c: str.toCharArray()) {
            map[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            sb.append(i + ":" + map[i] + ",");
        }

        return sb.toString();
    }
}
