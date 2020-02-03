package by.epam.buber.service.impl.util;

import java.util.Random;

public class CoordinatesGenerator {
    public static long generate(){
        Random random = new Random();
        int coordinates = random.nextInt(999000000 + 1);
        coordinates +=1000000;
        return coordinates;
    }
}
