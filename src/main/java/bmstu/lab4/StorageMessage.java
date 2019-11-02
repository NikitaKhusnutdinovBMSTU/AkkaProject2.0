package bmstu.lab4;

public class StorageMessage {

    private final String result;
    private final String expectedResult;
    private String checker;
    private final Object[] param;
    private final String testName;

    public StorageMessage(String result, String expectedResult, Object[] param, String testName, int indexId){
        this.result = result;
        this.expectedResult = expectedResult;
        this.param = param;
        this.testName = testName;
        checkResults();
    }

    private void checkResults(){
        if(!(result.toLowerCase().equals(expectedResult.toLowerCase()))){
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
