package entities;

import java.util.Objects;

public class Floor {
    private int oneStory;
    private int twoStory;
    private int threeStory;
    private int fourStory;
    private int fiveStory;

    public Floor() {
    }

    public int getOneStory() {
        return oneStory;
    }

    public void setOneStory(int oneStory) {
        this.oneStory = oneStory;
    }

    public int getTwoStory() {
        return twoStory;
    }

    public void setTwoStory(int twoStory) {
        this.twoStory = twoStory;
    }

    public int getThreeStory() {
        return threeStory;
    }

    public void setThreeStory(int threeStory) {
        this.threeStory = threeStory;
    }

    public int getFourStory() {
        return fourStory;
    }

    public void setFourStory(int fourStory) {
        this.fourStory = fourStory;
    }

    public int getFiveStory() {
        return fiveStory;
    }

    public void setFiveStory(int fiveStory) {
        this.fiveStory = fiveStory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return oneStory == floor.oneStory && twoStory == floor.twoStory && threeStory == floor.threeStory && fourStory == floor.fourStory && fiveStory == floor.fiveStory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneStory, twoStory, threeStory, fourStory, fiveStory);
    }
}