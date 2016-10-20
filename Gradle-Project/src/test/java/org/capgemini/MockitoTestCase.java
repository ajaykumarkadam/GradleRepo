package org.capgemini;

import static org.junit.Assert.*;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Address;
import org.cap.dto.Customer;
import org.cap.exception.InsufficientBalanceException;
import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MockitoTestCase {
	@Mock
	private AccountDao accountDao;
	private AcccountService accService;
	
	@Before
	public void beforeMethod(){
		
		MockitoAnnotations.initMocks(this);
		accService=new AccountServiceImpl(accountDao);
	}

	@Test
	public void findAccountTestCase(){
		Account account=new Account();
		account.setAccountNo(1001);
		account.setAmount(5000);
		Customer cust=new Customer();
		cust.setCustName("John");
		cust.setCustAddress(new Address());
		account.setCustomer(cust);
		//Declaration
		Mockito.when(accService.findAccountById(1001)).thenReturn(account);
		//Actual business logic
		Account account1=accService.findAccountById(1001);
		//verification
		Mockito.verify(accountDao).findAccountById(1001);
		assertEquals(5000, account.getAmount(),0.0);
	}
	
	@Test(expected=InsufficientBalanceException.class)
	public void withdrawalTestCase() throws InsufficientBalanceException{
		Account account=new Account();
		account.setAccountNo(1001);
		account.setAmount(5000);
		Customer cust=new Customer();
		cust.setCustName("John");
		cust.setCustAddress(new Address());
		account.setCustomer(cust);
		//Declaration
		Mockito.when(accService.findAccountById(1001)).thenReturn(account);
		//Actual business logic                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
		Account account1=accService.withdraw(1001, 6000);
		//verification
		Mockito.verify(accountDao).findAccountById(1001);
		assertEquals(4000, account1.getAmount(),0.0);
	}
	
	@Test
	public void Deposit_testCase(){
		Account account=new Account();
		account.setAccountNo(1001);
		account.setAmount(5000);
		Customer cust=new Customer();
		cust.setCustName("John");
		cust.setCustAddress(new Address());
		account.setCustomer(cust);
		//Declaration
		Mockito.when(accService.findAccountById(1001)).thenReturn(account);
		//Actual business logic                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
		Account account1=accService.deposit(1001, 500);
		//verification
		Mockito.verify(accountDao).findAccountById(1001);
		assertEquals(5500, account1.getAmount(),0.0);
	}
	
}
