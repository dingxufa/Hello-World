package designPattern.builder;

/**
 * Created by ding on 2018/3/7.
 */
public class RealAirShipDirector implements AirShipDirector {
    private AirShipBuilder builder;

    public RealAirShipDirector(AirShipBuilder builder) {
        this.builder = builder;
    }


    @Override
    public AirShip directAirShip() {
        Engine e = builder.builderEngine();
        OrbitalModule o = builder.builderOrbitalModule();
        EscapeTower et = builder.builderEscapeTower();

        //装配成飞船对象
        AirShip ship = new AirShip();
        ship.setEngine(e);
        ship.setEscapeTower(et);
        ship.setOrbitalModule(o);

        return ship;
    }
}
