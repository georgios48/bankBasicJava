package accounts;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    String customerName;
    LocalDate birthDate;
    Gender gender;
    String uniqueId;
    BigDecimal balance;


    public Account(String customerName, LocalDate birthDate, Gender gender, String uniqueId){
        this.customerName = customerName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.uniqueId = uniqueId;
        this.balance = BigDecimal.ZERO;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void subtract(BigDecimal amount){
        if (balance.compareTo(amount) > 0 || balance.compareTo(amount) == 0){
            balance = balance.subtract(amount);
            System.out.println("Removed " + amount + " balance left " + balance);
        }

        else {
            System.out.println("Not enough balance. " + balance + " - balance. " + amount + " - amount.");
        }
    }

    public void add(BigDecimal amount){
        balance = balance.add(amount);
        System.out.println(amount + " added to balance. " + "Total balance: " + balance);
    }
}

