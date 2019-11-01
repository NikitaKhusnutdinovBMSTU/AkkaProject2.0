package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;
import javafx.util.Pair;

public class Manager extends AbstractActor {

    private final ActorRef executors;
    private final ActorRef storage;

    public Manager(){
        executors = getContext().actorOf(new RoundRobinPool(5).props(Props.create(JSExecutor.class)));
        storage = getContext().actorOf(Props.create(Storage.class));
    }

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(PackageDecoded.class, pack -> {
            int len = pack.getTests().length;
            for(int i = 0; i < len; i++){
                executors.tell(new ExecuteMSG(i, pack), storage);
            }
        }).match(GetMessage.class, req -> storage.tell()).build();
    }
}
