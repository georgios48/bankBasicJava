import accounts.Gender;
import bank.Bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Bank bank = new Bank();

        Scanner scanner = new Scanner(System.in);

        menu(bank, scanner);

    }
    static void menu(Bank bank, Scanner scanner){
        String command;
        do {
            System.out.println("Available commands: 'addAccount', 'removeAccount', 'addRandom' - adds n random accounts, 'printAccounts - prints all accounts, 'accountOptions', 'e' or 'exit'");
            System.out.println();
            System.out.println("Enter command: ");
            command = scanner.nextLine();

            switch (command) {
                case "addAccount" -> {
                    System.out.println();
                    System.out.println("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.println();
                    System.out.println("Enter birth date of the customer: ");
                    String userInputDate = scanner.nextLine();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
                    LocalDate birthDate = LocalDate.parse(userInputDate, dateFormat);
                    System.out.println();
                    System.out.println("Choose gender: 'Male', 'Female', 'Other'");
                    String userInputGender = scanner.nextLine();
                    Gender toInputGender = Gender.OTHER;
                    while (!(userInputGender.equals("Male") || userInputGender.equals("Female") || userInputGender.equals("Other"))) {
                        System.out.println("Choose gender: 'Male', 'Female', 'Other'");
                        switch (userInputGender) {
                            case "Male" -> {
                                toInputGender = Gender.MALE;
                            }
                            case "Female" -> {
                                toInputGender = Gender.FEMALE;
                            }
                            case "Other" -> {
                                toInputGender = Gender.OTHER;
                            }
                            default -> {
                                System.out.println("Wrong option");
                                System.out.println("Choose gender.");
                                userInputGender = scanner.nextLine();
                            }
                        }
                    }
                    System.out.println("Enter UniqueId of the account: ");
                    String uniqueId = scanner.nextLine();
                    bank.addAccount(customerName, birthDate, toInputGender, uniqueId);
                }

                case "removeAccount" -> {
                    System.out.println();
                    System.out.println("Enter the id of the account you want to remove: ");
                    String uniqueId = scanner.nextLine();

                    bank.removeAccount(uniqueId);
                }

                case "addRandom" -> {
                    System.out.println("Enter the number of random accounts you want to be created: ");
                    Scanner scanner1 = new Scanner(System.in);
                    int numberOfAccounts = scanner1.nextInt();
                    bank.addRandomAccounts(numberOfAccounts);
                }

                case "printAccounts" -> {
                    bank.printAccounts();
                }

                case "accountOptions" -> {
                    System.out.println("Enter the uniqueID of the account to access: ");
                    String uniqueId = scanner.nextLine();
                    accountOptions(bank, uniqueId, scanner);
                }

                default -> {
                    System.out.println("Unknown command.");
                }
            }

        }while (!(command.equals("exit") || command.equals("e")));
    }
    static void accountOptions(Bank bank, String uniqueId, Scanner scanner){
        String userCommand;
        do {
            System.out.println("Account options: 'addBalance', 'subBalance', 'printAccount', 'type 'back', to return into main menu'");
            userCommand = scanner.nextLine();
            switch (userCommand) {
                case "addBalance" -> {
                    System.out.println();
                    System.out.println("Enter value to be added: ");
                    Scanner scanner2 = new Scanner(System.in);
                    BigDecimal valueToAdd = scanner2.nextBigDecimal();
                    bank.addBalance(uniqueId, valueToAdd);
                }

                case "subBalance" -> {
                    System.out.println();
                    System.out.println("Enter value to be withdrawn: ");
                    Scanner scanner2 = new Scanner(System.in);
                    BigDecimal valueToSub = scanner2.nextBigDecimal();
                    bank.subBalance(uniqueId, valueToSub);
                }

                case "printAccount" -> {
                    System.out.println();
                    bank.singleAccountPrint(uniqueId);
                }

                default -> {
                    System.out.println();
                    if (!(userCommand.equals("back"))) {
                        System.out.println("Wrong command.");
                    }
                }
            }
        }while (!(userCommand.equals("back")));
    }
}