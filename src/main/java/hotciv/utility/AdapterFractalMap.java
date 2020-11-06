package hotciv.utility;

import hotciv.framework.GameConstants;
import thirdparty.ThirdPartyFractalGenerator;

public class AdapterFractalMap {

    public static String[] fractalMap () {
        String[] randomWorldLayout = new String[GameConstants.WORLDSIZE];
        ThirdPartyFractalGenerator generator =
                new ThirdPartyFractalGenerator();
        String line;
        for ( int r = 0; r < 16; r++ ) {
            line = "";
            for ( int c = 0; c < 16; c++ ) {
                line = line + generator.getLandscapeAt(r,c);
            }
            randomWorldLayout[r] = line;

        } return randomWorldLayout;
    }
}
