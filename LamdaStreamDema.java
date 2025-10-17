import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: " + salary;
    }
}

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " | Marks: " + marks;
    }
}

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " | " + category + " | Price: " + price;
    }
}

public class LambdaStreamDemo {
    public static void main(String[] args) {

        // =====================================================
        // 🟩 PART A: Sorting Employee Objects Using Lambda
        // =====================================================
        System.out.println("========== PART A: Sorting Employees ==========\n");

        List<Employee> employees = Arrays.asList(
            new Employee("Ravi", 25, 55000),
            new Employee("Anita", 30, 72000),
            new Employee("Karan", 22, 45000),
            new Employee("Meena", 28, 60000)
        );

        System.out.println("Original Employee List:");
        employees.forEach(System.out::println);

        // Sort by Name (Alphabetically)
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        // Sort by Age (Ascending)
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        // Sort by Salary (Descending)
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);


        // =====================================================
        // 🟩 PART B: Filtering and Sorting Students Using Streams
        // =====================================================
        System.out.println("\n\n========== PART B: Filtering and Sorting Students ==========\n");

        List<Student> students = Arrays.asList(
            new Student("Aman", 82.5),
            new Student("Pooja", 68.0),
            new Student("Ravi", 91.2),
            new Student("Simran", 77.8),
            new Student("Karan", 72.4)
        );

        System.out.println("Students scoring above 75%, sorted by marks:\n");

        students.stream()
                .filter(s -> s.marks > 75)
                .sorted((s1, s2) -> Double.compare(s1.marks, s2.marks))
                .map(s -> s.name)
                .forEach(System.out::println);


        // =====================================================
        // 🟩 PART C: Stream Operations on Product Dataset
        // =====================================================
        System.out.println("\n\n========== PART C: Stream Operations on Products ==========\n");

        List<Product> products = Arrays.asList(
            new Product("Laptop", 75000, "Electronics"),
            new Product("Mobile", 45000, "Electronics"),
            new Product("Table", 8000, "Furniture"),
            new Product("Chair", 3500, "Furniture"),
            new Product("Jeans", 2000, "Clothing"),
            new Product("Shirt", 1500, "Clothing"),
            new Product("TV", 60000, "Electronics")
        );

        // Grouping by Category
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        System.out.println("Products Grouped by Category:");
        grouped.forEach((cat, prodList) -> {
            System.out.println(cat + " -> " + prodList);
        });

        // Most Expensive Product in Each Category
        Map<String, Optional<Product>> maxPrice = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));

        System.out.println("\nMost Expensive Product in Each Category:");
        maxPrice.forEach((cat, prod) -> 
            System.out.println(cat + " -> " + prod.get())
        );

        // Average Price of All Products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}
