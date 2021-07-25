class LC126WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // BFS + DFS: shortest path
        Set<String> wordsSet = new HashSet();
        Set<String> checkDuplicates = new HashSet();
        
        Map<String, List<String>> graph = new HashMap();
        
        for(String s: wordList) {
            wordsSet.add(s);
        }
        wordsSet.add(beginWord);
        // build graph
        for (String str: wordsSet) {
            graph.put(str, new ArrayList());
            for (int i = 0; i < str.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char[] charArray = str.toCharArray();
                    charArray[i] = c;
                    String newStr = new String(charArray);
                    if (!newStr.equals(str) && wordsSet.contains(newStr)) {
                        graph.get(str).add(newStr);
                    }
                }
            }
        }
        
        // use bfs to find the length of the transformation
        Queue<String> queue = new LinkedList();
        queue.offer(beginWord);
        checkDuplicates.add(beginWord);
        int deep = 0;
        boolean isFound = false;
        while(!queue.isEmpty()) {
            List<String> wordsByLevel = new ArrayList();
            deep++;
            while(!queue.isEmpty()) {
                String newString = queue.poll();
                wordsByLevel.add(newString);
                if (newString.equals(endWord)) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) break;
            // System.out.println(wordsByLevel);
            for (String word: wordsByLevel) {
                for (String str: graph.get(word)) {
                    if (!checkDuplicates.contains(str)) {
                        queue.add(str);
                        checkDuplicates.add(str);
                    }
                }
            }
        }
        
        // use dfs to find solutions
        checkDuplicates = new HashSet();
        List<List<String>> res = new ArrayList();
        if (!isFound) return res;
        List<String> solution = new ArrayList();
        solution.add(endWord);
        System.out.println(deep);
        dfs(solution, res, graph, beginWord, endWord, deep, checkDuplicates);
        
        return res;
        
    }
    
    public void dfs(List<String> solution, List<List<String>> res, Map<String, List<String>> graph, String endWord, String currWord, int deep, Set<String> checkDuplicates) {
        if (solution.size() == deep) {
            if (solution.get(solution.size() - 1).equals(endWord)) {
                // System.out.println(solution);
                List<String> realSolution = new ArrayList(solution);
                Collections.reverse(realSolution);
                res.add(realSolution);
            }
            return;
        }
        
        for (String str: graph.get(currWord)) {
            if (checkDuplicates.contains(str)) continue;
            solution.add(str);
            // System.out.println(str);
            checkDuplicates.add(str);
            dfs(solution, res, graph, endWord, str, deep, checkDuplicates);
            checkDuplicates.remove(str);
            solution.remove(solution.size() - 1);
        }
    }
    
}
