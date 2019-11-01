package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;


public class Storage extends AbstractActor {
    private HashMap<Integer, PackageDecoded> data = new HashMap<>();

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(GetMessage.class,
                req -> getSender().tell(data.get(req.getPackageId()),
                        ActorRef.noSender()))
                .match(PackageDecoded.class, msg -> {
                    
                    System.out.println("MSG->" + msg.getFunctionName());
                    data.put(msg.getPackageId(), msg);
                })
                .build();
    }
}
