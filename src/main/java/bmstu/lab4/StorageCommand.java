package bmstu.lab4;


import javafx.util.Pair;

public class StorageCommand {
    private final int packageID;
    private final StorageMessage storageMessage;

    public StorageCommand(int idx, StorageMessage storageMsg){
        this.packageID = idx;
        this.storageMessage = storageMsg;
    }

    public int getPackageID(){
        return packageID;
    }

    public StorageMessage getStorageMessage(){
        return storageMessage;
    }

}
