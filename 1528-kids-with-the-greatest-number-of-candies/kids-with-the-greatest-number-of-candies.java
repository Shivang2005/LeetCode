class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int n = candies.length;
        ArrayList<Boolean> result = new ArrayList<>();

        int maxCandies = candies[0];
        for (int i = 0; i < n; i++){
            if (candies[i] > maxCandies){
                maxCandies = candies[i];
            }
        }

        for (int i = 0; i < n; i++){
            if (candies[i] + extraCandies >= maxCandies) {
                result.add(true);
            }
            else {
                result.add(false);
            }
        }
        return result;
    }
}