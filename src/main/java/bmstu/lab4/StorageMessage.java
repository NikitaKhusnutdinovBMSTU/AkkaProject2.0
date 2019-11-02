package bmstu.lab4;

public class StorageMessage {

    private String result;
    private String expectedResult;
    private String checker;
    //private int indexId;

    public StorageMessage(String result, String expectedResult, int indexId){
        this.result = result;
        checkResults();
    }

    private void checkResults(){
        if(result.toLowerCase().equals(expectedResult.toLowerCase())){
            this.checker = "CORRECT ANSWER!";
        }else{
            this.checker = "WRONG ANSWER!";
        }
    }

    private String getResult(){
        return result;
    }

    private String getExpectedResult(){
        return expectedResult;
    }

    private String getChecker(){
        return checker;
    }
}
