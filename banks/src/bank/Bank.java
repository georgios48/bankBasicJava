package bank;

import accounts.Account;
import accounts.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Bank {

    List<Account> accounts;

    public Bank(){
        this.accounts = new ArrayList<>();
    }

    public void printAccounts(){
        accounts.forEach(account -> System.out.println(account.getCustomerName() + " | " + account.getUniqueId()));
    }

    public void singleAccountPrint(String uniqueID){
        if (accounts.stream().map(account -> account.getUniqueId()).toList().contains(uniqueID)){
            System.out.println(accounts.stream().filter(account -> account.getUniqueId().equals(uniqueID)).toList().get(0).getCustomerName());
            System.out.println(accounts.stream().filter(account -> account.getUniqueId().equals(uniqueID)).toList().get(0).getBirthDate());
            System.out.println(accounts.stream().filter(account -> account.getUniqueId().equals(uniqueID)).toList().get(0).getGender());
            System.out.println("Balance - " + accounts.stream().filter(account -> account.getUniqueId().equals(uniqueID)).toList().get(0).getBalance());
            System.out.println(uniqueID);
        }
    }

    public void addAccount(String customerName, LocalDate birthDate, Gender gender, String uniqueId){

        if (accounts.stream().map(account -> account.getUniqueId()).toList().contains(uniqueId)){
            System.out.println("Already existing account with the unique ID: " + uniqueId);
        }

        else {
            Account account = new Account(customerName, birthDate, gender, uniqueId);
            accounts.add(account);
        }
    }

    public void removeAccount(String uniqueId){
        if(accounts.stream().map(account -> account.getUniqueId()).toList().contains(uniqueId)){
            accounts.remove(accounts.stream().filter(account -> account.getUniqueId().equals(uniqueId)).toList().get(0));
        }
        else{
            System.out.println("Unique ID not found: " + uniqueId);
        }
    }

    public void addRandomAccounts(int numberAccounts){
        for (int i = 0; i < numberAccounts; i++){
            String customerName = "Stoyan Kolev";
            Random random = new Random();
            LocalDate birthDate = LocalDate.now().minusDays(random.nextInt(500));
            Gender gender = Gender.values()[i % 2];
            String uniqueId = UUID.randomUUID().toString();

            addAccount(customerName, birthDate, gender, uniqueId);
        }
    }

    public void addBalance(String uniqueId, BigDecimal valueToAdd){
        if(accounts.stream().map(account -> account.getUniqueId()).toList().contains(uniqueId)){
            accounts.stream().filter(account -> account.getUniqueId().equals(uniqueId)).toList().get(0).add(valueToAdd);
        }
        else {
            System.out.println("No Unique Id found: " + uniqueId);
        }
    }

    public void subBalance(String uniqueId, BigDecimal valueToSub){
        if(accounts.stream().map(account -> account.getUniqueId()).toList().contains(uniqueId)){
            accounts.stream().filter(account -> account.getUniqueId().equals(uniqueId)).toList().get(0).subtract(valueToSub);
        }
        else {
            System.out.println("No Unique Id found: " + uniqueId);
        }
    }
}
