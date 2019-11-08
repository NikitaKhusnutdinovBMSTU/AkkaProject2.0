package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import javafx.util.Pair;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSExecutor extends AbstractActor {


    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(ExecuteMSG.class, m -> {
            //initialisePair(m.getMsg());
            Pair<Integer, PackageDecoded> receivedMSG = m.getMsg();
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            String jsScript = receivedMSG.getValue().getJSScript();
            String functionName = receivedMSG.getValue().getFunctionName();
            Object[] params = receivedMSG.getValue().getTest(receivedMSG.getKey()).getParams();
            try {
                engine.eval(jsScript);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            Invocable invocable = (Invocable) engine;
            String res = invocable.invokeFunction(functionName, params).toString();
            String checker;
            TestDecoded curTest = receivedMSG.getValue().getTest(receivedMSG.getKey());
            if(res.toLowerCase().equals(curTest.getExpectedResult().toLowerCase())){
                checker = "CORRECT ANSWER!";
            }else{
                checker = "WRONG ANSWER!";
            }
            StorageMessage sMsg = new StorageMessage(
                    res,
                    curTest.getExpectedResult(),
                    checker,
                    params,
                    curTest.getTestName()
            );
            StorageCommand storageCommand = new StorageCommand(receivedMSG.getKey(), sMsg);
            getSender().tell(storageCommand, ActorRef.noSender());
        }).build();
    }

}
