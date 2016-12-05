package com.oocamp.parking;

/**
 * Created by bgong on 16/12/1.
 */
public class ParkingPosition {

    private int parkingLotNum;
    private int parkingNumber;

    public ParkingPosition() {
    }

    public int getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public int getParkingLotNum() {
        return parkingLotNum;
    }

    public void setParkingLotNum(int parkingLotNum) {
        this.parkingLotNum = parkingLotNum;
    }
}
