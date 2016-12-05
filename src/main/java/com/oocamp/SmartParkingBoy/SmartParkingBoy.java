package com.oocamp.SmartParkingBoy;

import com.oocamp.Car.Car;
import com.oocamp.parking.ParkingInfo;
import com.oocamp.parking.ParkingLot;
import com.oocamp.parking.ParkingLotInfo;
import com.oocamp.parking.ParkingPosition;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SmartParkingBoy {
    private ParkingLot parkingLot;

    public SmartParkingBoy(ParkingLot parkingLot) {

        this.parkingLot = parkingLot;
    }

    public ParkingInfo Parking(Car parkingCar) {
        ParkingPosition position = findEmptyPlace(this.parkingLot.getPlace());

        if (position.getParkingLotNum() == -1 || position.getParkingNumber() == -1) {
            return new ParkingInfo() {{
                setStatus(0);
                setParkingLot(-1);
                setParkingBoyParking(true);
            }};
        }

        this.parkingLot.getPlace().get(position.getParkingLotNum()).getParkingPlace()[position.getParkingNumber()] = parkingCar.getCarNumber();

        return new ParkingInfo() {{
            setStatus(1);
            setParkingLot(position.getParkingLotNum());
            setParkingBoyParking(true);
        }};
    }

    protected ParkingPosition findEmptyPlace(List<ParkingLotInfo> parkingLotInfos) {
        ParkingPosition position = new ParkingPosition();
        position.setParkingLotNum(-1);
        position.setParkingNumber(-1);

        if (parkingLotInfos.size() > 0) {
            ParkingLotInfo parkingLotInfo = parkingLotInfos.stream().max((item1, item2) ->
                    Long.compare(
                            Arrays.stream(item1.getParkingPlace()).filter(subItem -> subItem.equals("")).count(),
                            Arrays.stream(item2.getParkingPlace()).filter(subItem -> subItem.equals("")).count()
                    )).get();

            position.setParkingLotNum(parkingLotInfo.getParkingLotNumber());
            position.setParkingNumber(Arrays.asList(parkingLotInfo.getParkingPlace()).indexOf(""));
        }

        return position;
    }

    public Car Picking(String carNumber) {
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
