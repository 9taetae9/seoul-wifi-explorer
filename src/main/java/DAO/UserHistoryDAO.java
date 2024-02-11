package DAO;

import DTO.UserHistory;
import com.example.seoulwifiexplorer.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHistoryDAO {
    public void saveUserLocation(UserHistory userHistory) {
        String sql = "INSERT INTO UserLocationHistory (latitude, longitude, query_date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, userHistory.getLatitude());
            pstmt.setDouble(2, userHistory.getLongitude());
            pstmt.setString(3, userHistory.getQueryDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserHistory> getUserLocationHistory() {
        String sql = "SELECT * FROM UserLocationHistory";
        List<UserHistory> historyList = new ArrayList<>();

        try(Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()){
                UserHistory userHistory = new UserHistory(
                        rs.getInt("id"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("query_date")
                );
                historyList.add(userHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }


    public void deleteUserHistory(int id) {
        String sql = "DELETE FROM UserLocationHistory WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
