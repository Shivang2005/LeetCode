import java.util.*;

class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        if (n < 4) return 0;

        Map<Long, Map<Long, Integer>> lines = new HashMap<>();
        Map<Long, Map<Long, Integer>> midpoints = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                int dy = y2 - y1;
                int dx = x2 - x1;
                int g = gcd(Math.abs(dy), Math.abs(dx));
                dy /= g;
                dx /= g;

                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                
                long slopeKey = (((long) dy) << 32) | (dx & 0xFFFFFFFFL);
                long intercept = -1L * dy * x1 + 1L * dx * y1;
                
                lines.computeIfAbsent(slopeKey, k -> new HashMap<>())
                     .merge(intercept, 1, Integer::sum);

                long midX = x1 + x2; 
                long midY = y1 + y2;
                long midKey = (midX << 32) | (midY & 0xFFFFFFFFL);
                
                midpoints.computeIfAbsent(midKey, k -> new HashMap<>())
                         .merge(slopeKey, 1, Integer::sum);
            }
        }

        long totalTrapezoids = 0;

        for (Map<Long, Integer> map : lines.values()) {
            if (map.size() < 2) continue;
            long currentSlopeCount = 0;
            long sumSquares = 0;
            for (int count : map.values()) {
                currentSlopeCount += count;
                sumSquares += (long) count * count;
            }
            totalTrapezoids += (currentSlopeCount * currentSlopeCount - sumSquares) / 2;
        }

        long parallelograms = 0;
        for (Map<Long, Integer> map : midpoints.values()) {
            long totalPairs = 0;
            long sumSq = 0;
            for (int count : map.values()) {
                totalPairs += count;
                sumSq += (long) count * count;
            }
            parallelograms += (totalPairs * totalPairs - sumSq) / 2;
        }

        return (int) (totalTrapezoids - parallelograms);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}