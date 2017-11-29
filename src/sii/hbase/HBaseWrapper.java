package sii.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

import java.io.IOException;

public class HBaseWrapper {

    // TODO verificare Zookeeper poichè non funziona
    public HTable getTable(Configuration config, String name){
        HTable table = null;
        try  {
            table = new HTable(config, name);
        } catch (IOException e){
            System.out.println("Errore nella creazione della TABLE: " + e.getMessage());
        }
        return table;
    }

    public Boolean addRecord(HTable table, String rowKey, String family, String qualifier, String value){
        byte[] row = rowKey.getBytes();
        Put put = new Put(row);

        try {
            put.add(family.getBytes(),qualifier.getBytes(),value.getBytes());
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
