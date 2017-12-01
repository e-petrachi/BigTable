package sii.hbase;

import java.io.IOException;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;

public class HBaseConnector {

    private static String TABLENAME = "table";
    private static String FAMILYNAME = "family";
    private static String ROWNAME = "row";
    private static String COLNAME = "column";

    public static void main(String[] argv){
        HBaseWrapper wrapper = new HBaseWrapper();
        HTable table = wrapper.getTable(TABLENAME);

        // INSERIMENTO DATI CASUALI

        System.out.println("--------------------------------------------------");
        for (int i=1; i<11; i++){
            for (int j=1; j<4; j++) {
                wrapper.addRecord(table, ROWNAME + i, FAMILYNAME + "1", COLNAME + j, " " + (Math.random()*10000));
            }
        }
        System.out.println("Inserimenti Effettuati con Successo");
        System.out.println("--------------------------------------------------");

        printTable(table);

        RowBean rb = wrapper.getOneRecord(table, ROWNAME + 2);
        System.out.println( rb.toString() );
        wrapper.deleteRecord(table, ROWNAME + 2);

        printTable(table);

        wrapper.closeTable(table);
    }

    public static void printTable(HTable table){
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("ROW  \tFAMILY\tQUALIFIER \t VALUE");
        System.out.println("--------------------------------------------------");

        for (int i=1; i<11; i++) {
            String title = new String(ROWNAME + i);

            Get get = new Get(title.getBytes());
            Result rs = null;

            try{
                rs = table.get(get);
            } catch (IOException e){
                System.out.println("Errore nella ricerca della Tabella: " + e.getMessage());
            }

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
        System.out.println("--------------------------------------------------");
        System.out.println();
    }
}
