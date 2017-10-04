package paystation.domain;



import Impl.paystation.fury.IllegalCoinException;
import Impl.paystation.fury.PayStationImpl;
import interfaces.paystation.fury.PayStation;
import interfaces.paystation.fury.WeekendDecider;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ratestrategies.paystation.fury.AlternatingRateStrategy;
import ratestrategies.paystation.fury.LinearRateStrategy;
import ratestrategies.paystation.fury.ProgressiveRateStrategy;
import ratestrategies.paystation.fury.RealWeekendModule;

/*
Weekdays: Progressive
Weekend: linear
*/
public class AlternatingRateTest {

	PayStation ps_forced_weekday;
	PayStation ps_forced_weekend;
	PayStation ps_real;
	
	@Before
	public void setUp() {
		WeekendDecider isWeekend = new FixedWeekendModel(true);
		WeekendDecider notWeekend = new FixedWeekendModel(false);
		WeekendDecider realDay = new RealWeekendModule();
		ps_real = new PayStationImpl(new AlternatingRateStrategy(new ProgressiveRateStrategy(), new LinearRateStrategy(), realDay));
		ps_forced_weekday= new PayStationImpl(new AlternatingRateStrategy(new ProgressiveRateStrategy(), new LinearRateStrategy(), notWeekend));
		ps_forced_weekend=new PayStationImpl(new AlternatingRateStrategy(new ProgressiveRateStrategy(), new LinearRateStrategy(), isWeekend));
	}
	@Test
	public void realweekdayWithDisplayIntegration() throws IllegalCoinException{
		addDollar(ps_real); 
		addDollar(ps_real);
		assertEquals(75, ps_real.readDisplay());
	}
    @Test
    public void realweekdayFourHour() throws IllegalCoinException{
		for(int i=0;i<9;i++)
			addDollar(ps_real);
		addHalfDollar(ps_real);
		assertEquals(240,ps_real.readDisplay());
    }
    @Test
    public void realweekdayThreeHour() throws IllegalCoinException{
		for(int i=0;i<6;i++)
			addDollar(ps_real);
		addHalfDollar(ps_real);
		assertEquals(180,ps_real.readDisplay());
    }
    @Test
    public void realweekdayTwoHour() throws IllegalCoinException{
		for(int i=0;i<3;i++)
			addDollar(ps_real);
		addHalfDollar(ps_real);
		assertEquals(120,ps_real.readDisplay());
    }

    @Test
    public void realweekdayOneHour() throws IllegalCoinException{
		addDollar(ps_real);
		addHalfDollar(ps_real);
		assertEquals(60,ps_real.readDisplay());
    } 
	
	    /*CONVENIENCE METHODS*/
    private void addDollar(PayStation ps) throws IllegalCoinException{
		for(int i=0;i<4;i++)
			ps.addPayment(25);
		}
    /*CONVENIENCE METHODS*/
    private void addHalfDollar(PayStation ps) throws IllegalCoinException{
	    ps.addPayment(25);
	    ps.addPayment(25);
    }
	
	@Test
	public void weekdayWithDisplayIntegration() throws IllegalCoinException{
		addDollar(ps_forced_weekday); 
		addDollar(ps_forced_weekday);
		assertEquals(75, ps_forced_weekday.readDisplay());
	}
    @Test
    public void weekdayFourHour() throws IllegalCoinException{
		for(int i=0;i<9;i++)
			addDollar(ps_forced_weekday);
		addHalfDollar(ps_forced_weekday);
		assertEquals(240,ps_forced_weekday.readDisplay());
    }
    @Test
    public void weekdayThreeHour() throws IllegalCoinException{
		for(int i=0;i<6;i++)
			addDollar(ps_forced_weekday);
		addHalfDollar(ps_forced_weekday);
		assertEquals(180,ps_forced_weekday.readDisplay());
    }
    @Test
    public void weekdayTwoHour() throws IllegalCoinException{
		for(int i=0;i<3;i++)
			addDollar(ps_forced_weekday);
		addHalfDollar(ps_forced_weekday);
		assertEquals(120,ps_forced_weekday.readDisplay());
    }

    @Test
    public void weekdayOneHour() throws IllegalCoinException{
		addDollar(ps_forced_weekday);
		addHalfDollar(ps_forced_weekday);
		assertEquals(60,ps_forced_weekday.readDisplay());
    } 
	
	/////////////////////////////////////////////////////BEGIN WEEKEND/////////////////////////////
    @Test
    public void weekend14minfor35cents()throws IllegalCoinException {
        ps_forced_weekend.addPayment(10);
        ps_forced_weekend.addPayment(25);
        assertEquals(14, ps_forced_weekend.readDisplay());
    }
	
	@Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps_forced_weekend.addPayment(17);
    }
	
	@Test
    public void weekend14minfor35cents10MinFor25Cents() throws IllegalCoinException {
        ps_forced_weekend.addPayment(25);
        assertEquals(10, ps_forced_weekend.readDisplay());
    }
	
	@Test
    public void weekend14minfor35cents2MinFor5Cents() throws IllegalCoinException {
        ps_forced_weekend.addPayment(5);
        assertEquals(2, ps_forced_weekend.readDisplay());
    }
	@Test 
	public void weekendOneTwoAndThreeHours() throws IllegalCoinException{
		addDollar(ps_forced_weekend);
		addHalfDollar(ps_forced_weekend);
		assertEquals(60,ps_forced_weekend.readDisplay());		
		addDollar(ps_forced_weekend);
		addHalfDollar(ps_forced_weekend);
		assertEquals(120,ps_forced_weekend.readDisplay());
		addDollar(ps_forced_weekend);
		addHalfDollar(ps_forced_weekend);
		assertEquals(180,ps_forced_weekend.readDisplay());
		addHalfDollar(ps_forced_weekend);
		assertEquals(200,ps_forced_weekend.readDisplay());
	}
}
