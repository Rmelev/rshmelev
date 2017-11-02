package ru.job4j.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
     * Set representation for HashMap.
     */
    private Set<Map.Entry<User, List<Account>>> set = bankClients.entrySet();

    /**
     * Constructor.
     * @param user - added user.
     */
    public void addUser(User user) {
        bankClients.put(user, new ArrayList<Account>());
    }

    /**
     *
     * @param user - deletted user.
     */
    public void deleteUser(User user) {
        // без temp при удалении выдает ConcurrentModificationException.
        Map.Entry<User, List<Account>> temp = null;
        for (Map.Entry<User, List<Account>> client : set) {
            if (client.getKey().equals(user)) {
                temp = client;
            }
        }
        if (temp != null) {
            bankClients.remove(temp.getKey());
        }
    }

    /**
     * @param user - user, to whom we add account.
     * @param account - added account.
     */
    public void addAccountToUser(User user, Account account) {
        for (Map.Entry<User, List<Account>> client : set) {
            if (client.getKey().equals(user)) {
                bankClients.get(client.getKey()).add(account);
            }
        }
    }

    /**
     * @param user - user, from whom we delete account.
     * @param account - deletted account.
     */
    public void deleteAccountFromUser(User user, Account account) {
        for (Map.Entry<User, List<Account>> client : set) {
            if (client.getKey().equals(user)) {
                bankClients.get(client.getKey()).remove(account);
            }
        }
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
        boolean flagSrcUser = false;
        boolean flagDstUser = false;
        boolean flagSrcAccount = false;
        boolean flagDstAccount = false;
        List<Account> foundUsersSrcAccounts = null;
        List<Account> foundUsersDstAccounts = null;
        for (Map.Entry<User, List<Account>> client : set) {
            if (client.getKey().equals(srcUser)) {
                foundUsersSrcAccounts = client.getValue();
                flagSrcUser = true;
            }
            if (client.getKey().equals(dstUser)) {
                foundUsersDstAccounts = client.getValue();
                flagDstUser = true;
            }
        }
        if (!flagSrcUser) {
            System.out.println("Client for withdrawal wasn't found.");
            return false;
        }
        if (!flagDstUser) {
            System.out.println("Client for deposit wasn't found.");
            return false;
        }
        for (Account tempAccount : foundUsersSrcAccounts) {
            if (tempAccount.equals(srcAccount)) {
                flagSrcAccount = true;
                if (tempAccount.getValue() > amount) {
                    tempAccount.setValue(tempAccount.getValue() - amount);
                } else {
                    System.out.println("Haven't enough money for finish operation.");
                }
            }
        }
        if (!flagSrcAccount) {
            System.out.println("Source account wasn't found.Try another account!");
            return false;
        }
        for (Account tempAccount : foundUsersDstAccounts) {
            if (tempAccount.equals(dstAccount)) {
                flagDstAccount = true;
                tempAccount.setValue(tempAccount.getValue() + amount);
            }
        }
        if (!flagDstAccount) {
            System.out.println("Distance account wasn't found.Try another account!");
            return false;
        }
        return true;
    }
}