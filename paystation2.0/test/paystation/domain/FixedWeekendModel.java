package paystation.domain;

import interfaces.paystation.fury.WeekendDecider;

/*
	Test stub module for mocking day of week. Assists in testing process.
	Test case will define the return of weekendFlag
	If constructor is passed true, isWeekend will return true.
*/
public class FixedWeekendModel implements WeekendDecider{
	
	private final boolean today;
	public FixedWeekendModel(boolean flag){
		today=flag;
	}
	@Override
	public boolean isWeekend(){
		return today;
	}
}
