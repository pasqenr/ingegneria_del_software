package model;

import java.sql.ResultSet;

public class LeaveOrdersModel extends OrdersModel {
    public LeaveOrdersModel(ResultSet rs) {
        super(new String[] {
                "numero_bolla",
                "data_ordine",
                "codice_articolo",
                "nome",
                "prezzo"
        }, rs);
    }
}
