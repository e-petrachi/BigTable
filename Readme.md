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
       <value>file:///DIRECTORY_DECIDI_TU_DOVE/hbase</value>
     </property>
   </configuration>
 

4. Settare il path corretto della JRE nel file 'conf/hbase-env.sh'
5. Avviare demone come segue:

    `$ ./bin/start-hbase.sh`
    
6. Avviare la shell come segue:

    `$ ./bin/hbase shell`

7. I comandi principali sono:

     ```sh
     $ create 'TABELLA','FAMIGLIA1','FAMIGLIA2'
     $ put 'TABELLA', 'RIGA', 'FAMIGLIA1:COLONNA1', 'CONTENUTO'
     $ get 'TABELLA', 'RIGA'
     $ delete 'TABELLA', 'RIGA', 'FAMIGLIA1:COLONNA1', 'CONTENUTO'
     
     
    