package bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDecoded {
    private final String testName;
    private final float expectedResult;
    private final int[] params;
    private String result;

    @JsonCreator
    public TestDecoded(@JsonProperty("testName") String testname,
                       @JsonProperty("expectedResult") String expectedResult,
                       @JsonProperty("params") int[] params){
        this.testName = testname;
        this.expectedResult = Float.parseFloat(expectedResult);
        this.params = params;
        this.result = "NONE";
    }

    public int[] getParams(){
        return params;
    }

    public void setResult(String res){
        this.result = res;
    }

}
