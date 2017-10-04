package edu.temple.cis.c3238.banksim;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 */
/**
 * 1) Create a new thread in which testing summing the amounts in each account 
 * is running on its own thread
 * 2) Code protection such that the new testing thread and any transfer threads
 *	are mutually exclusive
 *		i. Testing thread starts by sending signal to all transfer threads
 *		ii. Waits for all transferring threads to finish current transfer
 *		iii. resumes testing task and transfer threads wait until test is done
 *		NOTE: Unrelated transfers must be able to proceed concurrently
 * @author fury
 */
public class Bank {

    public static final int NTEST = 10;
    private final Account[] accounts;
    private long ntransacts = 0;
    private final int initialBalance;
    private final int numAccounts;
    private boolean open;
	public AtomicBoolean testLock = new AtomicBoolean();
	
    public Bank(int numAccounts, int initialBalance) {
        open = true;
        this.initialBalance = initialBalance;
        this.numAccounts = numAccounts;
        accounts = new Account[numAccounts];
        for (int i = 0; i < numAccounts; i++) {
            accounts[i] = new Account(this, i, initialBalance);
        }
		testLock.set(false);
    }

    public void transfer(int from, int to, int amount) {
		//if its false, keep it false, and return true which ends loop
		while(!testLock.compareAndSet(false,false));
		accounts[from].waitForAvailableFunds(amount);
        if (!open) return;
		if (accounts[from].withdraw(amount)) {
			accounts[to].deposit(amount);
		}		
		if (shouldTest()) test();
    }

    public void test() {
		Thread testThread = new testThread(this, accounts, initialBalance);
		testThread.start();
    }

    public int size() {
        return accounts.length;
    }
    
    public synchronized boolean isOpen() {return open;}
    
    public void closeBank() {
        synchronized (this) {
            open = false;
        }
		//notify each account on its lock
        for (Account account : accounts) {
            synchronized(account) {
                account.notifyAll();
            }
        }
    }
    
    public synchronized boolean shouldTest() {
        return ++ntransacts % NTEST == 0;
    }

}
