package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Methods {

    private java.sql.Connection db;

    public Methods() throws SQLException, ClassNotFoundException{
        Conn conn = new Conn();
        db = conn.Db();
    }

    public void Insert(Model model) throws SQLException{
        PreparedStatement stmt = db.prepareStatement("INSERT INTO fibonacci (value, result) values (?, ?);");
        stmt.setInt(1, model.getValue());
        stmt.setInt(2, model.getResult());
        stmt.executeUpdate();
    }
}
