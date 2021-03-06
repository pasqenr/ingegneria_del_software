package controller;

import database.DatabaseWrapper;
import model.OrdersModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manage the leaves table visualization.
 */
public class LeaveController {
    private final OrdersModel ordersModel;

    /**
     * Create a new LeaveController.
     */
    public LeaveController() {
        final DatabaseWrapper db = new DatabaseWrapper();

        final ResultSet rs = fetchLeaveOrders(db);
        ordersModel = new OrdersModel(new String[] {
                "numero_bolla",
                "data_ordine",
                "negozio",
                "codice_articoli",
                "nome",
                "quantita",
                "prezzo_totale"
        }, rs);

        db.close();
    }

    /**
     * Fetch from the database the orders that will populate the <code>ordersModel</code> table.
     *
     * @param db A <code>DatabaseWrapper</code> instance.
     * @return The <code>ResultSet</code> of the orders used to populate <code>ordersModel</code> table.
     */
    private ResultSet fetchLeaveOrders(final DatabaseWrapper db) {
        ResultSet rs = null;

        final String query =
                "SELECT u.numero_bolla, " +
                        "       u.data AS data_ordine, " +
                        "       n.nome AS negozio, " +
                        "       u.spedizioniere, " +
                        "       GROUP_CONCAT(a.codice) AS codice_articoli, " +
                        "       ota.nome_tipo_articolo AS nome, " +
                        "       ota.quantita, " +
                        "       ota.prezzo_totale " +
                        "FROM uscita u " +
                        "       JOIN negozio n on u.negozio = n.codice_fiscale " +
                        "       JOIN spedizioniere s on u.spedizioniere = s.nome " +
                        "       JOIN ordine o on n.codice_fiscale = o.negozio " +
                        "       JOIN ordine_tipo_articolo ota on o.codice = ota.codice_ordine " +
                        "       JOIN articolo a on ota.nome_tipo_articolo = a.tipo_articolo " +
                        "GROUP BY ota.nome_tipo_articolo " +
                        "ORDER BY u.numero_bolla";
        final PreparedStatement stmt;

        try {
            stmt = db.getCon().prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * @return A <code>OrdersModel</code> that can be used to generate a <code>TableModel</code>.
     */
    public OrdersModel getOrdersModel() {
        return ordersModel;
    }
}
