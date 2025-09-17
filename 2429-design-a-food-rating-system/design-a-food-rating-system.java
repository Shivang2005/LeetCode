import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class FoodRatings {

    private static class Food implements Comparable<Food> {
        String name;
        String cuisine;
        int rating;

        Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }

        @Override
        public int compareTo(Food other) {
            if (this.rating != other.rating) {
                return Integer.compare(other.rating, this.rating);
            }
            return this.name.compareTo(other.name);
        }
    }

    private Map<String, Integer> foodRatingMap;
    private Map<String, String> foodCuisineMap;
    private Map<String, PriorityQueue<Food>> cuisineFoodMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodRatingMap = new HashMap<>();
        foodCuisineMap = new HashMap<>();
        cuisineFoodMap = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodRatingMap.put(food, rating);
            foodCuisineMap.put(food, cuisine);

            cuisineFoodMap.computeIfAbsent(cuisine, k -> new PriorityQueue<>())
                          .add(new Food(food, cuisine, rating));
        }
    }

    public void changeRating(String food, int newRating) {
        foodRatingMap.put(food, newRating);
        String cuisine = foodCuisineMap.get(food);
        cuisineFoodMap.get(cuisine).add(new Food(food, cuisine, newRating));
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineFoodMap.get(cuisine);
        Food highest = pq.peek();
        while (foodRatingMap.get(highest.name) != highest.rating) {
            pq.poll();
            highest = pq.peek();
        }
        return highest.name;
    }
}