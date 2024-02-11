package DAO;

import DTO.WifiSpot;
import com.example.seoulwifiexplorer.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WifiSpotDAO {

    public WifiSpot getWifiSpotByManagementNumber(String managementNumber) {
        WifiSpot wifiSpot = null;
        String sql = "SELECT * FROM WifiSpot WHERE management_number = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, managementNumber);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    wifiSpot = new WifiSpot(
                            0.0000,
                            rs.getString("management_number"),
                            rs.getString("ward_office"),
                            rs.getString("wifi_name"),
                            rs.getString("address_street"),
                            rs.getString("address_detail"),
                            rs.getString("installation_floor"),
                            rs.getString("installation_type"),
                            rs.getString("installation_agency"),
                            rs.getString("service_type"),
                            rs.getString("network_type"),
                            rs.getInt("construction_year"),
                            rs.getString("indoor_outdoor"),
                            rs.getString("remarks"),
                            rs.getDouble("latitude"),
                            rs.getDouble("longitude"),
                            rs.getString("work_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wifiSpot;
    }

}
