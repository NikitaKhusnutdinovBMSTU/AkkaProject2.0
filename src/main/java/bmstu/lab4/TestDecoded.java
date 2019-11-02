package bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDecoded {
    private final String testName;
    private final String expectedResult;
    private final Object[] params;
    private String result;
    private String checker;

    @JsonCreator
    public TestDecoded(@JsonProperty("testName") String testname,
                       @JsonProperty("expectedResult") String expectedResult,
                       @JsonProperty("params") Object[] params){
        this.testName = testname;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = "NONE";
        this.checker = "NOT READY YET";
    }

    public Object[] getParams(){
        return params;
    }

    public void setResult(String res){
        this.result = res;
        this.checkResults();
    }

    public String getResult(){
        return result;
    }

    public String getTestName(){
        return testName;
    }

    public String getExpectedResult(){
        return expectedResult;
    }

    public String getChecker(){
        return checker;
    }

    private void checkResults(){
        if(result.toLowerCase().equals(expectedResult.toLowerCase())){
            this.checker = "CORRECT ANSWER!";
        }else{
            this.checker = "WRONG ANSWER!";
        }
    }

}
