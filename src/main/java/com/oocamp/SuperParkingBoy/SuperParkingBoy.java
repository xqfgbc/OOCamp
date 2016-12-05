package com.oocamp.SuperParkingBoy;

import com.oocamp.Car.Car;
import com.oocamp.parking.ParkingInfo;
import com.oocamp.parking.ParkingLot;
import com.oocamp.parking.ParkingLotInfo;
import com.oocamp.parking.ParkingPosition;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SuperParkingBoy {

    private ParkingLot parkingLot;

    public SuperParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }


    public ParkingInfo parking(Car car) {
        ParkingPosition position = findEmptyPlace((this.parkingLot.getPlace()));

        if (position.getParkingLotNum() == -1 || position.getParkingNumber() == -1) {
            return new ParkingInfo() {{
                setStatus(0);
                setParkingLot(-1);
                setParkingBoyParking(true);
            }};
        }

        this.parkingLot.getPlace().get(position.getParkingLotNum()).getParkingPlace()[position.getParkingNumber()] = car.getCarNumber();

        return new ParkingInfo() {{
            setStatus(1);
            setParkingLot(position.getParkingLotNum());
            setParkingBoyParking(true);
        }};
    }

    private ParkingPosition findEmptyPlace(List<ParkingLotInfo> place) {
        ParkingPosition position = new ParkingPosition();
        position.setParkingLotNum(-1);
        position.setParkingNumber(-1);

        if (this.parkingLot.isFull()){
            return position;
        }

        if (place.size()>0 && place.stream().allMatch(item->item.getParkingPlace().length>0)){
            ParkingLotInfo maxRateParkinglot = place.stream()
                    .max((item1, item2) -> Long.compare(
                        Arrays.stream(item1.getParkingPlace()).filter(item->item.equals("")).count()/item1.getParkingPlace().length,
                        Arrays.stream(item2.getParkingPlace()).filter(item->item.equals("")).count()/item2.getParkingPlace().length)
                    ).get();

            position.setParkingLotNum(maxRateParkinglot.getParkingLotNumber());
            position.setParkingNumber(Arrays.asList(maxRateParkinglot.getParkingPlace()).indexOf(""));
        }

        return position;
    }

    public Car picking(String carNumber) {
        Predicate<String> filter = (item) -> item.equals(carNumber);

        boolean isExists = parkingLot.getPlace().stream()
                .flatMap(item -> Arrays.stream(item.getParkingPlace()))
                .anyMatch(filter);

        if (isExists){
            for (ParkingLotInfo item : parkingLot.getPlace()){
                for (int i=0; i < item.getParkingPlace().length; i++){
                    if (item.getParkingPlace()[i].equals(carNumber)){
                        item.getParkingPlace()[i] = "";

                        return new Car(carNumber);
                    }
                }
            }
        }

        return new Car("");
    }
}
