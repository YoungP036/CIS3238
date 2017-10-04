/**
 * Implementation of Receipt.
 *
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package Impl.paystation.fury;

import interfaces.paystation.fury.Receipt;

public class ReceiptImpl implements Receipt {

    private int value;

    public ReceiptImpl(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }
}
