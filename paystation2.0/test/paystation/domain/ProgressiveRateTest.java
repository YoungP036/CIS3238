/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import Impl.paystation.fury.PayStationImpl;
import interfaces.paystation.fury.PayStation;
import Impl.paystation.fury.IllegalCoinException;
import ratestrategies.paystation.fury.ProgressiveRateStrategy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fury
 */
public class ProgressiveRateTest {
    
    PayStation ps;
   
    @Before
    public void setup() {
        ps = new PayStationImpl(new ProgressiveRateStrategy());
    }
    
    
    /*CONVENIENCE METHODS*/
    private void addDollar() throws IllegalCoinException{
	for(int i=0;i<4;i++)
	    ps.addPayment(25);
    }
    /*CONVENIENCE METHODS*/
    private void addHalfDollar() throws IllegalCoinException{
	    ps.addPayment(25);
	    ps.addPayment(25);
    }
	@Test
	public void displayIntegration() throws IllegalCoinException{
		addDollar(); 
		addDollar();
		assertEquals(75, ps.readDisplay());
	}
    @Test
    public void progressiveFourHour() throws IllegalCoinException{
	for(int i=0;i<9;i++)
	    addDollar();
	addHalfDollar();
	assertEquals(240,ps.readDisplay());
    }
    @Test
    public void progressiveThreeHour() throws IllegalCoinException{
	for(int i=0;i<6;i++)
	    addDollar();
	addHalfDollar();
	assertEquals(180,ps.readDisplay());
    }
    @Test
    public void progressiveTwoHour() throws IllegalCoinException{
	for(int i=0;i<3;i++)
	    addDollar();
	addHalfDollar();
	//350 in, 120 out
	assertEquals(120,ps.readDisplay());
    }

    @Test
    public void progressiveOneHour() throws IllegalCoinException{
	addDollar();
	addHalfDollar();
	assertEquals(60,ps.readDisplay());
    } 
}
