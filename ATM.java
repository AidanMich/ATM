import java.util.HashMap;

public class ATM {
    static HashMap<String, Double> account = new HashMap<String, Double>();

    public void openAccount(String userid, double deposit) throws Exception {
        if (account.containsKey(userid)) {
            throw new Exception("This account already exists bozo");
        }
        account.put(userid, deposit);
    }

    public void closeAccount(String userid) throws Exception {
        if (account.get(userid) == 0) {
            account.remove(userid);
        } else {
            throw new Exception("Withdraw your money first");
        }
    }

    public double checkBalance(String userid) {
        return account.get(userid);
    }

}