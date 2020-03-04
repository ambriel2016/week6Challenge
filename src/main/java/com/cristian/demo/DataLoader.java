package com.cristian.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    CarRepository carRepository;

    @Override
    public void run(String... strings) throws Exception{
        Car car1;
        car1 = new Car(1, "Cadillac", "XT4", "2019", "$35,190.00", "https://www.cadillac.com/suvs/xt4/build-and-price/trim", "Max");
        carRepository.save(car1);

        Car car2;
        car2 = new Car(2, "Chrysler", "Chrysler Pacifica ", "2020", "$33.745.00", "https://www.chrysler.com/incentives.html?modelYearCode=CUC202005", "James");
        carRepository.save(car2);

        Car car3;
        car3 = new Car(3, "Ford", "Ford Edge", "2020", "$31,100.00", "https://www.ford.com/suvs-crossovers/edge/?gnav=header-all-vehicles", "Cristian");
        carRepository.save(car3);

        Car car4;
        car4 = new Car(4, "Honda", "CR - V", "2020", "$25,050.00", "https://automobiles.honda.com/cr-v", "Astin");
        carRepository.save(car4);

        Car car5;
        car5 = new Car(5, "Jeep", "Compass", "2019", "$22.095.00", "https://www.jeep.com/2019/compass.html", "Josia");
        carRepository.save(car5);

        Car car6;
        car6 = new Car(6, "Volkswagen", "Golf SportWagen 1.4T S", "2020", "$21,895.00", "https://www.vw.com/builder/tab/trim/model/golf-sportwagen/", "Reynaldo");
        carRepository.save(car6);
    }
}

