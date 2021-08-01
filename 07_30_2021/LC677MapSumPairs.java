class LC677MapSumPairs {
    
    /** Initialize your data structure here. */
    TrieNode root;
    public LC677MapSumPairs() {
        root = new TrieNode('0');
    }
    
    public void insert(String key, int val) {
        TrieNode curr = root;
        
        for (char c: key.toCharArray()) {
            TrieNode newNode = new TrieNode(c);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, newNode);
            }
            curr = curr.children.get(c);
        }
        
        if (curr.getEnd() > 0) {
            curr.replaceSum(val);
        } else {
            curr.addSum(val);
            curr.addEnd();
        }

    }
    
    public int sum(String prefix) {
        int[] sum = {0};
        TrieNode curr = root;
        for (char c: prefix.toCharArray()) {
            TrieNode newNode = new TrieNode(c);
            if (!curr.children.containsKey(c)) {
                return 0;
            }
            curr = curr.children.get(c);
        }
        
        dfs(sum, curr);
        
        return sum[0];
    }
    
    public void dfs(int[] sum, TrieNode node) {
        
        if (node.getEnd() > 0) {
            sum[0] += node.getSum();
        }
        if (node.children.size() == 0) return;
        for (TrieNode next: node.children.values()) {
            dfs(sum, next);
        }
    }
    
    class TrieNode {
        public Character c;
        int ends = 0;
        long sum = 0;
        Map<Character, TrieNode> children = new HashMap();
        
        TrieNode (Character c) {
            this.c = c;
        }
        
        public void addSum (int val) {
            this.sum += val;
        }
        
        public void replaceSum(int val) {
            this.sum = val;
        }
        
        public void addEnd () {
            this.ends++;
        }
        
        public int getSum() {
            return (int)sum;
        }
        
        public int getEnd() {
            return ends;
        }
        
        // @Override
        // public boolean equals(TrieNode t) {
        //     return t.c.equals(this.c);
        // }
    }
}
