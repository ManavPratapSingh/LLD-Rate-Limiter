package modules.strategies;

import modules.interfaces.IFilterStrategy;
import java.util.LinkedList;
import java.util.Queue;


public class MovingWindowStrategy implements IFilterStrategy {

    private static final int MAX_REQUESTS = 3;
    private static final long WINDOW_MS = 1000;

    private final Queue<Long> timestamps = new LinkedList<>();

    @Override
    public synchronized boolean isAllowed() {
        long now = System.currentTimeMillis();
        
        while (!timestamps.isEmpty() && (now - timestamps.peek() > WINDOW_MS)) {
            timestamps.poll();
        }
        
        if (timestamps.size() < MAX_REQUESTS) {
            timestamps.add(now);
            return true;
        }

        return false;
    }
}
