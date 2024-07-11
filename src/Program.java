
import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter department's name: ");
        String departmentName = sc.nextLine();

        System.out.println("Enter worker data: ");
        System.out.print("Name: ");
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String workerLevel = sc.nextLine();
        System.out.print("Base Salary: ");
        double baseSalary = sc.nextDouble();

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel),
                        baseSalary, new Department(departmentName));

        System.out.print("How many contracts to this worker?");
        int numberOfContracts = sc.nextInt();
        for (int i = 1; i <= numberOfContracts; i++){
            System.out.printf("Enter contract #%d data: %n", i);
            System.out.print("Date (DD/MM/YYYY): ");
            String date = sc.next();
            LocalDate contractDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duration: ");
            int hours = sc.nextInt();

            worker.addContract(new HourContract(contractDate, valuePerHour, hours));
        }

        System.out.print("Enter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = sc.next();
        int monthFiltered = Integer.parseInt(monthAndYear.substring(0,2));
        int yearFiltered = Integer.parseInt(monthAndYear.substring(3));

        System.out.printf("Name: %s %n", worker.getName());
        System.out.printf("Department: %s %n", worker.getDepartment().getName());
        System.out.printf("Income for %d/%d: %.2f", monthFiltered, yearFiltered, worker.income(yearFiltered,monthFiltered));

        sc.close();
    }
}
