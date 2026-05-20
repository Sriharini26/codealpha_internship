package Task4;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String URL =
            "jdbc:mysql://localhost:3306/Task4";

    static final String USER = "root";

    static final String PASS = "Bairo@123";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con =DriverManager.getConnection(
                            URL, USER, PASS);

            int choice;

            do {

                System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");

                System.out.println("1. View Rooms");
                System.out.println("2. Book Room");
                System.out.println("3. Cancel Booking");
                System.out.println("4. View Booking Details");
                System.out.println("5. Exit");

                System.out.print("Enter Choice: ");

                choice = sc.nextInt();

                switch(choice) {

                case 1:

                    Statement stmt1 =
                            con.createStatement();

                    ResultSet rs1 =
                            stmt1.executeQuery("SELECT * FROM rooms");

                    while(rs1.next()) {

                        System.out.println(
                                rs1.getInt("room_no")
                                + " | "
                                + rs1.getString("category")
                                + " | ₹"
                                + rs1.getDouble("price")
                                + " | Available: "
                                + rs1.getBoolean("available")
                        );
                    }

                    break;

                case 2:

                    System.out.print(
                            "Enter Room Number: ");

                    int bookRoom = sc.nextInt();

                    PreparedStatement ps1 =
                            con.prepareStatement(

                                    "UPDATE rooms SET available=false WHERE room_no=?"
                            );

                    ps1.setInt(1, bookRoom);

                    int booked = ps1.executeUpdate();

                    if(booked > 0) {

                        System.out.println(
                                "Payment Successful!");

                        System.out.println(
                                "Room Booked!");
                    }

                    else {

                        System.out.println(
                                "Room Not Found!");
                    }

                    break;

                case 3:

                    System.out.print(
                            "Enter Room Number: ");

                    int cancelRoom = sc.nextInt();

                    PreparedStatement ps2 =
                            con.prepareStatement(

                                    "UPDATE rooms SET available=true WHERE room_no=?"
                            );

                    ps2.setInt(1, cancelRoom);

                    int cancelled =
                            ps2.executeUpdate();

                    if(cancelled > 0) {

                        System.out.println(
                                "Booking Cancelled!");
                    }

                    else {

                        System.out.println(
                                "Room Not Found!");
                    }

                    break;

                case 4:

                    Statement stmt2 =
                            con.createStatement();

                    ResultSet rs2 =
                            stmt2.executeQuery(

                                    "SELECT * FROM rooms WHERE available=false"
                            );

                    System.out.println(
                            "\n===== BOOKED ROOMS =====");

                    while(rs2.next()) {

                        System.out.println(
                                rs2.getInt("room_no")
                                + " | "
                                + rs2.getString("category")
                                + " | ₹"
                                + rs2.getDouble("price")
                        );
                    }

                    break;

                case 5:

                    System.out.println(
                            "Thank You!");

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

            } while(choice != 5);

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }

        sc.close();
    }
}