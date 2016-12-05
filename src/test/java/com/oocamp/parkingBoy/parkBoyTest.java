package com.oocamp.parkingBoy;

import com.oocamp.Car.Car;
import com.oocamp.parking.ParkingLot;
import com.oocamp.parking.ParkingInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class parkBoyTest {
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    @Before
    public void init(){
        parkingLot = new ParkingLot(2,5);
        parkingBoy = new ParkingBoy(parkingLot);
    }

    @Test
    public void should_given_two_parkinglot_first_parkinglot_has_place_when_parkingboy_parking_car_return_parking_car_in_first_parkinglot(){
        //given
        Car myCar = new Car("京NX52G0");

        //when
        ParkingInfo parkingInfo = parkingBoy.parking(myCar);

        //then
        Assert.assertEquals(parkingInfo.getStatus(), 1);
        Assert.assertEquals(parkingInfo.getParkingLot(), 0);
        Assert.assertTrue(parkingInfo.isParkingBoyParking());
    }

    @Test
    public void should_given_two_parkinglot_and_first_parkinglot_is_full_when_parkingboy_parking_car_return_parking_car_in_second_parkinglot(){
        //given
        Car myCar = new Car("京NX52G0");
        Car[] otherCar = new Car[6];

        for (int i = 0; i < otherCar.length; i++) {
            otherCar[i] = new Car("京NX52G" + (i+1));
        }

        for (int i = 0; i < otherCar.length; i++) {
            parkingBoy.parking(otherCar[i]);
        }

        //when
        ParkingInfo info = parkingBoy.parking(myCar);

        //then
        Assert.assertEquals(info.getStatus(), 1);
        Assert.assertEquals(info.getParkingLot(), 1);
        Assert.assertTrue(info.isParkingBoyParking());
    }

    @Test
    public void should_given_parkingboy_parking_car_when_parkingboy_picking_car_return_myCar(){
        //given
        Car myCar = new Car("京NX52G0");
        parkingBoy.parking(myCar);

        //when
        Car car = parkingBoy.picking("京NX52G0");

        //then
        Assert.assertNotNull(car);
        Assert.assertEquals(myCar.getCarNumber(), car.getCarNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_given_no_parking_car_when_parkingboy_picking_car_shrow_IllegalArgumentException(){
        //given
        Car myCar = new Car("京NX52G0");
        parkingBoy.parking(myCar);

        //when
        parkingBoy.picking("fda");
    }

    @Test
    public void should_given_two_parkinglot_and_all_parkinglot_is_full_when_parkingboy_parking_car_return_parking_car_fail(){
        //given
        Car myCar = new Car("京NX52G0");
        Car[] otherCar = new Car[10];

        for (int i = 0; i < otherCar.length; i++) {
            otherCar[i] = new Car("京NX52G" + (i+1));
        }

        for (int i = 0; i < otherCar.length; i++) {
            parkingBoy.parking(otherCar[i]);
        }

        //when
        ParkingInfo parkingInfo = parkingBoy.parking(myCar);

        //then
        Assert.assertEquals(parkingInfo.getStatus(), 0);
    }

    @Test
    public void should_given_no_parkinglot_when_parkingboy_parking_car_return_parking_car_fail(){
        //given
        ParkingLot noParkingLot = new ParkingLot(0,0);
        ParkingBoy boy = new ParkingBoy(noParkingLot);
        Car myCar = new Car("京NX52G0");

        //when
        ParkingInfo info = boy.parking(myCar);

        //then
        Assert.assertEquals(info.getStatus(),0);
    }
}
