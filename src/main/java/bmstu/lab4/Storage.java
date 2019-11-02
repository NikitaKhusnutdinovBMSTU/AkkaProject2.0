package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.japi.pf.ReceiveBuilder;
import java.util.ArrayList;
import java.util.HashMap;


public class Storage extends AbstractActor {
    private HashMap<Integer, ArrayList<StorageMessage>> data = new HashMap<>();

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(
                GetMessage.class,
                req -> {
                    System.out.println("ID: " + req.getPackageId());
                    getSender().tell(
                        data.get(req.getPackageId()).toArray(),
                        ActorRef.noSender()
                    );
                })

                .match(StorageCommand.class, msg ->{
                    if(data.containsKey(msg.getPackageID())) {
                        ArrayList<StorageMessage> localData = data.get(msg.getPackageID());
                        localData.add(msg.getStorageMessage());
                        data.put(msg.getPackageID(), localData);
                    }else{
                        ArrayList<StorageMessage> initList = new ArrayList<>();
                        initList.add(msg.getStorageMessage());
                        data.put(msg.getPackageID(), initList);
                        System.out.println(data.get(11));
                    }
                }
                )
                .build();
    }
}
