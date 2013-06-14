package org.jclarity.training.chapter3;

import java.math.BigDecimal;
import static org.junit.Assert.*;

import org.jclarity.training.chapter3.TicketRevenue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the <code>TicketRevenue</code> logic
 * 
 * We've given you several use cases that need to be fulfilled.
 * 
 * Remember, RED-->GREEN-->REFACTOR and your SOLID principles
 */
public class TicketRevenueTest {

    private TicketRevenue venueRevenue;
    private BigDecimal expectedRevenue;

    @Before
    public void setUp() {
        venueRevenue = new TicketRevenue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIfLessThanZeroTicketsAreSold() {
        // TODO
    }

    @Test
    public void zeroSalesEqualsZeroRevenue() {
        // TODO
    }

    @Test
    public void oneTicketSoldIsThirtyInRevenue() {
        // TODO
    }

    @Test
    public void tenTicketsSoldIsThreeHundredInRevenue() {
        // TODO
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIfMoreThanOneHundredTicketsAreSold() {
        // TODO
    }
}