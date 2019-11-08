package bmstu.lab4;

import javafx.util.Pair;


//++
public class ExecuteMSG {

    private Pair<Integer, PackageDecoded> msg;


    public ExecuteMSG(int a, PackageDecoded packageDecoded) {
        this.msg = new Pair<>(a, packageDecoded);
    }

    public Pair<Integer, PackageDecoded> getMsg() {
        return this.msg;
    }
}
