/*package database;

Resuna UV

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class Extractor {
    public static void main(String[] args) throws Exception {
        // database connection
        //Class driverClass = Class.forName("sqlite-jdbc");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:sqlite:" + System.getProperty("user.dir") + "/magazzino.sqlite",
                "sa",
                "");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("articolo");
        partialDataSet.addTable("evasione");
        partialDataSet.addTable("ingresso");
        partialDataSet.addTable("ingresso_articolo");
        partialDataSet.addTable("negozio");
        partialDataSet.addTable("ordine");
        partialDataSet.addTable("ordine_tipo_articolo");
        partialDataSet.addTable("posizione");
        partialDataSet.addTable("spedizioniere");
        partialDataSet.addTable("sport");
        partialDataSet.addTable("statistica");
        partialDataSet.addTable("tipo_articolo");
        partialDataSet.addTable("uscita");
        partialDataSet.addTable("uscita_articolo");
        partialDataSet.addTable("utente");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

        // full database export
        IDataSet fullDataSet = new QueryDataSet(connection);
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));

        // write DTD file
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("test.dtd"));

        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
        String[] depTableNames =
                TablesDependencyHelper.getAllDependentTables(connection, "articolo");
        IDataSet depDataset = connection.createDataSet(depTableNames);
        FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));
    }
}
*/