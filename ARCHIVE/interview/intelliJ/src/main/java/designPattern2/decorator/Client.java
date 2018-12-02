package designPattern2.decorator;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Client {
	public static void main(String[] args) {
		Car car  = new Car();
		car.move();
		
		System.out.println("增加新的功能，飞行----------");
		FlyCar flycar = new FlyCar(car);
		flycar.move();
		
		System.out.println("增加新的功能，水里游---------");
		WaterCar  waterCar = new WaterCar(car);
		waterCar.move();
		
		System.out.println("增加两个新的功能，飞行，水里游-------");
		WaterCar waterCar2 = new WaterCar(new FlyCar(car));
		waterCar2.move();
//		陆地上跑！
//		增加新的功能，飞行----------
//		陆地上跑！
//		天上飞！
//		增加新的功能，水里游---------
//		陆地上跑！
//		水上游！
//		增加两个新的功能，飞行，水里游-------
//		陆地上跑！
//		天上飞！
//		水上游！		
		
//		Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:/a.txt"))));
		
	}
}
