package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;


public class Storage extends AbstractActor {
    private ArrayList<PackageDecoded> data;

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(GetMessage.class,
                req -> getSender().tell(data.get(req.getPackageId()),
                        ActorRef.noSender())).match(PackageDecoded.class, msg -> {
                            data.add(msg);
        })
                .build();
    }
}
