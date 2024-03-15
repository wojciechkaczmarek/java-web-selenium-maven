package data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@NoArgsConstructor
public class SuburbList {
    @Getter @Setter
    private Suburb[] suburbs;

    @Override
    public String toString()
    {
        return "ClassPojo [Suburb = "+ Arrays.toString(suburbs) +"]";
    }
}
