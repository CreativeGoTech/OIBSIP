import java.util.Scanner;

class BankAccount {
    private String userId;
    private String pin;
    private float balance;
    private String transactionHistory;

    public BankAccount(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0f;
        this.transactionHistory = "";
    }

    public boolean login(String enteredUserId, String enteredPin) {
        return userId.equals(enteredUserId) && pin.equals(enteredPin);
    }

    public void deposit(float amount) {
        balance += amount;
        transactionHistory += "Deposited: Rs " + amount + "\n";
        displayBalance();
    }

    public void withdraw(float amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory += "Withdrawn: Rs " + amount + "\n";
            displayBalance();
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(float amount, BankAccount recipient) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory += "Transferred: Rs " + amount + " to " + recipient.userId + "\n";
            displayBalance();
        } else {
            System.out.println("Insufficient balance. Need to add amount into your account");
        }
    }

    public float getBalance() {
        return balance;
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:\n" + transactionHistory);
    }

    public void displayBalance() {
        System.out.println("Current Balance: Rs " + balance);
    }
}

public class Atminterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456", "1234");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM Interface");

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (account.login(userId, pin)) {
            System.out.println("Login successful.");

            boolean running = true;
            while (running) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Deposit");// used to deposit amount
                System.out.println("2. Withdraw");// used to withdraw amount
                System.out.println("3. Transfer");// used to transfer amount into another account
                System.out.println("4. Check Balance");// used to check remaining balance in account
                System.out.println("5. Transaction History");// show transaction history
                System.out.println("6. Quit");// used to quit the process

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        float depositAmount = scanner.nextFloat();
                        account.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        float withdrawAmount = scanner.nextFloat();
                        account.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter recipient's user ID: ");
                        String recipientId = scanner.next();
                        System.out.print("Enter amount to transfer: ");
                        float transferAmount = scanner.nextFloat();
                        BankAccount recipient = new BankAccount(recipientId, ""); 
                        account.transfer(transferAmount, recipient);
                        break;
                    case 4:
                        account.displayBalance();
                        break;
                    case 5:
                        account.displayTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM Interface.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Plz try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN. Exiting");
        }
        scanner.close();
    }
}
