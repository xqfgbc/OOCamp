package com.oocamp.parking;

/**
 * Created by bgong on 16/11/22.
 */
public class ParkingInfo {
    private int status=-1;
    private int parkingLot=-1;
    private boolean isParkingBoyParking=false;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(int parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean isParkingBoyParking() {
        return isParkingBoyParking;
    }

    public void setParkingBoyParking(boolean parkingBoyParking) {
        isParkingBoyParking = parkingBoyParking;
    }
}
