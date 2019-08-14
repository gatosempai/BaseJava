package dev.oruizp.feature.landing;

public class LandingData {

    private String title;
    private Feature feature;

    public LandingData(String title, Feature feature) {
        this.title = title;
        this.feature = feature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public enum Feature {
        PAGING,
        LIVEDATA,
        BROADCAST,
        SERVICE,
        ROOM
    }
}