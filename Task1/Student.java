package Task1;

import java.util.ArrayList;

class Student {
    String name;
    ArrayList<Integer> grades;

    // Constructor
    Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    // Add grade
    void addGrade(int grade) {
        grades.add(grade);
    }

    // Calculate average
    double getAverage() {
        int sum = 0;
        for (int g : grades) {
            sum += g;
        }
        return grades.size() > 0 ? (double) sum / grades.size() : 0;
    }

    // Highest score
    int getHighest() {
        int max = Integer.MIN_VALUE;
        for (int g : grades) {
            if (g > max) max = g;
        }
        return max;
    }

    // Lowest score
    int getLowest() {
        int min = Integer.MAX_VALUE;
        for (int g : grades) {
            if (g < min) min = g;
        }
        return min;
    }

    // Display student report
    void displayReport() {
        System.out.println("\nStudent Name: " + name);
        System.out.println("Grades: " + grades);
        System.out.println("Average: " + getAverage());
        System.out.println("Highest: " + getHighest());
        System.out.println("Lowest: " + getLowest());
    }
}

