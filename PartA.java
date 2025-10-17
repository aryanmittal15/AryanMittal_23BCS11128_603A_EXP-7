import java.util.ArrayList;
import java.util.Scanner;

public class PartA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter 3 integers: ");
        
        for (int i = 0; i < 3; i++) {
            System.out.print("Please Enter num: ");
            String strNum = sc.nextLine();
            try {
                int num = Integer.parseInt(strNum); // String → int
                numbers.add(num); // Autoboxing: int → Integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
                i--; // retry current iteration
            }
        }

        int sum = 0;
        for (Integer n : numbers) { // Unboxing: Integer → int
            sum += n;
        }

        System.out.println("Sum of integers = " + sum);
        sc.close();
    }
}
