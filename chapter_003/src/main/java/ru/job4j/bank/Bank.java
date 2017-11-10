package ru.job4j.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * all users & their accounts with all accessible operations.
 */
public class Bank {
    /**
     * all users & their accounts.
     */
    private Map<User, List<Account>> bankClients = new HashMap<>();

    /**
     * getter.
     * @return - bankClients.
     */
    public Map<User, List<Account>> getBankClients() {
        return bankClients;
    }

    /**
     * Constructor.
     * @param user - added user.
     */
    public void addUser(User user) {
        bankClients.put(user, new ArrayList<Account>());
    }

    /**
     * @param user - deletted user.
     */
    public void deleteUser(User user) {
        bankClients.remove(user);
    }

    /**
     * @param user - user, to whom we add account.
     * @param account - added account.
     */
    public void addAccountToUser(User user, Account account) {
        bankClients.get(user).add(account);
    }

    /**
     * @param user - user, from whom we delete account.
     * @param account - deletted account.
     */
    public void deleteAccountFromUser(User user, Account account) {
        bankClients.get(user).remove(account);
    }

    /**
     * transfer money(amount) from srcUser & srcAccount to dstUser & dstAccount.
     * @param srcUser - from that user we get money.
     * @param srcAccount - from that user's account we get money.
     * @param dstUser - user, to whom we put money.
     * @param dstAccount - user's account, where we put money.
     * @param amount - amount of money to transfer.
     * @return - true, if success.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        Account source = findRequiredAcc(srcUser, srcAccount);
        if (source == null) {
            System.out.println("Source account doesn't found. Please, try again");
        }

        Account dest = findRequiredAcc(dstUser, dstAccount);
        if (dest == null) {
            System.out.println("Destination account doesn't found. Please, try again");
        }

        boolean allow = source.getValue() > amount;

        if (allow) {
            source.setValue(source.getValue() - amount);
            dest.setValue(dest.getValue() + amount);
        } else {
            System.out.println("Haven't enough money for finish operation.");
        }

        return allow;
    }

    /**
     * find required account.
     * @param user - User.
     * @param account - required User's account.
     * @return - required account or null.
     */
    public Account findRequiredAcc(User user, Account account) {
        Account reqAccount = null;
        for (Account tempAccount : bankClients.get(user)) {
            if (tempAccount.equals(account)) {
                reqAccount = tempAccount;
                break;
            }
        }
        return reqAccount;
    }
}