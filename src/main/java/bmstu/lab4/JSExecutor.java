package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import javafx.util.Pair;

public class JSExecutor extends AbstractActor {

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(ExecuteMSG.class, m -> {
            Pair<Integer, PackageDecoded> receivedMSG = m.getMsg();

        }).build();
    }
}
