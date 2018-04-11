package sample;

import javafx.beans.property.StringProperty;

public class Model_DiamondData {
    private StringProperty list;
    private StringProperty data;

    public Model_DiamondData(StringProperty list, StringProperty data){
        this.list = list;
        this.data = data;
    }

    public StringProperty getList(){
        return list;
    }

    public StringProperty getData(){
        return data;
    }
}
