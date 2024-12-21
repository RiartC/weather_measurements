package ch.bbw.pr.weather.model;

public class Measure {
    private String kind;
    private Double value;

    public Measure(String kind, Double value) {
        this.kind = kind;
        this.value = value;
    }

    public String getKind() {
        return kind;
    }

    public double getValue() {
        return value;
    }
}
