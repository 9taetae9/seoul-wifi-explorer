package com.example.seoulwifiexplorer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

@WebServlet(name = "WifiSpotServlet", urlPatterns = {"/wifispot"})
public class WifiSpotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        double userLat, userLng;
        try {
            userLat = Double.parseDouble(request.getParameter("lat"));
            userLng = Double.parseDouble(request.getParameter("lng"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다. 위도와 경도를 확인해주세요.");
            return;
        }


        //list to hold wifi spots
        List<WifiSpot> wifiSpotsList = new ArrayList<>();

        // Query the database for nearest wifi spots
        String query = "SELECT *, (6371 * acos(cos(radians(?)) * cos(radians(lat)) * cos(radians(lng) - radians(?)) + sin(radians(?)) * sin(radians(lat)))) AS distance_km FROM WifiSpot ORDER BY distance_km ASC LIMIT 20";

        // perform the SQL query using the DatabaseUtil class
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, userLat);
            pstmt.setDouble(2, userLng);
            pstmt.setDouble(3, userLat);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    WifiSpot spot = new WifiSpot(
                            rs.getInt("id"),
                            rs.getString("management_number"),
                            rs.getString("ward_office"),
                            rs.getString("wifi_name"),
                            rs.getString("address_street"),
                            rs.getString("address_detail"),
                            rs.getString("installation_floor"),
                            rs.getString("installation_type"),
                            rs.getString("service_type"),
                            rs.getString("network_type"),
                            rs.getInt("construction_year"),
                            rs.getString("indoor_outdoor"),
                            rs.getString("remarks"),
                            rs.getDouble("latitude"),
                            rs.getDouble("longitude"),
                            rs.getString("work_date"),
                            rs.getDouble("distance_km")
                    );
                    wifiSpotsList.add(spot);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 베이스 접근 중 오류 발생");
            return;
        }


        //Foward the results to the JSP page
        request.setAttribute("wifiSpots", wifiSpotsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("wifispot.jsp");
        dispatcher.forward(request, response);
    }
}
