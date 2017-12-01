package sii.hbase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RowBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private String tableName;
    private String rowKey;
    private Map<String, String> rowContent;

    public RowBean(){
        super();
    }

    public RowBean(String rowKey, String tableName, Map<String, String> rowContent) {
        super();
        this.rowKey = rowKey;
        this.tableName = tableName;
        this.rowContent = rowContent;
    }

    public RowBean(String rowKey, String tableName) {
        super();
        this.rowKey = rowKey;
        this.tableName = tableName;
        this.rowContent = new HashMap<String, String>();
    }

    public void addRowContent(String family, String qualifier, String value){
        String key = family + ":" + qualifier;

        this.rowContent.put(key, value);
    }

    @Override
    public String toString() {
        String s = "";
        s = s + "RowBean [ \n\t TableName= " + tableName + "\n\t RowKey= " + rowKey + "\n\t RowContent= {";
        for (String row: rowContent.keySet()) {
            s = s + "\n\t\t" + row + " \t" + rowContent.get(row);
        }
        s = s + "\n\t }\n ]";

        return s;
    }

    public String getTable() {
        return tableName;
    }

    public void setTable(String tableName) {
        this.tableName = tableName;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public Map<String, String> getRowContent() {
        return rowContent;
    }

    public void setRowContent(Map<String, String> rowContent) {
        this.rowContent = rowContent;
    }

}
