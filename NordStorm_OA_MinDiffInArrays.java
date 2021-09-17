package dolare.main.others;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NordStorm_OA_MinDiffInArrays {
    public static void main(String[] args) {
        int[][] test1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] test2 = {{1, 5, 9}, {2, 3, 4}, {-10, 0, 11}};
        int[][] test3 = {{-2, 8, 11}, {7, 33, 90}, {-10, -7, 12}};
        System.out.println(new NordStorm_OA_MinDiffInArrays().minDiff(test1));
        System.out.println(new NordStorm_OA_MinDiffInArrays().minDiff(test2));
        System.out.println(new NordStorm_OA_MinDiffInArrays().minDiff(test3));
    }

    public List<Integer> minDiff (int[][] arr) {
        int m = arr.length, n = arr[0].length;
        List<ArrayIndexToVaue> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(new ArrayIndexToVaue(i, arr[i][j]));
            }
        }

        Collections.sort(list, (a, b) -> a.value - b.value);

        int left = 0, right = m * n - 1;

        // store numbers of the elements left in the array
        int[] counts = new int[m];
        Arrays.fill(counts, n); // each array has n elements at begining

        List<Integer> solution1 = scanSortedArray(left, right, list, counts, 0);
        List<Integer> solution2 = scanSortedArray(left, right, list, counts, 1);
//        System.out.println(solution1);
//        System.out.println(solution2);
        int diff1 = solution1.get(solution1.size() - 1) - solution1.get(0);
        int diff2 = solution2.get(solution2.size() - 1) - solution2.get(0);

        return diff1 > diff2 ? solution2 : solution1;
    }


    public List<Integer> scanSortedArray(int left, int right, List<ArrayIndexToVaue> list, int[] counts, int direction) {
        List<Integer> solution = new ArrayList<>();
        if (direction == 0) { // scan from left to right first
            int[] countLocal = Arrays.copyOf(counts, counts.length), selected = new int[counts.length];
            while(left < right) {
                int elementInArr = list.get(left).arrNumber;
                countLocal[elementInArr]--;
                if(countLocal[elementInArr] == 0) {
                    break;
                }
                left++;
            }

            while(left < right) {
                int elementInArr = list.get(right).arrNumber;
                countLocal[elementInArr]--;
                if(countLocal[elementInArr] == 0) {
                    break;
                }
                right--;
            }
            // construct  possible solution
            while(left <= right) {
                int elementInArr = list.get(left).arrNumber;
                if (selected[elementInArr]++ == 0) {
                    solution.add(list.get(left).value);
                }
                left++;
            }
        } else {
            int[] countLocal = Arrays.copyOf(counts, counts.length), selected = new int[counts.length];
            while(left < right) {
                int elementInArr = list.get(right).arrNumber;
                countLocal[elementInArr]--;
                if(countLocal[elementInArr] == 0) {
                    break;
                }
                right--;
            }
            while(left < right) {
                int elementInArr = list.get(left).arrNumber;
                countLocal[elementInArr]--;
                if(countLocal[elementInArr] == 0) {
                    break;
                }
                left++;
            }
            while(left <= right) {
                int elementInArr = list.get(left).arrNumber;
                if (selected[elementInArr]++ == 0) {
                    solution.add(list.get(left).value);
                }
                left++;
            }
        }

        return solution;
        // scan from right to left first
    }

    class ArrayIndexToVaue {
        int arrNumber;
        int value;

        public ArrayIndexToVaue(int arrNumber, int value) {
            this.arrNumber = arrNumber;
            this.value = value;
        }
    }
}
