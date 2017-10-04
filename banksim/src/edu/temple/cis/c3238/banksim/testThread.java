package edu.temple.cis.c3238.banksim;

import java.util.concurrent.TimeUnit;

/**
 * 1) Create a new thread in which testing summing the amounts in each account 
 * is running on its own thread
 * 2) Code protection such that the new testing thread and any transfer threads
 *	are mutually exclusive
 *		i. Testing thread starts by sending signal to all transfer threads
 *		ii. Waits for all transferring threads to finish current transfer
 *		iii. resumes testing task and transfer threads wait until test is done
 *		NOTE: Unrelated transfers must be able to proceed concurrently
 */
class testThread extends Thread {

    private final Bank bank;
	private final int numAccounts;
	private final Account[] accounts;
	private final int INITIAL_BALANCE;
    public testThread(Bank b,Account[] accs, int startBalance) {
        bank = b;
		numAccounts=bank.size();
		accounts=accs;
		INITIAL_BALANCE=startBalance;
	}

    @Override
    public void run() {
		this.sumTest();	
    }


	private void sumTest(){
		
		//returns false and sets to true
		while(bank.testLock.getAndSet(true));
		try{TimeUnit.MICROSECONDS.sleep(75000);}
		catch(InterruptedException ex){}
		try{
		/*******BEGIN CRITICAL SECTION*********/
			int sum = 0;
			for (Account account : accounts) {
				System.out.printf("%s %s%n", 
					Thread.currentThread().toString(), account.toString());
				sum += account.getBalance();
			}//end for			
		/*******END CRITICAL SECTION*********/	
			System.out.println(Thread.currentThread().toString()+" Sum: " + sum);
			if (sum != numAccounts * INITIAL_BALANCE) {
				System.out.println(Thread.currentThread().toString() + 
					" Money was gained or lost");
				System.exit(1);
			} else {
				System.out.println(Thread.currentThread().toString() + 
					" The bank is in balance");
			}
		}//end try
		finally{bank.testLock.set(false);}
	}//end test
}//end class