package hotciv.variants.implementations.broker;

import hotciv.framework.City;
import hotciv.framework.Unit;

import java.util.HashMap;

public class HotCivNameService implements NameService {

    private HashMap<String, City> cityMap;
    private HashMap<String, Unit> unitMap;

    public HotCivNameService() {
        cityMap = new HashMap<>();
        unitMap = new HashMap<>();
    }


    @Override
    public void putCity(String id, City city) {
        cityMap.put(id, city);
    }

    @Override
    public City getCity(String id) {
       return cityMap.get(id);
    }

    @Override
    public void putUnit(String id, Unit unit) {
        unitMap.put(id, unit);
    }

    @Override
    public Unit getUnit(String id) {
        return unitMap.get(id);
    }
}
