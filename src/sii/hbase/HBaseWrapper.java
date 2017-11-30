package sii.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.KeyValue;

import java.io.IOException;

public class HBaseWrapper {

    // TODO verificare perchè Zookeeper funziona lentamente

    public HTable getTable(Configuration config, String name){
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

    public Boolean deleteRecord(String tableName, String rowKey){
        // TODO implementare
        return false;
    }

    public RowBean getOneRecord(String tableName, String rowKey){
        // TODO implementare
        return null;
    }
}
