package interfaces.paystation.fury;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fury
 */
public interface RateStrategy {
    
     /**
     * Handles the $$ to minute conversion for the a PayStation implementation
     *
     * @return The minutes purchased
     */
    public int rateCalculation(int a);
}
