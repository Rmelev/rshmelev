package ru.job4j.bank;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for test sort method.
 */
public class BankTest {
    /**
     * test1.
     */
    @Test
    public void whenaddUserThenAddedUser() {
        Bank bank = new Bank();
        User roma = new User("roma", 450355555);
        User gena = new User("gena", 730311111);
        User leha = new User("leha", 690822222);
        User beha = new User("beha", 234545454);
        Account romaAcc1 = new Account(1234567.12, 123456789);
        Account romaAcc2 = new Account(55555.55, 555555555);
        Account lehaAcc1 = new Account(22222.34, 222222222);
        Account romaAcc3 = new Account(33333.33, 333333333);
        Account behaAcc1 = new Account(11111.11, 111111111);
        Account genaAcc1 = new Account(11111.11, 111111111);
        bank.addUser(roma);
        bank.addUser(gena);
        bank.addUser(leha);
        bank.addUser(beha);
        bank.addAccountToUser(roma, romaAcc1);
        bank.addAccountToUser(leha, lehaAcc1);
        bank.addAccountToUser(roma, romaAcc2);
        bank.addAccountToUser(roma, romaAcc3);
        bank.addAccountToUser(beha, behaAcc1);
        bank.addAccountToUser(gena, genaAcc1);
        bank.deleteUser(gena);
        bank.deleteAccountFromUser(roma, romaAcc2);
        bank.transferMoney(roma, romaAcc1, leha, lehaAcc1, 1000);
        Set<Map.Entry<User, List<Account>>> set = bank.getBankClients().entrySet();
        for (Map.Entry<User, List<Account>> client : set) {
            System.out.println(client.getKey() + " счета:" + client.getValue());
        }
        assertThat(bank.transferMoney(leha, lehaAcc1, roma, romaAcc1, 10000), is(true));
        assertThat(bank.getBankClients().size(), is(3));
    }
}