package attractions;

import behaviours.IReviewed;

public abstract class Attraction implements IReviewed {
    private String name;
    private int rating;
    private int visitCount;

    public Attraction(String name, int rating) {
        this.name = name;
        this.rating = rating;
        this.visitCount = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getRating() {
        return this.rating;
    }

    public int getVisitCount() {
        return this.visitCount;
    }

    public void increaseVisitCount(){
        this.visitCount += 1;
    }
}
