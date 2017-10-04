package ratestrategies.paystation.fury;

import interfaces.paystation.fury.RateStrategy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fury
 */
public class LinearRateStrategy implements RateStrategy{
    
    @Override
    public int rateCalculation(int a){
	return a/5*2;
    }
   
}
