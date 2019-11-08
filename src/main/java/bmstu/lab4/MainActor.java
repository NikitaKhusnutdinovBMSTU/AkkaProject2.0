package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

//++
public class MainActor extends AbstractActor {
    private final static int NUM_ROUND_ROBIN_POOL = 5;

    private final ActorRef executors;
    private final ActorRef storage;

    public MainActor() {
        executors = getContext().actorOf(new RoundRobinPool(NUM_ROUND_ROBIN_POOL).props(Props.create(JSExecutor.class)));
        storage = getContext().actorOf(Props.create(Storage.class));
    }

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(
                PackageDecoded.class, pack -> {
                    int len = pack.getTests().length;
                    for (int idx = 0; idx < len; idx++) {
                        executors.tell(new ExecuteMSG(idx, pack), storage);
                    }
                })
                .match(GetMessage.class, req -> storage.tell(req, sender())).build();
    }
}
