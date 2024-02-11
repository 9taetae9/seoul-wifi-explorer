package com.example.seoulwifiexplorer;

import DTO.WifiSpot;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiDataFetcher {
    private static final String API_KEY = "51735952526b746838316a4266444c";
    private static final String API_BASE_URL = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/TbPublicWifiInfo/";
    private final OkHttpClient client = new OkHttpClient();
    private static final int PAGE_SIZE = 1000;

    public List<WifiSpot> fetchWifiSpots(int startIdx, int endIdx) throws IOException {
        List<WifiSpot> wifiSpots = new ArrayList<>();
        String url = API_BASE_URL + startIdx + "/" + endIdx;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonObject jsonObj = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonArray records = jsonObj.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
                records.forEach(record -> {
                    JsonObject rec = record.getAsJsonObject();
                    WifiSpot spot = new WifiSpot(
                            rec.get("X_SWIFI_MGR_NO").getAsString(),
                            rec.get("X_SWIFI_WRDOFC").getAsString(),
                            rec.get("X_SWIFI_MAIN_NM").getAsString(),
                            rec.get("X_SWIFI_ADRES1").getAsString(),
                            rec.get("X_SWIFI_ADRES2").getAsString(),
                            rec.get("X_SWIFI_INSTL_FLOOR").getAsString(),
                            rec.get("X_SWIFI_INSTL_TY").getAsString(),
                            rec.get("X_SWIFI_INSTL_MBY").getAsString(),
                            rec.get("X_SWIFI_SVC_SE").getAsString(),
                            rec.get("X_SWIFI_CMCWR").getAsString(),
                            rec.get("X_SWIFI_CNSTC_YEAR").getAsInt(),
                            rec.get("X_SWIFI_INOUT_DOOR").getAsString(),
                            rec.get("X_SWIFI_REMARS3").getAsString(),
                            rec.get("LAT").getAsDouble(),
                            rec.get("LNT").getAsDouble(),
                            rec.get("WORK_DTTM").getAsString()
                    );
                    wifiSpots.add(spot);
                });
            }
        }
        return wifiSpots;
    }


    public void insertWifiSpotsIntoDB(List<WifiSpot> wifiSpots) {
        String sql = "INSERT INTO WifiSpot (management_number, ward_office, wifi_name, address_street, address_detail, installation_floor, installation_type,installation_agency, service_type, network_type, construction_year, indoor_outdoor, remarks, latitude, longitude, work_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int cnt = 0;
            for (WifiSpot spot : wifiSpots) {
                pstmt.setString(1, spot.getManagementNumber());
                pstmt.setString(2, spot.getWardOffice());
                pstmt.setString(3, spot.getWifiName());
                pstmt.setString(4, spot.getAddressStreet());
                pstmt.setString(5, spot.getAddressDetail());
                pstmt.setString(6, spot.getInstallationFloor());
                pstmt.setString(7, spot.getInstallationType());
                pstmt.setString(8, spot.getInstallationAgency());
                pstmt.setString(9, spot.getServiceType());
                pstmt.setString(10, spot.getNetworkType());
                pstmt.setInt(11, spot.getConstructionYear());
                pstmt.setString(12, spot.getIndoorOutdoor());
                pstmt.setString(13, spot.getRemarks());
                pstmt.setDouble(14, spot.getLatitude());
                pstmt.setDouble(15, spot.getLongitude());
                pstmt.setString(16, spot.getWorkDate());
                pstmt.addBatch();

                if (++cnt % 1000 == 0 || cnt == wifiSpots.size()) {
                    pstmt.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalCount() throws IOException {
        String url = API_BASE_URL + "1/1";
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JsonObject jsonObj = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonObject wifiInfoObject = jsonObj.getAsJsonObject("TbPublicWifiInfo");
                return wifiInfoObject.get("list_total_count").getAsInt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int fetchAndStoreWifiSpots(int totalCount) throws IOException {
        List<WifiSpot> allNewWifiSpots = new ArrayList<>();
        int totalPages = (totalCount / PAGE_SIZE) + (totalCount % PAGE_SIZE > 0 ? 1 : 0);

        for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
            int startIdx = ((currentPage - 1) * PAGE_SIZE) + 1;
            int endIdx = currentPage * PAGE_SIZE;
            endIdx = Math.min(endIdx, totalCount);

            List<WifiSpot> wifiSpots = fetchWifiSpots(startIdx, endIdx);
            if (wifiSpots.isEmpty()) {
                return 0;
            }
            allNewWifiSpots.addAll(wifiSpots);
        }

        if (!allNewWifiSpots.isEmpty()) {
            clearExistingWifiSpots();
        }


        insertWifiSpotsIntoDB(allNewWifiSpots);

        return allNewWifiSpots.size();
    }

    public void clearExistingWifiSpots() {
        String sql = "DELETE FROM WifiSpot";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
