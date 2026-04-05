package modules;

import modules.interfaces.IRemoteResource;


public class RemoteResource implements IRemoteResource {
    @Override
    public String get_request() {
        return "SUCCESS: Resource accessed! (Data fetched)";
    }
}
