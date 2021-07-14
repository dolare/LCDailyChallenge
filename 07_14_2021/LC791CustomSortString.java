class LC791CustomSortString {
    public String customSortString(String order, String str) {
        // use charArray to check if letter exist and the count of each letter
        int[] charCount = new int[26];
        Arrays.fill(charCount, -1);
        // use set to store the index that has the letter we want to reorder
        Set<Integer> charIndex = new HashSet();
        for (char c: order.toCharArray()) {
            charCount[c - 'a']++;
        }
        // store index for all the letters in the order
        for (int i = 0; i < str.length(); i++) {
            if (charCount[str.charAt(i) - 'a'] != -1) {
                charIndex.add(i);
                charCount[str.charAt(i) - 'a']++;
            }
        }
        
        // reorder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (charIndex.contains(i)) {
                for (char c: order.toCharArray()) {
                    if (charCount[c - 'a'] > 0) {
                        sb.append(c);
                        charCount[c - 'a']--;
                        break;
                    }
                }
            } else {
                sb.append(str.charAt(i));
            }
        }
        
        return sb.toString();
        
    }
}
