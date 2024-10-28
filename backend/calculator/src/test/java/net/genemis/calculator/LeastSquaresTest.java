package net.genemis.calculator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeastSquaresTest {

    @Test
    public void testLeastSquares() {
        List<Pair> data = List.of(Pair.of(1, 2), Pair.of(2, 3), Pair.of(3, 4));
        Pair result = LeastSquares.calculate(data);
        assertEquals(1, result.x(), 0.0001);
        assertEquals(1, result.y(), 0.0001);

        data = List.of(Pair.of(1, 2), Pair.of(2, 1), Pair.of(3, 0));
        result = LeastSquares.calculate(data);
        assertEquals(-1, result.x(), 0.0001);
        assertEquals(3, result.y(), 0.0001);

        data = List.of(Pair.of(-1, -1), Pair.of(1, 1), Pair.of(3, 3), Pair.of(10, 10));
        result = LeastSquares.calculate(data);
        assertEquals(1, result.x(), 0.0001);
        assertEquals(0, result.y(), 0.0001);

        data = List.of(Pair.of(0, -1), Pair.of(0, 1), Pair.of(0, 3), Pair.of(0, 10));
        result = LeastSquares.calculate(data);
        assertEquals(Double.NaN, result.x(), 0.0001);
        assertEquals(Double.NaN, result.y(), 0.0001);

        data = List.of(Pair.of(1, -1), Pair.of(2, -1), Pair.of(3, -1), Pair.of(10, -1));
        result = LeastSquares.calculate(data);
        assertEquals(0, result.x(), 0.0001);
        assertEquals(-1, result.y(), 0.0001);
    }
}
