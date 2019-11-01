package bmstu.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageDecoded {
    private final int packageId;
    private final String jsScript;
    private final String functionName;
    private final TestsDecoded[] tests;

    @JsonCreator
    public PackageDecoded(@JsonProperty("packageId") String packageId,
                          @JsonProperty("jsSript") String jsScript,
                          @JsonProperty("functionName") String functionName,
                          @JsonProperty("tests") TestsDecoded[] tests){
        this.packageId = Integer.parseInt(packageId);
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public int getPackageId(){
        return packageId;
    }

    public String getFunctionName(){
        return functionName;
    }
}
