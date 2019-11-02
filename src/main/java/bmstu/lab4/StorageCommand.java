package bmstu.lab4;


import javafx.util.Pair;

public class StorageCommand {
    private Pair<Integer, StorageMessage> storageMessagePair;

    public StorageCommand(int idx, StorageMessage storageMsg){
        storageMessagePair = new Pair<>(idx, storageMsg);
    }

    public int getPackageID(){
        return storageMessagePair.getKey();
    }

    public StorageMessage getStorageMessage(){
        return storageMessagePair.getValue();
    }

}
