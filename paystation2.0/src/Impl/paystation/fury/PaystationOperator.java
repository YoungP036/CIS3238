/*
    Simulates a running paystation via the command line
 */
package Impl.paystation.fury;
import interfaces.paystation.fury.PayStation;
import interfaces.paystation.fury.Receipt;
import ratestrategies.paystation.fury.LinearRateStrategy;
import java.util.Scanner;
import java.util.Map;
import ratestrategies.paystation.fury.AlternatingRateStrategy;
import ratestrategies.paystation.fury.ProgressiveRateStrategy;
import ratestrategies.paystation.fury.RealWeekendModule;

public class PaystationOperator {

    public static void main(String[] args) throws IllegalCoinException {
        String cmd;
        Scanner in = new Scanner(System.in);
        boolean sentinal = true;
		PayStation ps = new PayStationImpl(new LinearRateStrategy());
	
        while(sentinal){
            System.out.println("Select an operation:");
	    System.out.println("\t0) Deposit $1");
            System.out.println("\t1) Deposit coin");
            System.out.println("\t2) Show parking time based on current coins entered");
            System.out.println("\t3) Buy ticket");
            System.out.println("\t4) Cancel");
            System.out.println("\t5) Change rate strategy");
            System.out.print("\t6) Exit\n>>");
            cmd=in.next();
         
	    System.out.println("\n\n");
            switch(cmd){
				case "0": //convenience, add $1
					ps.addPayment(25);
					ps.addPayment(25);
					ps.addPayment(25);
					ps.addPayment(25);
					break;
                case "1": //deposit coin
                    System.out.println("Select a coin to enter: 5,10 or 25 cents");
                    cmd=in.next();
		    //checking inputs here because I dont want exceptions thrown each time
		    //someone messes up. Run tests to see that there is exception thrown as assignment directs
                    switch(cmd){
                        case "5":
							ps.addPayment(5);
							break;
                        case "10":
							ps.addPayment(10);
							break;
                        case "25":
							ps.addPayment(25);
							break;                        
                        default : System.out.println("Invalid coin\n");
                    }
                    break;
					
                case "2": 
					System.out.println(ps.readDisplay() + " minutes\n\n");
                    break;
					
                case "3": //buy ticket
					Receipt slip = ps.buy();
                    System.out.println("Receipt for " + slip.value() + " minutes given\n\n");
					break;
                
		case "4": //cancel
			Map<Integer,Integer> coins_out = ps.cancel();
			if(coins_out.containsKey(5))
				System.out.println(coins_out.get(5) + " Nickels returned");
			if(coins_out.containsKey(10))
				System.out.println(coins_out.get(10) + " Dimes returned");
			if(coins_out.containsKey(25))
				System.out.println(coins_out.get(25) + " Quarters returned");
                    break;
                
		case "5": //change rate
			if(ps.readDisplay()==0){
				System.out.println("Select a rate");
				System.out.println("\t1) Linear rate strategy");
				System.out.println("\t2) Progressive rate strategy");
				System.out.println("\t3) Alternating rate strategy\n>>");
				cmd = in.next();
				System.out.println("\n\n");
				switch(cmd){
					case "1":
						ps = new PayStationImpl(new LinearRateStrategy());
						break;
					case "2":
						ps = new PayStationImpl(new ProgressiveRateStrategy());
						break;
					case "3":
						ps = new PayStationImpl(new AlternatingRateStrategy(new LinearRateStrategy(), new ProgressiveRateStrategy(), new RealWeekendModule()));
						break;
				}//end RS picker switch
					}//Allow RS change if
			else{
				System.out.println("ERROR: Changing the rate is not allowed with money in play, please remove money and try again\n\n");
				break;
			}//end not empty else
			break;
                case "6": //exit
                    sentinal=false;
                    break;
                default:
                    System.out.println("Invalid Command");

            }//end main decider switch
        }//end sentinal while
    }//end main   
}//end class
