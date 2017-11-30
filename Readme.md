# BigTable

Passi preliminari:

1. Scaricare HBASE dal sito <a href="#">Apache Foundation</a>
2. Decomprimere il file ed entrare nella directory
3. Editare il file 'conf/hbase-site.xml' come segue:

    ```xml
   <?xml version="1.0"?>
   <?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
   <configuration>
     <property>
       <name>hbase.rootdir</name>
       <value>file:///DIRECTORY/hbase</value>
     </property>
     <property>
      <name>hbase.zookeeper.property.dataDir</name>
      <value>/DIRECTORY/testuser/zookeeper</value>
     </property>
   </configuration>
 

4. Settare il path corretto della JRE nel file 'conf/hbase-env.sh'
5. Avviare demone come segue:

     ```sh
     $ ./bin/start-hbase.sh
    
6. Avviare la shell come segue:

     ```sh
     $ ./bin/hbase shell

7. I comandi principali sono:

     ```sh
     > list
     > create 'TABELLA','FAMIGLIA1','FAMIGLIA2'
     > put 'TABELLA', 'RIGA', 'FAMIGLIA1:COLONNA1', 'CONTENUTO'
     > get 'TABELLA', 'RIGA'
     > disable 'TABELLA'
     > drop 'TABELLA'
     > delete 'TABELLA', 'RIGA', 'FAMIGLIA1:COLONNA1'
     
8. Creare una tabella vuota per l'applicazione nel seguente modo:

    ```sh
    > create 'table','family1'
    
9. Controllare che sia effettivamente sia stata creata con:

    ```sh
    > list
    
10. Uscire dalla shell come segue:

    ```sh
    > quit
    
11. Avviare il main del progetto
12. Stoppare il demone come segue:

     ```sh
     $ ./bin/stop-hbase.sh
    