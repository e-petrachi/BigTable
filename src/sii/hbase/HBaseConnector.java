package sii.hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;

public class HBaseConnector {
    public static void main(String[] argv) throws IOException{
        HBaseWrapper wrapper = new HBaseWrapper();
        Configuration config = HBaseConfiguration.create();

        HTable table1 = wrapper.getTable(config, "dia");

        Boolean b = null;

        b = wrapper.addRecord(table1, "riga1", "informatica", "LM", "473004");

        if (b == true){
            System.out.println("|---> Inserimento effettuato!");
        }

        HTable table = null;

        try  {
            table = new HTable(config, "dia");
        } catch (Exception e){
            e.printStackTrace();
        }

        String title = new String("riga1");

        Get get = new Get(title.getBytes());
        Result rs = table.get(get);

        System.out.println("----------------------------------------------------------------------");
        System.out.println("ROW  \tFAMILY\tQUALIFIER \tVALUE");
        System.out.println("----------------------------------------------------------------------");

        for (KeyValue kv : rs.raw()){
            System.out.print(new String(kv.getRow()) + "\t");
            System.out.print(new String(kv.getFamily()) + "\t");
            String s = new String(kv.getQualifier());
            if(s.length()>6)
                System.out.print(s + "\t");
            else
                System.out.print(s + "\t\t");
            System.out.println(new String(kv.getValue()));
        }
    }
}
