class LC927ThreeEqualParts {
    public int[] threeEqualParts(int[] arr) {
        
        // approach:  calculate the how many 1 needed for each segment
        // => calculate how many 0 needed by from right segmnet to determin rightStartIndex =>
        // => calculate the leftEndIndex using the count1 and count0 we alrady got
        // => validate the mid segment elements to check if all three segmentString are the same
        int count1 = 0;
        int[] res = {-1, -1};
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) count1++;
        }
        
        if (count1 % 3 != 0) return res;
        if (count1 == 0) return new int[]{0, arr.length - 1};
        count1 = count1 / 3;
        
        int left = 0, right = arr.length -1;
        int leftCount1 = 0, rightCount1 = 0, leftRealCount0 = 0, rightRealCount0 = 0;
        String strLeft = "", strRight = "", strMid = "";
        StringBuilder strRightBuilder = new StringBuilder();
        // 0001110000(left) 01110000 0000 (right)11110000
        while(rightCount1 < count1) {
            strRightBuilder.append(arr[right]);
            if (arr[right--] == 1) {
                rightCount1++;
            } else {
                rightRealCount0++;
            }
        }
        strRight = strRightBuilder.reverse().toString();
        
        // ignore useless left0
        while(arr[left] == 0 && left < right) {
            left++;
        }
        
        while(leftCount1 < count1) {
            strLeft += Integer.toString(arr[left]);
            if (arr[left++] == 1) {
                leftCount1++;
            } else {
                leftRealCount0++;
            }
        }

        // make sure leftRealCount0 == rightRealCount0
        if (leftRealCount0 > rightRealCount0) return res; // no match
        while(leftRealCount0 < rightRealCount0) {
            if (arr[left] == 1) return res; // no match
            else {
                strLeft += Integer.toString(arr[left++]);
                leftRealCount0++;
            }
        }
        System.out.println(leftRealCount0 + " " + rightRealCount0);
        // System.out.println(leftCount1 + " " + rightCount1 + " " + leftRealCount0 + " " + rightRealCount0);
         
        int midStart = left, midEnd = right, midCount1 = 0, midReacCount0 = 0;
        // ingore useless mid0
        while(arr[midStart] == 0 && midStart < midEnd) {
            midStart++;
        }
        while(midStart <= midEnd) {
            strMid += Integer.toString(arr[midStart++]); // find the realMidEnd
            if (strLeft.equals(strMid) && strLeft.equals(strRight)) {
                res[0] = left - 1;
                res[1] = midStart;
                break;
            }
        }
        // System.out.println(strLeft + " " + strRight + " " + strMid);

        return res;
        
        
        
         
    }
}
