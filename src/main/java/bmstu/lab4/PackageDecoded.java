package bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//++
public class PackageDecoded {
    private final static String PACKAGE_ID = "packageId";
    private final static String JS_SCRIPT = "jsScript";
    private final static String FUNCTION_NAME = "functionName";
    private final static String TESTS = "tests";

    private final int packageId;
    private final String jsScript;
    private final String functionName;
    private final TestDecoded[] tests;

    @JsonCreator
    public PackageDecoded(@JsonProperty(PACKAGE_ID) String packageId,
                          @JsonProperty(JS_SCRIPT) String jsScript,
                          @JsonProperty(FUNCTION_NAME) String functionName,
                          @JsonProperty(TESTS) TestDecoded[] tests) {
        this.packageId = Integer.parseInt(packageId);
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getJSScript() {
        return jsScript;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public TestDecoded[] getTests() {
        return tests;
    }

    public TestDecoded getTest(int i) {
        return tests[i];
    }
}
