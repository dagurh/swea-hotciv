package hotciv.variants;

import hotciv.variants.implementations.AlphaCivAgingStrategy;
import hotciv.variants.implementations.AlphaCivWinnerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class AlphaCivAgingStrategyTest {

    private AlphaCivAgingStrategy alphaCivAgingStrategy;

    @BeforeEach
    void setup(){
        alphaCivAgingStrategy = new AlphaCivAgingStrategy();
    }

    @Test
    public void advanceAgefrom4000BCWillReturn3900BCTest(){
        int age = -4000;
        assertThat(alphaCivAgingStrategy.advanceAge(age), is(-3900));
    }

}
