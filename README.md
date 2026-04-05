# 🛡️ LLD: Moving Window Rate Limiter

A high-performance Java implementation of a **Sliding Window Log** (Moving Window) rate limiter. This project demonstrates the **Proxy** and **Strategy** design patterns to protect a remote resource from traffic bursts and abuse.

---

## 🏗️ Design Patterns

- **Proxy Pattern**: The `RateLimiterProxy` intercepts calls to the `RemoteResource`, acting as a middleware to enforce traffic rules.
- **Strategy Pattern**: The `MovingWindowStrategy` encapsulates the specific rate-limiting algorithm, allowing for easy swapping with other algorithms (e.g., Token Bucket, Leaky Bucket).

## 🚦 Core Logic: Moving Window
Unlike a Fixed Window algorithm, the **Moving Window** (Sliding Window Log) ensures that the limit is enforced relative to the current timestamp of every request. This prevents "double-dipping" at window boundaries.
- **Lazy Cleanup**: Old timestamps are removed from the queue only when a new request arrives, keeping resource usage efficient.

---

## 🛠️ Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher.

### Compilation
Compile all source files into a `bin` directory:
```bash
mkdir bin
javac -d bin Main.java modules/RemoteResource.java modules/RateLimiterProxy.java modules/interfaces/*.java modules/strategies/*.java
```

### Execution
Run the simulation:
```bash
java -cp bin Main
```

---

## 🧪 Expected Test Output

The simulation is pre-configured with a limit of **3 requests per 1-second window**.

```text
--- High-Performance Rate Limiter Simulation ---

Simulation: Sending 5 rapid requests (Limit: 3 per sec)...
Request #1: SUCCESS: Resource accessed! (Data fetched)
Request #2: SUCCESS: Resource accessed! (Data fetched)
Request #3: SUCCESS: Resource accessed! (Data fetched)
Request #4: 429 Too Many Requests: Rate limit exceeded.
Request #5: 429 Too Many Requests: Rate limit exceeded.

--- Waiting for 1.2 seconds for the window to slide... ---
Request #6 (After waiting): SUCCESS: Resource accessed! (Data fetched)

--- Rapid fire again (3 requests)... ---
Request #7: SUCCESS: Resource accessed! (Data fetched)
Request #8: SUCCESS: Resource accessed! (Data fetched)
Request #9: 429 Too Many Requests: Rate limit exceeded.
Request #10 (Expected Fail): 429 Too Many Requests: Rate limit exceeded.
```

---

## 📦 Project Structure
- `modules/interfaces/`: Core contracts (`IRemoteResource`, `IFilterStrategy`).
- `modules/strategies/`: Concrete rate-limiting algorithms.
- `modules/`: Proxy and Resource implementations.
- `Main.java`: Simulation entry point.

---
<p align="center">Built for Low-Level Design Excellence</p>
