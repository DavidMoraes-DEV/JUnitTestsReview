package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Account;
import tests.factory.AccountFactory;

public class AccountTests {

	@Test
	public void depositShouldIncreaseBalanceAndDiscountFeeWhenPositiveAmount() {
		
		double amount = 200;
		double expectedValue = 196;
		
		Account acc = AccountFactory.createEmptyAccount();
		
		acc.deposit(amount);
		
		Assertions.assertEquals(expectedValue, acc.getBalance());
	}
	
	@Test
	public void depositShouldDoNothingWhenNegativeAmount() {
		
		double expectedValue = 100.0;
		Account acc = AccountFactory.createAccount(expectedValue);
		
		double amount = -200;
		
		acc.deposit(amount);
		
		Assertions.assertEquals(expectedValue, acc.getBalance());
		
	}
	
	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance () {
		
		double initialBalance = 200.0;
		double amountWithdraw = 50.0;
		double expectedValue = 150.0;
		
		Account acc = AccountFactory.createAccount(initialBalance);
		
		acc.withdraw(amountWithdraw);
		
		Assertions.assertEquals(expectedValue, acc.getBalance());
	}
	
	@Test
	public void withdrawShouldThrowExceptionWhenInsuffivientBalance() {
		
		double initialBalance = 200.0;
		double amountWithdraw = 250.0;
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			
			Account acc = AccountFactory.createAccount(initialBalance);
			
			acc.withdraw(amountWithdraw);
		});
	}
	
	@Test
	public void fullWithdrawShouldClearBalanceAndReturnFullBalance () {
		
		double expectedValue = 0.0;
		double amount = 200.0;
		
		Account acc = AccountFactory.createAccount(amount);
		
		double result = acc.fullWithdraw();
		
		Assertions.assertTrue(expectedValue == acc.getBalance());
		Assertions.assertTrue(result == amount);
		
	}
}
