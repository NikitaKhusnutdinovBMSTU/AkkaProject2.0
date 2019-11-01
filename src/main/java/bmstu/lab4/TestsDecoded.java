package bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestsDecoded {
    private final String testName;
    private final float expectedResult;
    private final int[] params;

    @JsonCreator
    public TestsDecoded(@JsonProperty("testName") String testname,
                        @JsonProperty("expectedResult") String expectedResult,
                        @JsonProperty("params") int[] params){
        this.testName = testname;
        this.expectedResult = Float.parseFloat(expectedResult);
        this.params = params;
    }
}
