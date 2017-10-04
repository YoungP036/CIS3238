/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import interfaces.paystation.fury.WeekendDecider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ratestrategies.paystation.fury.RealWeekendModule;

/**
 *
 * @author fury
 */
public class weekendDeciderTest {
	
	WeekendDecider fixed_true;
	WeekendDecider fixed_false;
	WeekendDecider realDecider;
	@Before
	public void setUp() {
		fixed_false = new FixedWeekendModel(false);
		fixed_true= new FixedWeekendModel(true);
		realDecider = new RealWeekendModule();
	}
	
	@Test
	public void fixed_false(){
		assertEquals(false, fixed_false.isWeekend());
	}
	@Test
	public void fixed_true(){
		assertEquals(true,fixed_true.isWeekend());
	}
//	@Test//WILL ONLY PASS ON MON-FRI, DISABLE BEFORE SUBMISSION
//	public void real_false(){
//		assertEquals(false,realDecider.isWeekend());
//	@Test
//	public void real_true(){
//		assertEquals(true,realDecider.isWeekend());
//	}


}
