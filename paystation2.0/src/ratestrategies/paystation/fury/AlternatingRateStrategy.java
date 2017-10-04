/*
Weekdays: progressive rate structure
Weekend: linear rate struction
*/
package ratestrategies.paystation.fury;

import Impl.paystation.fury.PayStationImpl;
import interfaces.paystation.fury.RateStrategy;
import interfaces.paystation.fury.WeekendDecider;


/**
 *
 * @author fury
 */
public class AlternatingRateStrategy implements RateStrategy{
    
    private final RateStrategy weekdayRS;
    private final RateStrategy weekendRS;
    private RateStrategy currentRS;
    private final WeekendDecider weekendModel;
    
   	public AlternatingRateStrategy(RateStrategy weekdayRs, RateStrategy weekendRs, WeekendDecider wModel){
		
	    this.weekdayRS=weekdayRs;
	    this.weekendRS=weekendRs;
	    this.weekendModel=wModel;
	}
	
	@Override
    public int rateCalculation(int cents){
	if(weekendModel.isWeekend())
		return weekendRS.rateCalculation(cents);
	else
	    return weekdayRS.rateCalculation(cents);
    }
}
