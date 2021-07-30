
// n: number of student,course pairs in the input
// s: number of students
// c: total number of courses being offered (note: The number of courses any student can take is bounded by a small constant)



import java.io.*;
import java.util.*;

public class Solution {
  public List<String> courseOverlap(String[][] studentCoursePairs) {
    if (studentCoursePairs == null || studentCoursePairs.length == 0) {
      return null;
    }
    
    List<String> studentPairWithCommonCourses = new ArrayList<>();
    
    Map<String, List<String>> courses = new HashMap<>();
    
    
    // construct the map based on the input data// O(n)
    for (String[] studentCoursePair: studentCoursePairs) {
      courses.computeIfAbsent(studentCoursePair[0], k -> new ArrayList<>()).add(studentCoursePair[1]);
    }
    
    // get studentId array
    String[] studentIdSet = new String[courses.size()];
    int index = 0;
    // O(s)
    for (String studentId: courses.keySet()) {
       // System.out.println(studentId);
       studentIdSet[index++] = studentId;
    }
    
    // O(s^2*c^2)  O(s) (n) (c) 
     System.out.println(Arrays.toString(studentIdSet));
    for (int i = 0; i < studentIdSet.length; i++) {
      for (int j = i + 1; j < studentIdSet.length; j++) {
        String student1 = studentIdSet[i];
        String student2 = studentIdSet[j];
        String studentPair = student1 + "," + student2;
        List<String> commonCourses = new ArrayList<>();
        List<String> studen1Courses = courses.get(student1);
        List<String> studen2Courses = courses.get(student2);
        
        for (String course1: studen1Courses) { // c
          for (String course2: studen2Courses) { // c
            if (course1.equals(course2)) {
              commonCourses.add(course1);
            }
          }
        }
//         if (commonCourses.size() == 0) continue;
        // System.out.println(commonCourses);
        String solution = studentPair + ": " + commonCourses.toString();
        studentPairWithCommonCourses.add(solution);
      }
    }
    
    return studentPairWithCommonCourses;
  }
  
  public static void main(String[] argv) {
    String[][] studentCoursePairs1 = {
      {"58", "Linear Algebra"},
      {"94", "Art History"},
      {"94", "Operating Systems"},
      {"17", "Software Design"},
      {"58", "Mechanics"},
      {"58", "Economics"},
      {"17", "Linear Algebra"},
      {"17", "Political Science"},
      {"94", "Economics"},
      {"25", "Economics"},
      {"58", "Software Design"}
    };

    String[][] studentCoursePairs2 = {
      {"0", "Advanced Mechanics"},
      {"0", "Art History"},
      {"1", "Course 1"},
      {"1", "Course 2"},
      {"2", "Computer Architecture"},
      {"3", "Course 1"},
      {"3", "Course 2"},
      {"4", "Algorithms"}
    };

    String[][] studentCoursePairs3 = {
      {"23", "Software Design"}, 
      {"3",  "Advanced Mechanics"}, 
      {"2",  "Art History"}, 
      {"33", "Another"},
    };
    
    Solution test = new Solution();
    System.out.println(test.courseOverlap(studentCoursePairs1));
    System.out.println(test.courseOverlap(studentCoursePairs2));
    System.out.println(test.courseOverlap(studentCoursePairs3));
    
  }
}

/*
You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.

Write a function that takes in a collection of (student ID number, course name) pairs and returns, for every pair of students, a collection of all courses they share.


Sample Input:

student_course_pairs_1 = [
  ["58", "Linear Algebra"],
  ["94", "Art History"],
  ["94", "Operating Systems"],
  ["17", "Software Design"],
  ["58", "Mechanics"],
  ["58", "Economics"],
  ["17", "Linear Algebra"],
  ["17", "Political Science"],
  ["94", "Economics"],
  ["25", "Economics"],
  ["58", "Software Design"],
]

Sample Output (pseudocode, in any order):

find_pairs(student_course_pairs_1) =>
{
  "58,17": ["Software Design", "Linear Algebra"]
  "58,94": ["Economics"]
  "58,25": ["Economics"]
  "94,25": ["Economics"]
  "17,94": []
  "17,25": []
}



Additional test cases:

Sample Input:

student_course_pairs_2 = [
  ["0", "Advanced Mechanics"],
  ["0", "Art History"],
  ["1", "Course 1"],
  ["1", "Course 2"],
  ["2", "Computer Architecture"],
  ["3", "Course 1"],
  ["3", "Course 2"],
  ["4", "Algorithms"]
]



Sample output:

find_pairs(student_course_pairs_2) =>
{
  "1,0":[]
  "2,0":[]
  "2,1":[]
  "3,0":[]
  "3,1":["Course 1", "Course 2"]
  "3,2":[]
  "4,0":[]
  "4,1":[]
  "4,2":[]
  "4,3":[]
} 

Sample Input:
student_course_pairs_3 = [
  ["23", "Software Design"], 
  ["3", "Advanced Mechanics"], 
  ["2", "Art History"], 
  ["33", "Another"],
]


Sample output:

find_pairs(student_course_pairs_3) =>
{
  "23,3": []
  "23,2": []
  "23,33":[]
  "3,2":  []
  "3,33": []
  "2,33": []
}


*/

