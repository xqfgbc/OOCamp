package com.oocamp.parking;

import com.oocamp.Car.Car;

import java.lang.reflect.Array;
import java.util.*;

public class ParkingLot {

    private List<ParkingLotInfo> park;

    public ParkingLot(int parkingLotCount, int parkingPlaceCount) {
        park = new ArrayList<ParkingLotInfo>();
        for (int i = 0; i < parkingLotCount; i++) {
            ParkingLotInfo parkingPlace = new ParkingLotInfo();
            parkingPlace.setParkingLotNumber(i);
            parkingPlace.setParkingPlace(new String[parkingPlaceCount]);

            for (int j = 0; j < parkingPlaceCount; j++) {
                parkingPlace.getParkingPlace()[j] = "";
            }
            park.add(parkingPlace);
        }
    }

    public ParkingLot(int parkingLotCount, int... parkingPlaceCount) {
        park = new ArrayList<ParkingLotInfo>();
        for (int i = 0; i < parkingLotCount; i++) {
            ParkingLotInfo parkingPlace = new ParkingLotInfo();
            parkingPlace.setParkingLotNumber(i);
            parkingPlace.setParkingPlace(new String[parkingPlaceCount[i]]);

            for (int j = 0; j < parkingPlaceCount[i]; j++) {
                parkingPlace.getParkingPlace()[j] = "";
            }
            park.add(parkingPlace);
        }
    }

    public List<ParkingLotInfo> getPlace() {
        return park;
    }

    public boolean isFull(){
        return !park.stream().flatMap(item -> Arrays.stream(item.getParkingPlace())).anyMatch(item -> item.equals(""));
    }
}
