package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.japi.pf.ReceiveBuilder;

import java.util.*;


public class Storage extends AbstractActor {
    private Map<String, ArrayList<StorageMessage>> data = new HashMap<>();


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
                    String pID = Integer.toString(msg.getPackageID());
                    if (!data.containsKey(pID)){
                        data.put(pID, new ArrayList<>());
                    }
                    data.get(pID).add(msg.getStorageMessage());
                }
                )
                .build();
    }
}
