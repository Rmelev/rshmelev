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
        boolean flag = false;

        for (Account tempAccount : bankClients.get(srcUser)) {
            if (tempAccount.equals(srcAccount)) {
                if (tempAccount.getValue() >= amount) {
                    tempAccount.setValue(tempAccount.getValue() - amount);
                    flag = true;
                    break;
                } else {
                    System.out.println("Haven't enough money for finish operation.");
                    flag = false;
                }
            }
        }

        if (flag) {
            for (Account tempAccount : bankClients.get(dstUser)) {
                if (tempAccount.equals(dstAccount)) {
                    tempAccount.setValue(tempAccount.getValue() + amount);
                    break;
                }
            }
        }
        return flag;
    }
}