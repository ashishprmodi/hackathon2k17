package app.siemens.com.sos.model;

import java.util.StringTokenizer;

/**
 * Created by d3sbma on 6/13/2017.
 */

public class TaskModel {

    private final String alertMessage1 = "User :  ";
    private final String alertMessage2 = " met with an accident at  :  ";
    private final String mapUrl = "http://132.186.66.193/SOSCrashAlert/soscrashalert/accidentmapview.html?";
    private final String mapBasicUrl = "http://www.google.com/maps/place/";
    private final String cfUrl = "https://soscrashalert1.cfapps.io/soscrashalert/accidentmapview.html?";


//                    "username": "Karan Kapoor",
//                    "bloodGrp": "B+",
//                    "contactNumber": "917387460106",
//                    "emergencyContact1": "918007530200",
//                    "emergencyContact2": "917387460102",
//                    "emergencyContact3": "917387460112",
//                    "vehicleType": "2W",
//                    "insuranceNo": "102128129128121028",
//                    "alarmTaskId": 1,
//                    "taskType": "Hospital",
//                    "acknowledged": false,
//                    "status": "URGENT",
//                    "feedback": null,
//                    "alarmCreatedDate": 1497360755769,
//                    "GeoLocation": "18.53100857 73.84438992"



    //User name
    private String username;
    private String bloodGrp;
    private String contactNumber;
    private String emergencyContact1;
    private String emergencyContact2;
    private String emergencyContact3;
    private String vehicleType;
    private String insuranceNo;
    private int alarmTaskId;
    private String taskType;
    private String acknowledged;
    private String status;
    private String feedback;
    private String alarmCreatedDate;
    private String GeoLocation;

    public TaskModel(String username, String bloodGrp, String contactNumber, String emergencyContact1, String emergencyContact2, String emergencyContact3, String vehicleType, String insuranceNo, int alarmTaskId, String taskType, String acknowledged, String status, String feedback, String alarmCreatedDate, String geoLocation) {
        this.username = username;
        this.bloodGrp = bloodGrp;
        this.contactNumber = contactNumber;
        this.emergencyContact1 = emergencyContact1;
        this.emergencyContact2 = emergencyContact2;
        this.emergencyContact3 = emergencyContact3;
        this.vehicleType = vehicleType;
        this.insuranceNo = insuranceNo;
        this.alarmTaskId = alarmTaskId;
        this.taskType = taskType;
        this.acknowledged = acknowledged;
        this.status = status;
        this.feedback = feedback;
        this.alarmCreatedDate = alarmCreatedDate;
        GeoLocation = geoLocation;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "username='" + username + '\'' +
                ", bloodGrp='" + bloodGrp + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emergencyContact1='" + emergencyContact1 + '\'' +
                ", emergencyContact2='" + emergencyContact2 + '\'' +
                ", emergencyContact3='" + emergencyContact3 + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", insuranceNo='" + insuranceNo + '\'' +
                ", alarmTaskId=" + alarmTaskId +
                ", taskType='" + taskType + '\'' +
                ", acknowledged='" + acknowledged + '\'' +
                ", status='" + status + '\'' +
                ", feedback='" + feedback + '\'' +
                ", alarmCreatedDate='" + alarmCreatedDate + '\'' +
                ", GeoLocation='" + GeoLocation + '\'' +
                '}';
    }

    public String getUsername() {
        return alertMessage1 + " " + username + alertMessage2;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmergencyContact1() {
        return emergencyContact1;
    }

    public void setEmergencyContact1(String emergencyContact1) {
        this.emergencyContact1 = emergencyContact1;
    }

    public String getEmergencyContact2() {
        return emergencyContact2;
    }

    public void setEmergencyContact2(String emergencyContact2) {
        this.emergencyContact2 = emergencyContact2;
    }

    public String getEmergencyContact3() {
        return emergencyContact3;
    }

    public void setEmergencyContact3(String emergencyContact3) {
        this.emergencyContact3 = emergencyContact3;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public int getAlarmTaskId() {
        return alarmTaskId;
    }

    public void setAlarmTaskId(int alarmTaskId) {
        this.alarmTaskId = alarmTaskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(String acknowledged) {
        this.acknowledged = acknowledged;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getAlarmCreatedDate() {
        return alarmCreatedDate;
    }

    public void setAlarmCreatedDate(String alarmCreatedDate) {
        this.alarmCreatedDate = alarmCreatedDate;
    }

    public String getGeoLocation() {
        String latLong = this.GeoLocation;
        String [] latLongArray = latLong.split(" ");
        //http://132.186.66.193/SOSCrashAlert/soscrashalert/accidentmapview.html?lat=18.558007&long=73.807520
        //return mapUrl+"lat="+ latLongArray[0] + "&long=" + latLongArray[1];

        //direct url to google maps
        //return mapBasicUrl+latLongArray[0]+","+latLongArray[1];

        //Cf URL
        return cfUrl +"lat="+ latLongArray[0] + "&long=" + latLongArray[1];
    }

    public void setGeoLocation(String geoLocation) {
        GeoLocation = geoLocation;
    }
}
