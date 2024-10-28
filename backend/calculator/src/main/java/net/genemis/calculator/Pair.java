package net.genemis.calculator;

public record Pair(double x, double y) {

    public static Pair of(double x, double y) {
        return new Pair(x, y);
    }
}
