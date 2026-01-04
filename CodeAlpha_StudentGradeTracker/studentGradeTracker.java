import java.util.Scanner;

class studentGradeTracker {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input number of students
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[] grades = new int[n];

        // Input grades
        for (int i = 0; i < n; i++) {
            System.out.print("Enter grade for student " + (i + 1) + ": ");
            grades[i] = sc.nextInt();
        }

        // Calculate sum, average, highest and lowest
        int sum = 0;
        int highest = grades[0];
        int lowest = grades[0];

        for (int i = 0; i < n; i++) {
            sum += grades[i];

            if (grades[i] > highest) {
                highest = grades[i];
            }

            if (grades[i] < lowest) {
                lowest = grades[i];
            }
        }

        double average = (double) sum / n;

        // Summary Report
        System.out.println("\n------ Summary Report ------");
        System.out.println("Total Students : " + n);
        System.out.println("Average Grade  : " + average);
        System.out.println("Highest Score  : " + highest);
        System.out.println("Lowest Score   : " + lowest);
    }
}






// import java.util.ArrayList;
// import java.util.Scanner;
// import java.util.Collections;

// class studentGradeTracker {
//     public static void main(String[] args) {

//         Scanner sc = new Scanner(System.in);
//         ArrayList<Integer> grades = new ArrayList<>();

//         // Input number of students
//         System.out.print("Enter number of students: ");
//         int n = sc.nextInt();

//         // Input grades
//         for (int i = 0; i < n; i++) {
//             System.out.print("Enter grade for student " + (i + 1) + ": ");
//             grades.add(sc.nextInt());
//         }

//         // Initialize calculations
//         int sum = 0;
//         int highest=0,lowest=0;
//         // int highest = grades.get(0);
//         // int lowest = grades.get(0);

//         // Calculate sum, highest, and lowest
//         for (int grade : grades) {
//             sum += grade;

//             highest=Collections.max(grades);
//             lowest=Collections.min(grades);

//             // if (grade > highest) {
//             //     highest = grade;
//             // }

//             // if (grade < lowest) {
//             //     lowest = grade;
//             // }
//         }

//         double average = (double) sum / n;

//         // Summary Report
//         System.out.println("\n------ Summary Report ------");
//         System.out.println("Total Students : " + n);
//         System.out.println("Average Grade  : " + average);
//         System.out.println("Highest Score  : " + highest);
//         System.out.println("Lowest Score   : " + lowest);

//     }
// }
