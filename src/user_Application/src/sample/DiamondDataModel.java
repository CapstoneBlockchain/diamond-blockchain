package sample;

import javafx.beans.property.StringProperty;

public class DiamondDataModel {
    private StringProperty list;
    private StringProperty data;

    public DiamondDataModel(StringProperty list, StringProperty data){
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
