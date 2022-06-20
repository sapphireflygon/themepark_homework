import attractions.*;
import behaviours.IReviewed;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;
import people.Visitor;
import stalls.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ThemeParkTest {

    private ThemePark themePark;
    private ArrayList<Attraction> attractions;
    private ArrayList<Stall> stalls;
    private TobaccoStall tobaccoStall;
    private IceCreamStall iceCreamStall;
    private CandyflossStall candyflossStall;
    private RollerCoaster rollerCoaster;
    private Playground playground;
    private Park park;
    private Dodgems dodgems;
    private Visitor visitor;

    @Before
    public void before(){
        visitor = new Visitor(15, 1.7, 35.50);
        dodgems = new Dodgems("Bumper Cars", 5);
        park = new Park("Leafy Meadows", 9);
        playground = new Playground("Fun Zone", 7);
        rollerCoaster = new RollerCoaster("Blue Ridge", 10);
        tobaccoStall = new TobaccoStall("Jacks Drum", "Jack Jarvis", ParkingSpot.B1, 4);
        iceCreamStall = new IceCreamStall("Dream Cones", "Vanilla Ice", ParkingSpot.A4, 9);
        candyflossStall = new CandyflossStall("Candy Land", "Harry Belafonte", ParkingSpot.A1, 7);

        attractions = new ArrayList<>();
        attractions.add(dodgems);
        attractions.add(park);
        attractions.add(playground);
        attractions.add(rollerCoaster);

        stalls = new ArrayList<>();
        stalls.add(tobaccoStall);
        stalls.add(iceCreamStall);
        stalls.add(candyflossStall);

        themePark = new ThemePark(attractions, stalls);
    }

    @Test
    public void hasAttractionsArrayList(){
        assertEquals(attractions, themePark.getAttractions());
    }

    @Test
    public void hasStallsArrayList(){
        assertEquals(stalls, themePark.getStalls());
    }

    @Test
    public void canGetNumberOfAttractionsInPark(){
        assertEquals(4, themePark.getNumberOfAttractions());
    }

    @Test
    public void canGetNumberOfStallsInPark(){
        assertEquals(3, themePark.getNumberOfStalls());
    }

    @Test
    public void canGetAllReviewedObjects(){
        ArrayList<IReviewed> expected = new ArrayList<>();
        expected.add(tobaccoStall);
        expected.add(iceCreamStall);
        expected.add(candyflossStall);
        expected.add(dodgems);
        expected.add(park);
        expected.add(playground);
        expected.add(rollerCoaster);
        ArrayList<IReviewed> result = themePark.getAllReviewed();
        assertEquals(expected, result);
    }

    @Test
    public void canHaveVisitorVisitAnAttraction(){
        themePark.visit(visitor, rollerCoaster);
        assertEquals(1, visitor.getNumberOfAttractionsVisited());
        assertEquals(1, rollerCoaster.getVisitCount());
    }

    @Test
    public void canGetHashMapWithAllReviews(){
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("Bumper Cars", 5);
        expected.put("Leafy Meadows", 9);
        expected.put("Fun Zone", 7);
        expected.put("Blue Ridge", 10);
        expected.put("Jacks Drum", 4);
        expected.put("Dream Cones", 9);
        expected.put("Candy Land", 7);
        assertEquals(expected, themePark.getHashMapOfAllReviews());
    }

    @Test
    public void canGetAllAllowedForAVisitor(){
        ArrayList<IReviewed> expected = new ArrayList<>();
        expected.add(iceCreamStall);
        expected.add(candyflossStall);
        expected.add(dodgems);
        expected.add(park);
        expected.add(playground);
        expected.add(rollerCoaster);
        assertEquals(expected, themePark.getAllAllowedFor(visitor));
    }
}
