import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Solution {

    private record Pair(int freq, int value) {}

    public long[] findXSum(int[] nums, int k, int x) {
        if (nums.length < k) {
            return new long[0];
        }

        long[] answer = new long[nums.length - k + 1];
        Map<Integer, Integer> freq = new HashMap<>();

        // Sorts from best to worst
        Comparator<Pair> comparator = (a, b) -> {
            if (a.freq != b.freq) {
                return Integer.compare(b.freq, a.freq);
            }
            return Integer.compare(b.value, a.value);
        };

        TreeSet<Pair> topX = new TreeSet<>(comparator);
        TreeSet<Pair> others = new TreeSet<>(comparator);

        long topXSum = 0;
        long windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            others.add(new Pair(entry.getValue(), entry.getKey()));
        }

        while (topX.size() < x && !others.isEmpty()) {
            Pair p = others.pollFirst();
            topX.add(p);
            topXSum += (long) p.value * p.freq;
        }

        answer[0] = freq.size() < x ? windowSum : topXSum;

        for (int i = k; i < nums.length; i++) {
            int inNum = nums[i];
            int outNum = nums[i - k];

            windowSum += inNum - outNum;

            int oldFreqOut = freq.get(outNum);
            Pair oldPairOut = new Pair(oldFreqOut, outNum);
            if (topX.remove(oldPairOut)) {
                topXSum -= (long) oldPairOut.value * oldPairOut.freq;
            } else {
                others.remove(oldPairOut);
            }

            int newFreqOut = oldFreqOut - 1;
            if (newFreqOut > 0) {
                freq.put(outNum, newFreqOut);
                others.add(new Pair(newFreqOut, outNum));
            } else {
                freq.remove(outNum);
            }

            int oldFreqIn = freq.getOrDefault(inNum, 0);
            if (oldFreqIn > 0) {
                Pair oldPairIn = new Pair(oldFreqIn, inNum);
                if (topX.remove(oldPairIn)) {
                    topXSum -= (long) oldPairIn.value * oldPairIn.freq;
                } else {
                    others.remove(oldPairIn);
                }
            }
            
            int newFreqIn = oldFreqIn + 1;
            freq.put(inNum, newFreqIn);
            others.add(new Pair(newFreqIn, inNum));

            while (topX.size() < x && !others.isEmpty()) {
                Pair toPromote = others.pollFirst();
                topX.add(toPromote);
                topXSum += (long) toPromote.value * toPromote.freq;
            }

            // --- THIS IS THE FIX ---
            // Swap if the worst of topX is WORSE than the best of others.
            // A positive compare result means the first argument is "worse".
            while (!topX.isEmpty() && !others.isEmpty() && comparator.compare(topX.last(), others.first()) > 0) {
                Pair worstTop = topX.pollLast();
                Pair bestOther = others.pollFirst();
                
                topXSum -= (long) worstTop.value * worstTop.freq;
                topXSum += (long) bestOther.value * bestOther.freq;

                topX.add(bestOther);
                others.add(worstTop);
            }
            
            while (topX.size() > x) {
                Pair toDemote = topX.pollLast();
                others.add(toDemote);
                topXSum -= (long) toDemote.value * toDemote.freq;
            }

            answer[i - k + 1] = freq.size() < x ? windowSum : topXSum;
        }

        return answer;
    }
}