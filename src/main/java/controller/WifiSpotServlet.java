package controller;

import DTO.WifiSpot;
import com.example.seoulwifiexplorer.DatabaseUtil;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "WifiSpotServlet", urlPatterns = {"/wifiSpots"})
public class WifiSpotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        double userLat, userLng;
        try {
            userLat = Double.parseDouble(request.getParameter("lat"));
            userLng = Double.parseDouble(request.getParameter("lng"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다. 위도와 경도를 확인해주세요.");
            return;
        }

        // 가까운 와이파이 지점 정보를 담을 리스트
        List<WifiSpot> wifiSpotsList = new ArrayList<>();

        // 20개 이내의 가까운 와이파이 지점 정보 가져오기
        String query = "SELECT *, round(6371*acos(cos(radians(?))*cos(radians(latitude))*cos(radians(longitude) -radians(?)) + sin(radians(?))*sin(radians(latitude))), 4) AS distance FROM WifiSpot ORDER BY distance LIMIT 20";

        // DB에서 가까운 와이파이 지점 정보 가져오기
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, userLat);
            pstmt.setDouble(2, userLng);
            pstmt.setDouble(3, userLat);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    double distance = rs.getDouble("distance"); // 사용자와의 거리
                    WifiSpot spot = new WifiSpot(
                            distance,
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
                    wifiSpotsList.add(spot);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 베이스 접근 중 오류 발생");
            return;
        }

        if (!wifiSpotsList.isEmpty()) {
            //wifiSpotsList Gson -> JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(wifiSpotsList));
        } else {
            response.getWriter().write("[]");
        }
    }
}
