package sii.hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;

public class HBaseConnector {

    private static String TABLENAME = "table";
    private static String FAMILYNAME = "family";
    private static String ROWNAME = "row";
    private static String COLNAME = "column";

    public static void main(String[] argv) throws IOException{
        HBaseWrapper wrapper = new HBaseWrapper();
        Configuration config = HBaseConfiguration.create();

        HTable table = wrapper.getTable(config, TABLENAME);

        // INSERIMENTO DATI CASUALI

        for (int i=1; i<11; i++){
            for (int j=1; j<4; j++) {
                Boolean b = null;

                b = wrapper.addRecord(table, ROWNAME + i, FAMILYNAME + "1", COLNAME + j, " " + (Math.random()*10000));

                if (b == true) {
                    System.out.println("|-----------------> Inserimento effettuato!");
                }
            }
        }

        // LETTURA INTERA TABELLA

        System.out.println("----------------------------------------------------------------------");
        System.out.println("ROW  \tFAMILY\tQUALIFIER \t VALUE");
        System.out.println("----------------------------------------------------------------------");

        for (int i=1; i<11; i++) {
            String title = new String(ROWNAME + i);

            Get get = new Get(title.getBytes());
            Result rs = table.get(get);

            for (KeyValue kv : rs.raw()) {
                System.out.print(new String(kv.getRow()) + "\t");
                System.out.print(new String(kv.getFamily()) + "\t");
                String s = new String(kv.getQualifier());
                if (s.length() > 9)
                    System.out.print(s + "\t");
                else
                    System.out.print(s + "\t\t");
                System.out.println(new String(kv.getValue()));
            }
        }
    }
}
