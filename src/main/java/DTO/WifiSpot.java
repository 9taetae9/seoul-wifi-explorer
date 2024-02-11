package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WifiSpot {
    private double distanceKm;
    private String managementNumber;
    private String wardOffice;
    private String wifiName;
    private String addressStreet;
    private String addressDetail;
    private String installationFloor;
    private String installationType;
    private String installationAgency;
    private String serviceType;
    private String networkType;
    private int constructionYear;
    private String indoorOutdoor;
    private String remarks;
    private double latitude;
    private double longitude;
    private String workDate;

    public WifiSpot(String managementNumber, String wardOffice, String wifiName, String addressStreet, String addressDetail,
                    String installationFloor, String installationType, String installationAgency, String serviceType,
                    String networkType, int constructionYear, String indoorOutdoor, String remarks, double latitude,
                    double longitude, String workDate) {
        this.managementNumber = managementNumber;
        this.wardOffice = wardOffice;
        this.wifiName = wifiName;
        this.addressStreet = addressStreet;
        this.addressDetail = addressDetail;
        this.installationFloor = installationFloor;
        this.installationType = installationType;
        this.installationAgency = installationAgency;
        this.serviceType = serviceType;
        this.networkType = networkType;
        this.constructionYear = constructionYear;
        this.indoorOutdoor = indoorOutdoor;
        this.remarks = remarks;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workDate = workDate;
    }
}
