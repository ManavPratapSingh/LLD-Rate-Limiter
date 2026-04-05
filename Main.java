import modules.RemoteResource;
import modules.RateLimiterProxy;
import modules.strategies.MovingWindowStrategy;
import modules.interfaces.IRemoteResource;

/**
 * Main application to demonstrate the Sliding Window Rate Limiter.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- High-Performance Rate Limiter Simulation ---");
        
        // 1. Create the real resource
        IRemoteResource realResource = new RemoteResource();

        // 2. Create the strategy (Constraints are now hardcoded for demonstration: 3 req / 1 sec)
        MovingWindowStrategy strategy = new MovingWindowStrategy();

        // 3. Wrap it in a Proxy
        IRemoteResource proxy = new RateLimiterProxy(realResource, strategy);

        // --- SIMULATION ---
        System.out.println("\nSimulation: Sending 5 rapid requests (Limit: 3 per sec)...");

        for (int i = 1; i <= 5; i++) {
            System.out.print("Request #" + i + ": ");
            String response = proxy.get_request();
            System.out.println(response);
            
            // Minimal sleep to simulate real-world arrival times
            try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println("\n--- Waiting for 1.2 seconds for the window to slide... ---");
        try { Thread.sleep(1200); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Request #6 (After waiting): " + proxy.get_request());

        System.out.println("\n--- Rapid fire again (3 requests)... ---");
        for (int i = 7; i <= 9; i++) {
            System.out.println("Request #" + i + ": " + proxy.get_request());
            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        
        System.out.println("Request #10 (Expected Fail): " + proxy.get_request());
    }
}
