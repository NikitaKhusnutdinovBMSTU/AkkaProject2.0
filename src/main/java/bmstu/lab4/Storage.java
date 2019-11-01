package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Storage extends AbstractActor {
    private Set<PackageDecoded> data;

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(GetMessage.class, req -> sender().tell());
    }
}
