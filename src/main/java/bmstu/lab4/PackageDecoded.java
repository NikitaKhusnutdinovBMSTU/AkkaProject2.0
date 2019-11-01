package bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageDecoded {
    private final int packageId;
    private final String jsScript;
    private final String functionName;
    private final TestDecoded[] tests;

    @JsonCreator
    public PackageDecoded(@JsonProperty("packageId") String packageId,
                          @JsonProperty("jsScript") String jsScript,
                          @JsonProperty("functionName") String functionName,
                          @JsonProperty("tests") TestDecoded[] tests){
        this.packageId = Integer.parseInt(packageId);
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getJSScript(){
        return jsScript;
    }

    public int getPackageId(){
        return packageId;
    }

    public String getFunctionName(){
        return functionName;
    }

    public TestDecoded[] getTests(){
        return tests;
    }

    public TestDecoded getTest(int i){
        return tests[i];
    }

    public void wrightResult(int i, String result){
        tests[i].setResult(result);
    }
}
