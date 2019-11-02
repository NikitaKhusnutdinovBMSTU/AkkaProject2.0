package bmstu.lab4;

public class StorageMessage {

    private String result;
    private String expectedResult;
    //private int indexId;

    public StorageMessage(String result, String expectedResult, int indexId){
        this.result = result;
        this.expectedResult = expectedResult;
        //this.indexId = indexId;
    }
}
