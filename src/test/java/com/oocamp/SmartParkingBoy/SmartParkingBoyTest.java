package com.oocamp.SmartParkingBoy;

import com.oocamp.Car.Car;
import com.oocamp.parking.ParkingInfo;
import com.oocamp.parking.ParkingLot;
import com.oocamp.parking.ParkingLotInfo;
import com.oocamp.parkingBoy.ParkingBoy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.SampleModel;

public class SmartParkingBoyTest {
    private ParkingLot parkingLot;
    private SmartParkingBoy smartParkingBoy;

    @Before
    public void init() {
        parkingLot = new ParkingLot(2, 5);
        smartParkingBoy = new SmartParkingBoy(parkingLot);
    }

    @Test
    public void should_given_two_parkinglot_when_smartParkingBoy_parking_car_return_parking_car_in_first_parkinglot() {
        //given
        Car myCar = new Car("京NX52G0");

        //when
        ParkingInfo info = smartParkingBoy.Parking(myCar);

        //then
        Assert.assertEquals(info.getStatus(), 1);
        Assert.assertEquals(info.getParkingLot(), 0);
        Assert.assertTrue(info.isParkingBoyParking());
    }

    @Test
    public void should_given_two_parkinglot_and_first_parkinglot_empty_place_less_than_second_when_smartParkingBoy_parking_car_return_parking_car_in_second_parkinglot() {
        //given
        Car myCar = new Car("京NX52G0");
        ParkingLot parkingLot1 = new ParkingLot(2, 5);
        parkingLot1.getPlace().get(0).getParkingPlace()[0] = "123";
        SmartParkingBoy smartParkingBoy1 = new SmartParkingBoy(parkingLot1);

        //when
        ParkingInfo info = smartParkingBoy1.Parking(myCar);

        //then
        Assert.assertEquals(info.getStatus(), 1);
        Assert.assertEquals(info.getParkingLot(), 1);
        Assert.assertTrue(info.isParkingBoyParking());
    }

    @Test
    public void should_given_two_parkinglot_and_first_parkinglot_is_full_and_smartParkingBoy_parking_car_return_parking_car_in_second_parkinglot(){
        //given
        Car myCar = new Car("京NX52G0");
        Car[] otherCar = new Car[5];
        String[] carNumbers = new String[5];

        for (int i=0; i< otherCar.length; i++){
            otherCar[i] = new Car("京NX52G" +i);
        }

        for (int i = 0; i < carNumbers.length; i++) {
            carNumbers[i] = otherCar[i].getCarNumber();
        }

        parkingLot.getPlace().get(0).setParkingPlace(carNumbers);

        //when
        ParkingInfo info = smartParkingBoy.Parking(myCar);

        //then
        Assert.assertEquals(info.getParkingLot(), 1);
    }

    @Test
    public void should_given_two_parkinglot_and_second_parkinglot_is_full_and_smartParkingBoy_parking_car_return_parking_car_in_first_parkinglot(){
        //given
        Car myCar = new Car("京NX52G0");
        Car[] otherCar = new Car[5];
        String[] carNumbers = new String[5];

        for (int i=0; i< otherCar.length; i++){
            otherCar[i] = new Car("京NX52G" +i);
        }

        for (int i = 0; i < carNumbers.length; i++) {
            carNumbers[i] = otherCar[i].getCarNumber();
        }

        parkingLot.getPlace().get(1).setParkingPlace(carNumbers);

        //when
        ParkingInfo info = smartParkingBoy.Parking(myCar);

        //then
        Assert.assertEquals(info.getParkingLot(), 0);
    }

    @Test
    public void should_given_one_parkinglot_when_smartParkingBoy_parking_car_return_parking_car_in_first_parkinglot(){
        //given
        ParkingLot parkingLot1 = new ParkingLot(1,5);
        SmartParkingBoy smartParkingBoy1 = new SmartParkingBoy(parkingLot1);
        Car myCar = new Car("京NX52G0");

        //when
        ParkingInfo info = smartParkingBoy1.Parking(myCar);

        //then
        Assert.assertEquals(info.getStatus(),1);
        Assert.assertEquals(info.getParkingLot(),0);
    }

    @Test
    public void should_given_no_parkinglot_when_smartParkingBoy_parking_car_return_parking_car_fail(){
        //given
        ParkingLot parkingLot1 = new ParkingLot(0,5);
        SmartParkingBoy smartParkingBoy1 = new SmartParkingBoy(parkingLot1);
        Car myCar = new Car("京NX52G0");

        //when
        ParkingInfo info = smartParkingBoy1.Parking(myCar);

        //then
        Assert.assertEquals(info.getStatus(),0);
        Assert.assertEquals(info.getParkingLot(),-1);
    }

    @Test
    public void should_given_two_parkinglot_and_smartParkingBoy_parking_car_and_smartParkingBoy_picking_car_return_my_car(){
        //given
        Car myCar = new Car("京NX52G0");
        ParkingInfo parkingInfo = smartParkingBoy.Parking(myCar);

        //when
        Car pickingCar = smartParkingBoy.Picking(myCar.getCarNumber());

        //then
        Assert.assertEquals(parkingInfo.getStatus(),1);
        Assert.assertEquals(pickingCar.getCarNumber(), myCar.getCarNumber());
    }

    @Test
    public void should_given_two_parkinglot_and_first_parkinglot_empty_place_less_than_second_and_smartParkingBoy_parking_car_and_smartParkingBoy_picking_car_return_my_car(){
        //given
        Car myCar = new Car("京NX52G0");
        ParkingInfo info1 = smartParkingBoy.Parking(new Car("fda"));
        ParkingInfo info2 = smartParkingBoy.Parking(myCar);

        //when
        Car pickingCar = smartParkingBoy.Picking("京NX52G0");

        //then
        Assert.assertEquals(info1.getParkingLot(),0);
        Assert.assertEquals(info2.getParkingLot(),1);
        Assert.assertEquals(pickingCar.getCarNumber(), myCar.getCarNumber());
    }

    @Test
    public void should_given_smartParkingBoy_parking_car_when_smartParkingBoy_picking_car_without_in_parkinglot_return_picking_car_fail(){
        //given
        smartParkingBoy.Parking(new Car("123"));

        //when
        Car pickingCar = smartParkingBoy.Picking("fda");

        //then
        Assert.assertEquals(pickingCar.getCarNumber(), "");
    }

    @Test
    public void should_given_two_parkinglot_and_smartParkingBoy_parking_car_and_smartParkingBoy_parking_second_car_and_smartParkingBoy_picking_second_car_when_smartParkingBoy_parking_third_car_return_parking_car_in_second_parkinglot(){
        //given
        smartParkingBoy.Parking(new Car("123"));
        smartParkingBoy.Parking(new Car("456"));
        smartParkingBoy.Picking("456");

        //when
        ParkingInfo info = smartParkingBoy.Parking(new Car("asd"));

        //then
        Assert.assertEquals(info.getParkingLot(),1);
    }
}
