package com.oocamp.SuperBoyTest;

import com.oocamp.Car.Car;
import com.oocamp.SuperParkingBoy.SuperParkingBoy;
import com.oocamp.parking.ParkingInfo;
import com.oocamp.parking.ParkingLot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SuperBoyTest {
    private ParkingLot parkingLot;
    private SuperParkingBoy superParkingBoy;

    @Before
    public void init() {
        parkingLot = new ParkingLot(2, new int[] {8,5});
        superParkingBoy = new SuperParkingBoy(parkingLot);
    }

    @Test
    public void should_given_two_parkinglot_and_first_parkinglot_empty_rate_is_less_than_second_when_superParkingBoy_parking_car_return_parking_car_in_second_parkinglot(){
        //given
        ParkingInfo info = superParkingBoy.parking(new Car("123"));

        //when
        ParkingInfo info2 = superParkingBoy.parking(new Car("456"));

        //then
        Assert.assertEquals(info2.getParkingLot(),1);
    }

    @Test
    public void should_given_two_parkinglot_and_each_parkinglot_is_full_when_superParkingBoy_parking_car_return_parking_car_fail(){
        //given
        Car[] cars = new Car[13];
        for (int i = 0; i < 8; i++) {
            parkingLot.getPlace().get(0).getParkingPlace()[i] = "京NXfd" + i;
        }

        for (int j = 0; j < 5; j++) {
            parkingLot.getPlace().get(1).getParkingPlace()[j] = "京NXfd" + j;
        }

        //when
        ParkingInfo info = superParkingBoy.parking(new Car("fda"));

        //then
        Assert.assertEquals(info.getStatus(), 0);
    }

    @Test
    public void should_given_two_parkinglot_and_each_parkinglot_has_0_parkingplace_when_superParkingBoy_parking_car_return_parking_car_fail(){
        //given
        ParkingLot parkingLot1 = new ParkingLot(2,0);
        SuperParkingBoy superParkingBoy1 = new SuperParkingBoy(parkingLot1);

        //when
        ParkingInfo info = superParkingBoy1.parking(new Car("fa"));

        //then
        Assert.assertEquals(info.getStatus(), 0);
    }

    @Test
    public void should_given_0_parkinglot_and_each_parkinglot_has_0_parkingplace_when_superParkingBoy_parking_car_return_parking_car_fail(){
        //given
        ParkingLot parkingLot1 = new ParkingLot(0,0);
        SuperParkingBoy superParkingBoy1 = new SuperParkingBoy(parkingLot1);

        //when
        ParkingInfo info = superParkingBoy1.parking(new Car("fa"));

        //then
        Assert.assertEquals(info.getStatus(), 0);
    }

    @Test
    public void should_given_two_parkinglot_and_superParkingBoy_parking_car_when_superParkingBoy_picking_car_return_car(){
        //given
        superParkingBoy.parking(new Car("123"));

        //when
        Car car = superParkingBoy.picking("123");

        //then
        Assert.assertEquals(car.getCarNumber(), "123");
    }

    @Test
    public void should_given_two_parkinglot_and_superParkingBoy_parking_two_car_when_superParkingBoy_picking_car_that_not_in_parkinglot_return_picking_car_fail(){
        //given
        superParkingBoy.parking(new Car("123"));
        superParkingBoy.parking(new Car("gbc"));

        //when
        Car car = superParkingBoy.picking("456");

        //then
        Assert.assertEquals(car.getCarNumber(), "");
    }
}
