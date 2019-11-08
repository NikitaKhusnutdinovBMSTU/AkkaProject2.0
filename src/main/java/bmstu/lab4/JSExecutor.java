package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import javafx.util.Pair;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

// +
public class JSExecutor extends AbstractActor {
    private static final String JS_ENGINE = "nashorn";

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(ExecuteMSG.class, m -> {
            Pair<Integer, PackageDecoded> msg = m.getMsg();
            int index = msg.getKey();
            PackageDecoded packageDecoded = msg.getValue();
            TestDecoded test = packageDecoded.getTests()[index];
            ScriptEngine engine = new ScriptEngineManager().getEngineByName(JS_ENGINE);
            try{
                engine.eval(packageDecoded.getJSScript());
            } catch (ScriptException e){
                e.printStackTrace();
            }
            Invocable invocable = (Invocable) engine;
            String res = invocable.invokeFunction(packageDecoded.getFunctionName(), test.getParams()).toString();
            String check = "WRONG ANSWER!";

            if(res.equals(test.getExpectedResult())){
                check = "CORRECT ANSWER!";
            }

            StorageMessage storageMessage = new StorageMessage(
                    res,
                    test.getExpectedResult(),
                    check,
                    test.getParams(),
                    test.getTestName()
            );
            StorageCommand storageCommand = new StorageCommand(packageDecoded.getPackageId(), storageMessage);
            getSender().tell(storageCommand, ActorRef.noSender());
        }).build();
    }

}
