package sii.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.KeyValue;

import java.io.IOException;

public class HBaseWrapper {

    public HTable getTable(String name){
        Configuration config = HBaseConfiguration.create();
        HTable table = null;

        try  {
            table = new HTable(config, name);
        } catch (IOException e){
            System.out.println("Errore nella ricerca della Tabella: " + e.getMessage());
        }
        return table;
    }

    public Boolean addRecord(HTable table, String rowKey, String family, String qualifier, String value){
        byte[] row = rowKey.getBytes();
        Put put = new Put(row);

        try {
            put.add(family.getBytes(),qualifier.getBytes(),value.getBytes());
        } catch (Exception e){
            System.out.println("Errore nella creazione dei dati: " + e.getMessage());
            return false;
        }
        try {
            table.put(put);
        } catch (Exception e){
            System.out.println("Errore nell'inserimento dati nella Tabella: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean deleteRecord(HTable table, String rowKey){
        byte[] row = rowKey.getBytes();
        Delete delete = new Delete(row);

        try {
            table.delete(delete);
        } catch (IOException e) {
            System.out.println("Errore nell'eliminazione del record dalla Tabella: " + e.getMessage());
            return false;
        }
        return true;
    }

    public RowBean getOneRecord(HTable table, String rowKey){
        byte[] row = rowKey.getBytes();
        Get get = new Get(row);

        Result result = null;
        try {
            result = table.get(get);
        } catch (IOException e) {
            System.out.println("Errore nella ricerca del record nella Tabella: " + e.getMessage());
            return null;
        }

        String tableName = new String(table.getTableName());

        RowBean rowBean = new RowBean(rowKey, tableName);
        for(KeyValue kv : result.raw()) {
            rowBean.addRowContent(new String(kv.getFamily()),
                                    new String(kv.getQualifier()),
                                    new String(kv.getValue()));
        }
        return rowBean;
    }

    public void closeTable(HTable table){
        try{
            table.close();
        } catch (IOException e){
            System.out.println("Errore nella chiusura dalla Tabella: " + e.getMessage());
        }
    }
}
