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

    private int taskIdx;
    private PackageDecoded receivedPD;
    private String jsScript;



    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(ExecuteMSG.class, m -> {
//            initialisePair(m.getMsg());
//            Pair<Integer, PackageDecoded> receivedMSG = m.getMsg();
//            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//            String jsScript = receivedMSG.getValue().getJSScript();
//            String functionName = receivedMSG.getValue().getFunctionName();
//            Object[] params = receivedMSG.getValue().getTest(receivedMSG.getKey()).getParams();
//            try{
//                engine.eval(jsScript);
//            } catch( ScriptException e){
//                e.printStackTrace();
//            }
//            Invocable invocable = (Invocable) engine;
//
//            System.out.println("functionName->" + functionName + "params->");
//            String res = invocable.invokeFunction(functionName, params).toString();
//            PackageDecoded packageDecoded = receivedMSG.getValue();
//            packageDecoded.wrightResult(receivedMSG.getKey(), res);
            getSender().tell(packageDecoded, ActorRef.noSender());
        }).build();
    }

    private void initialisePair(Pair<Integer, PackageDecoded> msg){

    }

    private void runScript(){

    }

}
