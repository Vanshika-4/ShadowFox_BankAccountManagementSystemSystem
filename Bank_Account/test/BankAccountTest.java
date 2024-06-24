package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

import src.BankAccount;

public class BankAccountTest {
    private BankAccount account;

    @Before
    public void setUp() {
        account = new BankAccount();
    }

    @Test
    public void testDeposit() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdraw() {
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        account.deposit(50.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(100.0);
        });
        assertEquals("Insufficient funds.", exception.getMessage());
    }

    @Test
    public void testNegativeDeposit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50.0);
        });
        assertEquals("Deposit amount must be positive.", exception.getMessage());
    }

    @Test
    public void testNegativeWithdraw() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        });
        assertEquals("Withdrawal amount must be positive.", exception.getMessage());
    }

    @Test
    public void testTransactionHistory() {
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals(2, account.getTransactionHistory().size());
        assertEquals("Deposited: 100.0", account.getTransactionHistory().get(0));
        assertEquals("Withdrew: 50.0", account.getTransactionHistory().get(1));
    }
}
