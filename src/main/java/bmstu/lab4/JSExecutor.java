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
            for(int i = 0; i < params.length; i++){
                System.out.println(params[i]);
            }
            System.out.println("functionName->" + functionName + "params->");
            String res = invocable.invokeFunction(functionName, params).toString();
            PackageDecoded packageDecoded = receivedMSG.getValue();
            packageDecoded.wrightResult(receivedMSG.getKey(), res);
            getSender().tell(packageDecoded, ActorRef.noSender());
        }).build();
    }
}
