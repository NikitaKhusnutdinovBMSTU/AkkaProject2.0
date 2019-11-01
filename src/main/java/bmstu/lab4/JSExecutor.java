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
            Pair<Integer, PackageDecoded> receivedMSG = m.getMsg();
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            String jsScript = receivedMSG.getValue().getJSScript();
            String functionName = receivedMSG.getValue().getFunctionName();
            int[] params = receivedMSG.getValue().getTest(receivedMSG.getKey()).getParams();
            try{
                engine.eval(jsScript);
            } catch( ScriptException e){
                e.printStackTrace();
            }
            Invocable invocable = (Invocable) engine;
            String res = invocable.invokeFunction(functionName, params).toString();

            PackageDecoded packageDecoded = receivedMSG.getValue();
            packageDecoded.getTest(0).setResult(res);
            sender().tell(res, ActorRef.noSender());
        }).build();
    }
}
