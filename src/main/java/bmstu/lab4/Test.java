package bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDecoded {
    private static final String TEST_NAME = "testName";
    private static final String EXPECTED_RESULT = "expectedResult";
    private static final String PARAMS = "params";
    private static final String EMPTY_RESULT = "NONE";
    private static final String EMPTY_CHECKER = "NOT READY YET";

    private final String testName;
    private final String expectedResult;
    private final Object[] params;
    private String result;
    private String checker;

    @JsonCreator
    public TestDecoded(@JsonProperty(TEST_NAME) String testname,
                       @JsonProperty(EXPECTED_RESULT) String expectedResult,
                       @JsonProperty(PARAMS) Object[] params) {
        this.testName = testname;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = EMPTY_RESULT;
        this.checker = EMPTY_CHECKER;
    }

    public Object[] getParams() {
        return params;
    }

    public String getResult() {
        return result;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getChecker() {
        return checker;
    }

}
