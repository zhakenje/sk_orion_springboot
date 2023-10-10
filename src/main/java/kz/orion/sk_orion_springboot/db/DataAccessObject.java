package kz.orion.sk_orion_springboot.db;

import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@NoArgsConstructor
public class DataAccessObject {

    private DBConnection database = new DBConnection();
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection connect;

    public InputStream getReport(String report_name, String column_name){
        InputStream input = null;
        String query = "SELECT " + column_name + " FROM reports WHERE report_name='" + report_name +"'";
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()){
                input = rs.getBinaryStream(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return input;
    }

}
