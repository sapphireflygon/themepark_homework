import attractions.Attraction;
import attractions.RollerCoaster;
import behaviours.IReviewed;
import behaviours.ISecurity;
import org.w3c.dom.Attr;
import people.Visitor;
import stalls.Stall;

import java.util.ArrayList;
import java.util.HashMap;

public class ThemePark {
    private ArrayList<Attraction> attractions;
    private ArrayList<Stall> stalls;

    public ThemePark(ArrayList<Attraction> attractions, ArrayList<Stall> stalls) {
        this.attractions = attractions;
        this.stalls = stalls;
    }

    public ArrayList<Attraction> getAttractions() {
        return this.attractions;
    }

    public ArrayList<Stall> getStalls() {
        return this.stalls;
    }

    public int getNumberOfAttractions() {
        return this.attractions.size();
    }

    public int getNumberOfStalls(){
        return this.stalls.size();
    }

    public ArrayList<IReviewed> getAllReviewed() {
        ArrayList<IReviewed> reviewed = new ArrayList<>();
        for (Stall stall : this.stalls) {
            if (stall instanceof IReviewed) {
                reviewed.add(stall);
            }
        }
        for (Attraction attraction : this.attractions) {
            if (attraction instanceof IReviewed) {
                reviewed.add(attraction);
            }
        }
        return reviewed;
        // Not sure if necessary to do all this checking if all stalls&attractions are implementing IReviewed
        // but this won't break if that were to change for whatever reason
    }

    public void visit(Visitor visitor, Attraction attraction) {
        attraction.increaseVisitCount();
        visitor.addAttraction(attraction);
    }

    public HashMap<String, Integer> getHashMapOfAllReviews() {
        HashMap<String, Integer> reviewHashMap = new HashMap<>();
        ArrayList<IReviewed> reviews;
        reviews = this.getAllReviewed();
        for (IReviewed attraction : reviews) {
            reviewHashMap.put(attraction.getName(), attraction.getRating());
        }
        return reviewHashMap;
    }

    public ArrayList<IReviewed> getAllAllowedFor(Visitor visitor) {
        ArrayList<IReviewed> allowedArrayList = new ArrayList<>();
        ArrayList<IReviewed> revieweds;
        revieweds = this.getAllReviewed();
        for (IReviewed attraction : revieweds) {
            if (!(attraction instanceof ISecurity)) {
                allowedArrayList.add(attraction);
            } else {
                if (((ISecurity) attraction).isAllowedTo(visitor)) {
                    allowedArrayList.add(attraction);
                }
            }
        }
        return allowedArrayList;
    }
}
