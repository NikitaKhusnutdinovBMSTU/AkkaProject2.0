package bmstu.lab4;

import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storage extends AbstractActor {
    private Map<Integer, PackageDecoded> data;

    @Override
    public Receive createReceive() {
        return null;
    }
}
