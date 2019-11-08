package bmstu.lab4;

import javafx.util.Pair;


public class ExecuteMSG {

    private Pair<Integer, FunctionPackage> msg;


    public ExecuteMSG(int a, FunctionPackage functionPackage) {
        this.msg = new Pair<>(a, functionPackage);
    }

    public Pair<Integer, FunctionPackage> getMsg() {
        return this.msg;
    }
}
