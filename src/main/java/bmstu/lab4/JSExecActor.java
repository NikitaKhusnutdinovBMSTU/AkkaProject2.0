package bmstu.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import javafx.util.Pair;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class JSExecActor extends AbstractActor {
    private static final String JS_ENGINE = "nashorn";
    private static final String WRONG_ANSWER = "WRONG ANSWER!";
    private static final String CORRECT_ANSWER = "CORRECT ANSWER!";

    @Override
    public Receive createReceive() {

        return ReceiveBuilder.create().match(ExecuteMSG.class, m -> {
            Pair<Integer, FunctionPackage> msg = m.getMsg();
            int index = msg.getKey();
            FunctionPackage functionPackage = msg.getValue();
            Test test = functionPackage.getTests()[index];
            ScriptEngine engine = new ScriptEngineManager().getEngineByName(JS_ENGINE);
            try{
                engine.eval(functionPackage.getJSScript());
            } catch (ScriptException e){
                e.printStackTrace();
            }
            Invocable invocable = (Invocable) engine;
            String res = invocable.invokeFunction(functionPackage.getFunctionName(), test.getParams()).toString();

            String check = WRONG_ANSWER;
            if(res.equals(test.getExpectedResult())){
                check = CORRECT_ANSWER;
            }

            StorageMessage storageMessage = new StorageMessage(
                    res,
                    test.getExpectedResult(),
                    check,
                    test.getParams(),
                    test.getTestName()
            );
            StorageCommand storageCommand = new StorageCommand(functionPackage.getPackageId(), storageMessage);
            getSender().tell(storageCommand, ActorRef.noSender());
        }).build();
    }

}
