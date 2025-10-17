import java.io.*;
import java.util.Scanner;

class Employee {
    String name;
    String id;
    String designation;
    double salary;

    Employee(String name, String id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + designation + "," + salary;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Designation: " + designation);
        System.out.println("Salary: " + salary);
        System.out.println("-----------------------");
    }
}

public class PartC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "employees.txt";
        int choice;

        do {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine(); 

                    Employee emp = new Employee(name, id, designation, salary);

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                        bw.write(emp.toString());
                        bw.newLine();
                        System.out.println("Employee added successfully.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("\n--- All Employees ---");
                    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] data = line.split(",");
                            Employee e = new Employee(data[1], data[0], data[2], Double.parseDouble(data[3]));
                            e.display();
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("No employee records found.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}
