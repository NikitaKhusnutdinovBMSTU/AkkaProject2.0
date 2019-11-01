package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import javafx.util.Pair;

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
            try{
                engine.eval(jsScript);
            } catch( ScriptException e){
                e.printStackTrace();
            }
        }).build();
    }
}
