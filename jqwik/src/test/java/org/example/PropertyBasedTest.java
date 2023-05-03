package org.example;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.PropertyDefaults;
import net.jqwik.api.ShrinkingMode;

import static org.junit.jupiter.api.Assertions.*;

@PropertyDefaults(tries = 1000, shrinking = ShrinkingMode.FULL)
class PropertyBasedTest{

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property(shrinking = ShrinkingMode.OFF)
    void lengthOfConcatenatedStringIsGreaterThanLengthOfEach(
            @ForAll String string1, @ForAll String string2
    ) {
        String conc = string1 + string2;
        assertTrue(conc.length() > string1.length());
        assertTrue(conc.length() > string2.length());
    }
    
}
