package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import javafx.util.Pair;

//import javax.script.Invocable;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSExecutor extends AbstractActor {

    private int taskIdx;
    private PackageDecoded receivedPD;
    private String jsScript;
    private String functionName;
    private String res;



    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(ExecuteMSG.class, m -> {
            //initialisePair(m.getMsg());
            Pair<Integer, PackageDecoded> receivedMSG = m.getMsg();
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            String jsScript = receivedMSG.getValue().getJSScript();
            String functionName = receivedMSG.getValue().getFunctionName();
            Object[] params = receivedMSG.getValue().getTest(receivedMSG.getKey()).getParams();
            try{
                engine.eval(jsScript);
            } catch( ScriptException e){
                e.printStackTrace();
            }
            Invocable invocable = (Invocable) engine;

            System.out.println("functionName->" + functionName + "params->");
            String res = invocable.invokeFunction(functionName, 1,2).toString();
            PackageDecoded packageDecoded = receivedMSG.getValue();
            packageDecoded.wrightResult(receivedMSG.getKey(), res);
            getSender().tell(packageDecoded, ActorRef.noSender());
        }).build();
    }

    // ?! not working
    private void initialisePair(Pair<Integer, PackageDecoded> msg){
        receivedPD = msg.getValue();
        taskIdx = msg.getKey();
        jsScript = receivedPD.getJSScript();
        functionName = receivedPD.getFunctionName();
    }

    private void runScript(){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try{
            engine.eval(jsScript);
        } catch( ScriptException e){
            e.printStackTrace();
        }
        Invocable invocable = (Invocable) engine;
        Object params[] = receivedPD.getTest(taskIdx).getParams();
        res = invocable.invokeFunction(functionName).toString();
        receivedPD.wrightResult(taskIdx, res);
    }

}
