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
    private String jsScript, functionName;
    private Object[] params;
    private String res;


    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(ExecuteMSG.class, m -> {
            initialisePair(m.getMsg());
            runScript();
//            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//            try{
//                engine.eval(jsScript);
//            } catch( ScriptException e){
//                e.printStackTrace();
//            }
//            Invocable invocable = (Invocable) engine;
//
//            String res = invocable.invokeFunction(functionName, params).toString();
//            PackageDecoded packageDecoded = receivedMSG.getValue();
//            packageDecoded.wrightResult(receivedMSG.getKey(), res);
            getSender().tell(receivedPD, ActorRef.noSender());
        }).build();
    }

    private void initialisePair(Pair<Integer, PackageDecoded> msg){
        receivedPD = msg.getValue();
        taskIdx = msg.getKey();
        jsScript = receivedPD.getJSScript();
        functionName = receivedPD.getFunctionName();
        params = receivedPD.getTest(taskIdx).getParams();
    }

    private void runScript(){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try{
            engine.eval(jsScript);
            Invocable invocable = (Invocable) engine;
            res = invocable.invokeFunction(functionName, params).toString();
            receivedPD.wrightResult(taskIdx, res);
        } catch( ScriptException e){
            e.printStackTrace();
        }
    }

}
