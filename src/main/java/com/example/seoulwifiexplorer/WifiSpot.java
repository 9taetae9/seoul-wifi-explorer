package com.example.seoulwifiexplorer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WifiSpot {
    private int id;
    private String managementNumber;
    private String wardOffice;
    private String wifiName;
    private String addressStreet;
    private String addressDetail;
    private String installationFloor;
    private String installationType;
    private String serviceType;
    private String networkType;
    private int constructionYear;
    private String indoorOutdoor;
    private String remarks;
    private double latitude;
    private double longitude;
    private String workDate;
    private double distanceKm;
}
