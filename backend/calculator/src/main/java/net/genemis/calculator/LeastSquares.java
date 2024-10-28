package net.genemis.calculator;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;


public class LeastSquares {

    public static Pair calculate(Collection<Pair> input) {

        AtomicReference<Double> sumX = new AtomicReference<>(0.0);
        AtomicReference<Double> sumY = new AtomicReference<>(0.0);
        AtomicReference<Double> sumXY = new AtomicReference<>(0.0);
        AtomicReference<Double> sumXX = new AtomicReference<>(0.0);
        int n = input.size();

        input.forEach(pair -> {
            sumX.updateAndGet(v -> (v + pair.x()));
            sumY.updateAndGet(v -> (v + pair.y()));
            sumXY.updateAndGet(v -> (v + pair.x() * pair.y()));
            sumXX.updateAndGet(v -> (v + pair.x() * pair.x()));
        });
        double a = (n * sumXY.get() - sumX.get() * sumY.get()) / (n * sumXX.get() - sumX.get() * sumX.get());
        double b = (sumY.get() * sumXX.get() - sumX.get() * sumXY.get()) / (n * sumXX.get() - sumX.get() * sumX.get());

        return new Pair(a, b);
    }
}
