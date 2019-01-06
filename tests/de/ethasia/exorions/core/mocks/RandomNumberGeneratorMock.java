package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.RandomNumberGenerator;

public class RandomNumberGeneratorMock implements RandomNumberGenerator {

    @Override
    public boolean createRandomBoolean() {
        return false;
    }

    @Override
    public int createRandomIntegerBetweenAnd(int lowerBound, int upperBound) {
        return 0;
    }
}