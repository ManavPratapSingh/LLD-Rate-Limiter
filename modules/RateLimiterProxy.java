package modules;

import modules.interfaces.IRemoteResource;
import modules.interfaces.IFilterStrategy;


public class RateLimiterProxy implements IRemoteResource {
    private final IRemoteResource realResource;
    private final IFilterStrategy strategy;

    public RateLimiterProxy(IRemoteResource realResource, IFilterStrategy strategy) {
        this.realResource = realResource;
        this.strategy = strategy;
    }

    @Override
    public String get_request() {
        if (strategy.isAllowed()) {
            return realResource.get_request();
        } else {
            return "429 Too Many Requests: Rate limit exceeded.";
        }
    }
}
