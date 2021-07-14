class LC791CustomSortString {
    public String customSortString(String order, String str) {
        // use charArray to store the count
        int[] charCount = new int[26];

        // count letters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if(!order.contains(str.substring(i, i + 1))) {
                sb.append(str.charAt(i));   
            } else {
                 charCount[str.charAt(i) - 'a']++;
            }
        }
        
        // add reordered letters
        for(char c: order.toCharArray()) {
           while(charCount[c - 'a']-- > 0) {
               sb.append(c);
           }
        }
        
        return sb.toString();    
    }
}
