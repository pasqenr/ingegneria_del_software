package model;

import java.sql.ResultSet;

public class EntranceOrdersModel extends OrdersModel {

    public EntranceOrdersModel(ResultSet rs) {
        super(new String[]{
                "codice_ingresso",
                "data_ingresso",
                "codice_articolo",
                "nome",
                "prezzo",
                "posizione"
        }, rs);
    }
}
