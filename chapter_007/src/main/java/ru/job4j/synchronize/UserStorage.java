package ru.job4j.synchronize;

import net.jcip.annotations.ThreadSafe;

import java.util.TreeMap;

/**
 * Storage for Users.
 */
@ThreadSafe
public class UserStorage {
    /**
     * storage for Users.
     */
    private TreeMap<Integer, User> users = new TreeMap<>();

    /**
     * getter.
     * @return - users.
     */
    public TreeMap<Integer, User> getUsers() {
        return this.users;
    }

    /**
     * add User to storage.
     * @param user - user.
     */
    void add(User user) {
        users.put(user.getId(), user);
    }

    /**
     * update User in storage.
     * @param user - user.
     */
    synchronized void update(User user) {
        users.put(user.getId(), user);
    }

    /**
     * remove User in storage.
     * @param user - user.
     */
    void delete(User user) {
        users.remove(user.getId());
    }

    /**
     * transfer money from one account to another.
     * @param fromId - donor account.
     * @param toId - acceptor account.
     * @param amount - money to transfer.
     */
    void transfer(int fromId, int toId, double amount) {
        synchronized (this) {
            if (users.get(fromId).getAmount() > 0) {
                User donor = users.get(fromId);
                donor.setAmount(donor.getAmount() - amount);
                User accetor = users.get(toId);
                accetor.setAmount(accetor.getAmount() + amount);
            }
        }
    }
}
