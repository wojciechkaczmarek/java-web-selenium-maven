package utilities;

import com.google.gson.Gson;
import data.SuburbList;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DataProviderNSW {
    private static final Gson gson = new Gson();

    public static Object[][] loadSuburbsData() {
        String pathOfData = System.getProperty("user.dir")+"/src/main/resource/DataFiles/Suburb.json";
        try (Reader reader = new FileReader(pathOfData)) {
            SuburbList suburbList = gson.fromJson(reader, SuburbList.class);
            Object[][] data = new Object[suburbList.getSuburbs().length][2];
            for (int i = 0; i < suburbList.getSuburbs().length; i++) {
                data[i][0] = suburbList.getSuburbs()[i].getSuburbName();
                data[i][1] = suburbList.getSuburbs()[i].getServiceCenter();
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Object[0][0];
    }
}
