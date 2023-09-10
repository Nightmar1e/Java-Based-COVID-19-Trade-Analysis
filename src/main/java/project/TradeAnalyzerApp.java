package project;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import static project.TradeAnalyzerCalculator.*;

public class TradeAnalyzerApp {
    private static final String CSV_FILE_PATH = "/Users/Walid/Desktop/Becode/The Mountain/Java/CleanProject/src/main/resources/covid_and_trade.csv";

    private enum CalculationType {
        MONTHLY_TOTAL,
        MONTHLY_AVERAGE,
        YEARLY_TOTAL,
        YEARLY_AVERAGE,
        OVERVIEW,
        HELP,
        MORE_INFO,
        EXIT
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<TradeData> tradeDataList = TradeDataReader.readTradeDataFromCSV(CSV_FILE_PATH);

        CalculationType calculationType;
        do {
            displayMenu();
            calculationType = CalculationType.values()[getIntInput(scanner, "Enter the calculation type: ") - 1];

            switch (calculationType) {
                case HELP:
                    displayHelp();
                    break;
                case MORE_INFO:
                    displayHelpForSpecificCommand(scanner);
                    break;
                case EXIT:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    processCalculationType(calculationType, scanner, tradeDataList);
                    if (!shouldContinue(scanner)) {
                        calculationType = CalculationType.EXIT;
                    }
            }
        } while (calculationType != CalculationType.EXIT);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Available Commands:");
        for (int i = 0; i < CalculationType.values().length; i++) {
            System.out.println((i + 1) + ". " + CalculationType.values()[i]);
        }
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    private static boolean shouldContinue(Scanner scanner) {
        System.out.print("Would you like to search for something else? (y/n): ");
        String continueChoice = scanner.next();
        return continueChoice.equalsIgnoreCase("y");
    }

    private static void processCalculationType(CalculationType calculationType, Scanner scanner, List<TradeData> tradeDataList) {
        switch (calculationType) {
            case MONTHLY_TOTAL:
                int desiredYear = getDesiredYear(scanner);
                String desiredMonth = getDesiredMonth(scanner);
                calculateMonthlyTotal(tradeDataList, desiredYear, desiredMonth);
                break;
            case MONTHLY_AVERAGE:
                desiredYear = getDesiredYear(scanner);
                desiredMonth = getDesiredMonth(scanner);
                calculateMonthlyAverage(tradeDataList, desiredYear, desiredMonth);
                break;
            case YEARLY_TOTAL:
                desiredYear = getDesiredYear(scanner);
                calculateYearlyTotal(tradeDataList, desiredYear);
                break;
            case YEARLY_AVERAGE:
                desiredYear = getDesiredYear(scanner);
                calculateYearlyAverage(tradeDataList, desiredYear);
                break;
            case OVERVIEW:
                TradeDataOverview.displayOverview(tradeDataList);
                break;
            default:
                System.out.println("No explanation available for the selected command.");
        }
    }

    private static int getDesiredYear(Scanner scanner) {
        return getIntInput(scanner, "Enter the desired year: ");
    }

    private static String getDesiredMonth(Scanner scanner) {
        while (true) {
            System.out.print("Enter the desired month (e.g., 01 for January): ");
            String desiredMonth = scanner.next();
            if (desiredMonth.matches("\\d{2}")) {
                return desiredMonth;
            } else {
                System.out.println("Invalid input. Please enter a valid 2-digit month.");
            }
        }
    }

    private static void displayHelp() {
        System.out.println("Available Commands:");
        for (CalculationType type : CalculationType.values()) {
            System.out.println(type.ordinal() + 1 + ". " + type);
        }
    }

    private static void displayHelpForSpecificCommand(Scanner scanner) {
        System.out.print("Enter the number for which you want to know more about: ");
        int commandNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        displayHelpForCommand(commandNumber);
    }

    private static void displayHelpForCommand(int command) {
        CalculationType[] types = CalculationType.values();
        if (command >= 1 && command <= types.length) {
            System.out.println("More info about the " + types[command - 1] + " Command 🤗");
            // Add more info messages for each command as needed.
        } else {
            System.out.println("No explanation available for the selected command. 🤷‍");
        }
    }
}
