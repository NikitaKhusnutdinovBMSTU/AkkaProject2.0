package bmstu.lab4;


public class StorageMessage {

    private final String result;
    private final String expectedResult;
    private String checker;
    private final Object[] param;
    private final String testName;

    public StorageMessage(String result, String expectedResult, String checker, Object[] param, String testName) {
        this.result = result;
        this.expectedResult = expectedResult;
        this.checker = checker;
        this.param = param;
        this.testName = testName;
    }


    public String getResult() {
        return result;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getChecker() {
        return checker;
    }

    public Object[] getParam() {
        return param;
    }

    public String getTestName() {
        return testName;
    }

}
