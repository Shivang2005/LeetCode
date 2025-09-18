import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskManager {
    // Stores {userId, taskId, priority, operationId}
    private PriorityQueue<long[]> pq;
    
    // Maps taskId -> {userId, priority, operationId}
    private Map<Integer, long[]> taskMap;
    
    // A unique, incrementing counter for each add/edit operation
    private long operationCounter;

    public TaskManager(List<List<Integer>> tasks) {
        // Comparator is the same, but uses Long.compare
        this.pq = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) {
                return Long.compare(b[2], a[2]);
            }
            return Long.compare(b[1], a[1]);
        });

        this.taskMap = new HashMap<>();
        this.operationCounter = 0;

        for (List<Integer> task : tasks) {
            add(task.get(0), task.get(1), task.get(2));
        }
    }

    public void add(int userId, int taskId, int priority) {
        long opId = ++operationCounter;
        taskMap.put(taskId, new long[]{userId, priority, opId});
        pq.offer(new long[]{userId, taskId, priority, opId});
    }

    public void edit(int taskId, int newPriority) {
        long opId = ++operationCounter;
        long userId = taskMap.get(taskId)[0];
        taskMap.put(taskId, new long[]{userId, newPriority, opId});
        pq.offer(new long[]{userId, taskId, newPriority, opId});
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId);
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            long[] topTask = pq.peek();
            int taskId = (int) topTask[1];
            long opId = topTask[3];

            // THE FIX: Check the operationId to ensure this isn't a stale task.
            if (taskMap.containsKey(taskId) && taskMap.get(taskId)[2] == opId) {
                pq.poll();
                taskMap.remove(taskId);
                return (int) topTask[0]; // Return the correct userId
            } else {
                // This is a stale task (removed or edited), so discard it.
                pq.poll();
            }
        }
        return -1;
    }
}