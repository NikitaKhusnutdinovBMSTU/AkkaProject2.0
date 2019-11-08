package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.japi.pf.ReceiveBuilder;

import java.util.*;


public class Storage extends AbstractActor {
    private Map<Integer, ArrayList<StorageMessage>> data = new HashMap<>();


    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(
                GetMessage.class,
                req -> {
                    getSender().tell(
                        data.get(Integer.toString(req.getPackageId())),
                        ActorRef.noSender()
                    );
                })

                .match(StorageCommand.class, msg -> {

                    if (!data.containsKey(msg.getPackageID())){
                        data.put(msg.getPackageID(), new ArrayList<>());
                    }
                    data.get(msg.getPackageID()).add(msg.getStorageMessage());
                }
                )
                .build();
    }
}
