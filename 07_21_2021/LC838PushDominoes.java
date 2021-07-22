class LC838PushDominoes {
    public String pushDominoes(String dominoes) {
        char[] res = new char[dominoes.length()];
        //Step1: handle R...L Case
        //Step2: handle the other simple cases
        for (int i = 0; i < dominoes.length(); i++) {
            res[i] = dominoes.charAt(i);
            if (dominoes.charAt(i) == 'R') {
                // update indexR and get indexL
                int indexL = i + 1, indexR = i;
                while(indexL < dominoes.length() && dominoes.charAt(indexL) != 'L') {
                    if (dominoes.charAt(indexL) == 'R') {
                        indexR = indexL;
                    }
                    res[indexL] = dominoes.charAt(indexL);
                    indexL++;
                }
                if (indexL == dominoes.length()) {
                    break; // not find next L
                }
                i = indexL;
                int mid = indexR + (indexL - indexR)/2;
                if ((indexL - indexR - 1) % 2 == 0) {
                    for (int j = indexR; j <= mid; j++) {
                        res[j] = 'r';
                    }
                    for (int j = indexL; j > mid; j--) {
                        res[j] = 'l';
                    }
                } else {
                    for (int j = indexR; j < mid; j++) {
                        res[j] = 'r';
                    }
                    for (int j = indexL; j > mid; j--) {
                        res[j] = 'l';
                    }
                }
            }
        }
        
        for (int i = 0; i < res.length; i++) { // ignore lowercase now
            if (res[i] == 'L') {
                int index = i - 1;
                while(index >= 0 && res[index] == '.') {
                    res[index--] = 'L';
                }
            }
            if (res[i] == 'R') {
                int index = i + 1;
                System.out.println(index);
                while(index <= res.length - 1 && res[index] == '.') {
                    System.out.println(index);
                    res[index++] = 'R';
                }
            }
            if (res[i] == '\0') res[i] = '.';
        }
        
        return new String(res).toUpperCase();
    }
}
