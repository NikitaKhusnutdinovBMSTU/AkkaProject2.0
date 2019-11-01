package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class Manager extends AbstractActor {

    private final ActorRef executors;
    private final ActorRef storage;

    public Manager(){
        //??
        executors = getContext().actorOf(new RoundRobinPool(5).props(Props.create(JSExecutor.class)));
        storage = getContext().actorOf(Props.create(Storage.class));
    }

    @Override
    public Receive createReceive() {

        return receiveBuilder().match(PackageDecoded.class, pack -> {
            for(x : pack.getTests())

            }
        });
    }
}
