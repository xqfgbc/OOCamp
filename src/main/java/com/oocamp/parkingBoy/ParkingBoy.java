package com.oocamp.parkingBoy;

import com.oocamp.Car.Car;
import com.oocamp.parking.ParkingInfo;
import com.oocamp.parking.ParkingLot;
import com.oocamp.parking.ParkingLotInfo;
import com.oocamp.parking.ParkingPosition;

import java.util.List;

public class ParkingBoy {
    private final ParkingLot parkinglot;

    public ParkingBoy(ParkingLot park) {
        this.parkinglot = park;
    }

    public ParkingInfo parking(Car car) {
        List<ParkingLotInfo> parkingPlaces = parkinglot.getPlace();

        if (parkinglot.isFull()){
            return new ParkingInfo(){
                {
                    setStatus(0);
                    setParkingBoyParking(true);
                }
            };
        }

        final ParkingPosition parkingPosition = findEmptyPlace(parkingPlaces);

        if (parkingPosition.getParkingLotNum() == -1 || parkingPosition.getParkingNumber() == -1){
            return new ParkingInfo();
        }

        ParkingLotInfo places = parkingPlaces.get(parkingPosition.getParkingLotNum());
        places.getParkingPlace()[parkingPosition.getParkingNumber()] = car.getCarNumber();

        return new ParkingInfo(){
            {
                setStatus(1);
                setParkingLot(places.getParkingLotNumber());
                setParkingBoyParking(true);
            }
        };
    }

    protected ParkingPosition findEmptyPlace(List<ParkingLotInfo> parkingPlaces) {
        ParkingPosition position = new ParkingPosition();
        position.setParkingLotNum(-1);
        position.setParkingNumber(-1);

        for (int i = 0; i < parkingPlaces.size(); i++){
            ParkingLotInfo item = parkingPlaces.get(i);

            for (int j = 0; j < item.getParkingPlace().length; j++) {
                if (item.getParkingPlace()[j] == "" || item.getParkingPlace()[j] == null){
                    position.setParkingLotNum(i);
                    position.setParkingNumber(j);

                    break;
                }
            }

            if (position.getParkingLotNum() != -1 && position.getParkingNumber() != -1){
                break;
            }
        }

        return position;
    }

    public Car picking(String carNumber) {
        List<ParkingLotInfo> parkingPlaces = parkinglot.getPlace();
        ParkingPosition parkingPosition = findParkingCar(parkingPlaces, carNumber);

        if (parkingPosition.getParkingLotNum() == -1 || parkingPosition.getParkingNumber() == -1){
            throw new IllegalArgumentException("can not found carnumber");
        }

        ParkingLotInfo carParkingLot = parkingPlaces.get(parkingPosition.getParkingLotNum());
        carParkingLot.getParkingPlace()[parkingPosition.getParkingNumber()] = "";

        return new Car(carNumber);
    }

    protected ParkingPosition findParkingCar(List<ParkingLotInfo> parkingPlaces, String carNumber) {
        ParkingPosition position = new ParkingPosition();
        position.setParkingLotNum(-1);
        position.setParkingNumber(-1);

        for (int i = 0; i < parkingPlaces.size(); i++){
            ParkingLotInfo item = parkingPlaces.get(i);

            for (int j = 0; j < item.getParkingPlace().length; j++) {
                if (item.getParkingPlace()[j].equals(carNumber)){
                    position.setParkingLotNum(i);
                    position.setParkingNumber(j);

                    break;
                }
            }

            if (position.getParkingLotNum() != -1 && position.getParkingNumber() != -1){
                break;
            }
        }

        return position;
    }
}
