package Task1;

import java.util.ArrayList;
import java.util.Scanner;

public class studentgrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter student name: ");
            String name = sc.nextLine();

            Student student = new Student(name);

            System.out.print("Enter number of subjects: ");
            int subjects = sc.nextInt();

            for (int j = 0; j < subjects; j++) {
                System.out.print("Enter grade " + (j + 1) + ": ");
                int grade = sc.nextInt();
                student.addGrade(grade);
            }
            sc.nextLine(); 

            students.add(student);
        }

        System.out.println("\n===== STUDENT SUMMARY REPORT =====");
        for (Student s : students) {
            s.displayReport();
        }

        sc.close();
    }
}