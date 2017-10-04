/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratestrategies.paystation.fury;
import java.util.Calendar;
import interfaces.paystation.fury.WeekendDecider;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author fury
 */
public class RealWeekendModule implements WeekendDecider{
	@Override
	public  boolean isWeekend(){
		Calendar cal=GregorianCalendar.getInstance();
		int day=cal.get(GregorianCalendar.DAY_OF_WEEK);
		return (day==GregorianCalendar.SUNDAY || day==GregorianCalendar.SATURDAY);
	}
}
