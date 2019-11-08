package bmstu.lab4;

public class StorageMessage {

    private final String result;
    private final String expectedResult;
    private String checker;
    private final Object[] param;
    private final String testName;

    public StorageMessage(String result, String expectedResult, Object[] param, String testName){
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

    public String toString(){
        return "\n TO STRING ->" + result + "..." + expectedResult + "\n";
    }

    public String getResult(){
        return result;
    }

    public String getExpectedResult(){
        return expectedResult;
    }

    public String getChecker(){
        return checker;
    }

    public Object[] getParam() {
        return param;
    }

    public String getTestName() {
        return testName;
    }

}
