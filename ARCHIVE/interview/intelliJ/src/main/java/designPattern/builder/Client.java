package designPattern.builder;

public class Client {
	public static void main(String[] args) {
		
		AirShipDirector director = new RealAirShipDirector(new RealAirShipBuilder());

		AirShip ship = director.directAirShip();
		
		System.out.println(ship.getEngine().getName());
		
		ship.launch();
		
	}
}
