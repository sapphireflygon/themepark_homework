package attractions;

import org.junit.Before;
import org.junit.Test;
import people.Visitor;

import static org.junit.Assert.assertEquals;

public class RollercoasterTest {

    RollerCoaster rollerCoaster;

    @Before
    public void setUp() {
        rollerCoaster = new RollerCoaster("Blue Ridge", 10);
    }

    @Test
    public void hasName() {
        assertEquals("Blue Ridge", rollerCoaster.getName());
    }

    @Test
    public void hasRating() {
        assertEquals(10, rollerCoaster.getRating());
    }

    @Test
    public void hasVisitCount() {
        assertEquals(0, rollerCoaster.getVisitCount());
    }

    @Test
    public void visitorOver145cmTallCanRide(){
        Visitor visitor = new Visitor(14, 1.7, 80.0);
        assertEquals(true, rollerCoaster.isAllowedTo(visitor));
    }

    @Test
    public void visitorAgedOver12CanRide(){
        Visitor visitor = new Visitor(14, 1.7, 80.0);
        assertEquals(true, rollerCoaster.isAllowedTo(visitor));
    }

    @Test
    public void visitorAgedUnder12CannotRide(){
        Visitor visitor = new Visitor(9, 1.7, 80.0);
        assertEquals(false, rollerCoaster.isAllowedTo(visitor));
    }

    @Test
    public void visitorUnder145cmTallCannotRide(){
        Visitor visitor = new Visitor(14, 1.2, 80.0);
        assertEquals(false, rollerCoaster.isAllowedTo(visitor));
    }

    @Test
    public void hasDefaultPrice(){
        assertEquals(8.40, rollerCoaster.defaultPrice(), 0.00);
    }

    @Test
    public void chargesVisitorUnder200cmDefaultPrice(){
        Visitor visitor = new Visitor(14, 1.9, 80.0);
        assertEquals(8.40, rollerCoaster.priceFor(visitor), 0.00);
    }

    @Test
    public void willChargeDoubleIfVisitorOver200cmTall(){
        Visitor visitor = new Visitor(14, 2.3, 80.0);
        assertEquals(16.80, rollerCoaster.priceFor(visitor), 0.00);
    }
}
