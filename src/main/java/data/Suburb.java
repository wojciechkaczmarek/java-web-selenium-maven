package data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Suburb {
    @Getter @Setter
    private String suburbName;
    @Getter @Setter
    private String serviceCenter;

    @Override
    public String toString()
    {
        return "ClassPojo [suburbName = "+suburbName+", serviceCenter = "+serviceCenter+"]";
    }
}
