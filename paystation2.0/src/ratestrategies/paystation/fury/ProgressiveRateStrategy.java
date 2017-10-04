//1.  First hour: $1.50 (5 cents gives 2 minutes parking)
//2.  Second hour: $2.00 (5 cents gives 1.5 minutes)
//3.  Third and following hours: $3.00 per hour (5 cents gives 1 minute)
package ratestrategies.paystation.fury;

import interfaces.paystation.fury.RateStrategy;

/**
 *
 * @author fury
 */
public class ProgressiveRateStrategy implements RateStrategy{
    int park_time=0;
    @Override
    public int rateCalculation(int a){
	
		int time=0;
		//napkin math to get the coefficients
		//last_tier_mins+current_tier_cost*x = current_tier_mins
		//ie. 120+300x=180, solve for x
		if(a<0) return 0;
		if(a<150)//1-->24 seconds[0,149]
			time=(a*2)/5;
		else if(a<350){//150-->60 min [150,349]
			a-=150;
			time=60+(a*3)/10;
		}
		else{//$3.50-->120 min [350,inf]
			a-=350;
			time=120+(a*1)/5;
		}
		return time;
	}
}
