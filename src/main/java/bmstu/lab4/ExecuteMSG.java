package bmstu.lab4;

import javafx.util.Pair;

public class ExecuteMSG {
    //TODO smth more comfortable
    private Pair<Integer, PackageDecoded> msg;


    public ExecuteMSG(int a, PackageDecoded packageDecoded){
        msg = new Pair<>(a, packageDecoded);
    }

    public Pair<Integer, PackageDecoded> getMsg(){
        return msg;
    }
}
