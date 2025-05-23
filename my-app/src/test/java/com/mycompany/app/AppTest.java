package com.mycompany.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private static final double PRECISION = 5e-9;

    @Test
    public void testAverageCalculationPositive() {
        Sqrt solver = new Sqrt(0);
        double outcome = solver.average(3.0, 7.0);
        assertEquals(5.0, outcome, PRECISION);
    }

    @Test
    public void testAverageCalculationMixedSign() {
        Sqrt solver = new Sqrt(0);
        double outcome = solver.average(-4.5, 4.5);
        assertEquals(0.0, outcome, PRECISION);
    }

    @Test
    public void testGoodnessNearLimit() {
        Sqrt solver = new Sqrt(11.0);
        double testValue = Math.sqrt(11.0) + 1e-10;
        assertTrue(solver.good(testValue, 11.0));
    }

    @Test
    public void testGoodnessOutsideLimit() {
        Sqrt solver = new Sqrt(11.0);
        double testValue = Math.sqrt(11.0) + 1e-8;
        assertFalse(solver.good(testValue, 11.0));
    }

    @Test
    public void testImprovementStepConvergence() {
        Sqrt solver = new Sqrt(10.0);
        double startGuess = 2.5;
        double betterGuess = solver.improve(startGuess, 10.0);
        assertTrue(Math.abs(betterGuess - Math.sqrt(10.0)) < Math.abs(startGuess - Math.sqrt(10.0)));
    }

    @Test
    public void testImprovementStepWithExactGuess() {
        Sqrt solver = new Sqrt(20.0);
        double betterGuess = solver.improve(Math.sqrt(20.0), 20.0);
        assertEquals(Math.sqrt(20.0), betterGuess, PRECISION);
    }

    @Test
    public void testIterationFromGoodStart() {
        Sqrt solver = new Sqrt(30.0);
        double outcome = solver.iter(Math.sqrt(30.0), 30.0);
        assertEquals(Math.sqrt(30.0), outcome, PRECISION);
    }

    @Test
    public void testIterationFromNegativeStart() {
        Sqrt solver = new Sqrt(40.0);
        double outcome = solver.iter(-8.0, 40.0);
        assertEquals(-Math.sqrt(40.0), outcome, PRECISION);
    }

    @Test
    public void testCalculationPerfectSquare() {
        Sqrt solver = new Sqrt(64.0);
        double outcome = solver.calc();
        assertEquals(8.0, outcome, PRECISION);
    }

    @Test
    public void testCalculationNonPerfectSquare() {
        Sqrt solver = new Sqrt(3.0);
        double outcome = solver.calc();
        assertEquals(1.7320508, outcome, 1e-7);
    }

    @Test
    public void testCalculationFraction() {
        Sqrt solver = new Sqrt(0.16);
        double outcome = solver.calc();
        assertEquals(0.4, outcome, PRECISION);
    }

    @Test
    public void testCalculationEdgeCases() {
        Sqrt solverOne = new Sqrt(1.0);
        assertEquals(1.0, solverOne.calc(), PRECISION);

        Sqrt solverZero = new Sqrt(0.0);
        double zeroOutcome = solverZero.calc();
        assertTrue(solverZero.good(zeroOutcome, 0.0), "Calculation for zero should be considered 'good'");
    }
}