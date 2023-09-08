import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;

public class ATM {
    static ArrayList<String> keys = new ArrayList<>();
    static HashMap<String, Double> account = new HashMap<String, Double>();

    public static void openAccount(String userid, double deposit) throws Exception {
        if (account.containsKey(userid)) {
            throw new Exception("This account already exists bozo");
        }
        account.put(userid, deposit);
        keys.add(userid);
    }

    public static void closeAccount(String userid) throws Exception {
        if (account.get(userid) == 0) {
            account.remove(userid);
        } else {
            throw new Exception("Withdraw your money first");
        }
    }

    public static double checkBalance(String userid) {
        return account.get(userid);
    }

    public static double depositMoney(String userid, double input) throws Exception {
        if (!account.containsKey(userid)) {
            throw new Exception("You dont have an account, broke AF");
        }
        account.put(userid, account.get(userid) + input);
        return input;
    }

    public static double withdrawMoney(String userid, double amount) throws Exception {
        if (account.get(userid) < amount) {
            throw new Exception("Your not him");
        }
        account.put(userid, account.get(userid) - amount);
        return amount;
    }

    public static boolean transferMoney(String from, String to, double amount) throws Exception {
        if (account.get(from) < amount) {
            throw new Exception("blueski is not him");
        }
        withdrawMoney(from, amount);
        depositMoney(to, amount);
        return true;
    }

    public static void audit() throws FileNotFoundException {
        String file = "AccountAudit.txt";
        PrintWriter pw = new PrintWriter(file);
        for (int i = 0; i < keys.size(); i++) {
            pw.println(keys.get(i) + " " + account.get(keys.get(i)));
            // keys.remove(i);
        }
        pw.close();
    }

    public static void main(String[] args) throws Exception {
        openAccount("Aidan", 5000);
        openAccount("Chris", 5000);
        openAccount("Mark", 1000);
        System.out.println(keys.size());
        transferMoney("Chris", "Aidan", 3000);
        withdrawMoney("Chris", 1000);
        audit();
    }
}