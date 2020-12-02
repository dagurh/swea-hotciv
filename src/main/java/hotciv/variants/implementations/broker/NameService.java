package hotciv.variants.implementations.broker;

import hotciv.framework.City;
import hotciv.framework.Unit;

public interface NameService {
    void putCity(String id, City city);
    City getCity(String id);
    void putUnit(String id, Unit unit);
    Unit getUnit(String id);
}
