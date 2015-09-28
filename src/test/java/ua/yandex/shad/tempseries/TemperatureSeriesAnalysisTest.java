package ua.yandex.shad.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class TemperatureSeriesAnalysisTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorArgumentCorrectness() {
		double[] temperatureSeries = {10.0,-10.0,-200.0,13.0,-273.1};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
	}
	
	@Test
	public void testLength() {
		double[] temperatureSeries = {1.0, 4.2, 0.0, 5.4};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		int actualResult = seriesAnalysis.length();
		int expResult = 4;
		assertEquals(expResult, actualResult, 0.00001);
	}
	
    @Test
    public void testAverage() {
        double[] temperatureSeries = {1.0, -5.0, -1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }
    
	@Test (expected = IllegalArgumentException.class)
	public void testAverage_EmptySeries() {
		double[] temperatureSeries = {};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.average();
	}
	
	
	@Test
	public void testDeviation() {
		double[] temperatureSeries = {2, 2, 2, 2, 2, 2};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.deviation();
		double expResult = 0;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDeviation_EmptySeries() {
		double[] temperatureSeries = {};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.deviation();
	}
	
	@Test
	public void testMin() {
		double[] temperatureSeries = {0.0, -1.0 ,1.0 , 3.0, -2.0};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double expResult = -2.0;
		double actualResult = seriesAnalysis.min();
		assertEquals(expResult, actualResult, 0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMin_EmptySeries() {
		double[] temperatureSeries = {};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.min();
	}
	
	@Test
	public void testMax() {
		double[] temperatureSeries = {0.0, -1.0 ,1.0 , 3.0, -2.0};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double expResult = 3.0;
		double actualResult = seriesAnalysis.max();
		assertEquals(expResult, actualResult, 0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMax_EmptySeries() {
		double[] temperatureSeries = {};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.max();
	}
	
	@Test
	public void testFindTempClosestToZero_WithZeroTempInSeries() {
		double[] temperatureSeries = {-2.0, -1.0, 3.0, 0.0, -4.0};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToZero();
		double expResult = 0.0;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToZero_WithPositiveTempInSeries() {
		double[] temperatureSeries = {2.0, 1.0, 3.0, 0.5, 4.0, 0.9, 2.1, 0.4, 1.1};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToZero();
		double expResult = 0.4;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToZero_WithNegativeTempInSeries() {
		double[] temperatureSeries = {-2.0, -1.0, -3.0, -0.5, -4.0, -0.9, -2.1, -0.3, -1.1};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToZero();
		double expResult = -0.3;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToZero_WithNegativeAndPositiveTempInSeries() {
		double[] temperatureSeries = {-2.0, 1.0, -3.0, 0.5, 4.0, -0.9, 2.1, -0.3, 1.1, 0.25,-0.24};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToZero();
		double expResult = -0.24;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToZero_WithSimultaneouslyNegativeAndPositiveTempClosestToZeroInSeries() {
		double[] temperatureSeries = {-2.0, 2.0, -1.0, -3.0, 1.0, -0.5, -4.0, 0.9, 2.1, -0.3, -1.1, 0.3};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToZero();
		double expResult = 0.3;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFindTempClosestToZero_EmptySeries() {
		double[] temperatureSeries = {};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.findTempClosestToZero();
	}
	
	@Test
	public void testFindTempClosestToValue_WithThisValueTempInSeries() {
		double[] temperatureSeries = {-2.0, -1.0, 3.0, 5.0, -4.0};
		double value = 3.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToValue(value);
		double expResult = 3.0;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToValue_WithTempInSeriesGreaterThanThisValue() {
		double[] temperatureSeries = {3.5, 6.0, 4.0, 7.2, 3.9, 4.1, 5.4, 3.4, 5.1, 3.2};
		double value = 3.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToValue(value);
		double expResult = 3.2;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToValue_WithTempInSeriesLessThanThisValue() {
		double[] temperatureSeries = {-3.5, -6.0, -4.0, -7.2, -3.9, -4.1, -5.4, -3.4, -5.1, -3.2};
		double value = -3.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToValue(value);
		double expResult = -3.2;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToValue_WithDifferentTempInSeries() {
		double[] temperatureSeries = {-2.0, 2.1, 3.2, -4.3, 6.5, -6.5, -7.3, 4.0, 0.0};
		double value = 1.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToValue(value);
		double expResult = 0.0;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToValue_NegativeArgument() {
		double[] temperatureSeries = {1.0, -3.0, 0.5, 4.0, -0.9, 2.1, -0.3, 1.1, 0.25,-0.24, -2.5};
		double value = -2.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToValue(value);
		double expResult = -2.5;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToValue_WithDifferentValuesTempSimultaneouslyClosestToValueInSeries() {
		double[] temperatureSeries = {-6.0, -5.0, -4.5, 2.5, 5.5, -3.5, 0.0};
		double value = 4.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult = seriesAnalysis.findTempClosestToValue(value);
		double expResult = 5.5;
		assertEquals(expResult,actualResult,0.00001);
	}
	
	@Test
	public void testFindTempClosestToValue_ZeroArgument() {
		double[] temperatureSeries = {-6.0, -5.0, -4.5, 2.5, 5.5, -3.5, -2.5};
		double value = 0.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double actualResult1 = seriesAnalysis.findTempClosestToValue(value);
		double actualResult2 = seriesAnalysis.findTempClosestToZero();
		double expResult = 2.5;
		assertEquals(actualResult1, actualResult2, 0.00001);
		assertEquals(actualResult1, expResult, 0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFindTempClosestToValue_EmptySeries() {
		double[] temperatureSeries = {};
		double value = 0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.findTempClosestToValue(value);
	}
		
	@Test
	public void testFindTempsLessThen_WithNoSuitableTempsInSeries() {
		double[] temperatureSeries = {1.0, -2.0, 3.0, -5.0, 0.0, 3.2};
		double value = -10.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double[] actualResult = seriesAnalysis.findTempsLessThen(value);
		assertEquals(actualResult.length, 0, 0.00001);
	}
	
	@Test
	public void testFindTempsLessThen_WithProperTempsInSeries() {
		double[] temperatureSeries = {-23.2, 13.2, -14.2, -15.0, 0.0, 2.1, -12.4, -3.4, 5.4, 2.3, 6.7, 20.2, 36.6, 23.5, 0.0, 2.1, -1.0, -1.0};
		double value = 1.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double[] actualResult = seriesAnalysis.findTempsLessThen(value);
		double[] expResult = {-23.2, -14.2, -15.0, 0.0, -12.4, -3.4, 0.0, -1.0, -1.0};
		for (int i = 0; i < expResult.length; i++)
			assertEquals(expResult[i], actualResult[i], 0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFindTempsLessThen_EmptySeries() {
		double[] temperatureSeries = {};
		double value = 0.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.findTempsLessThen(value);
	}
	
	@Test
	public void testFindTempsGreaterThen_WithNoSuitableTempsInSeries() {
		double[] temperatureSeries = {1.0, -2.0, 3.0, -5.0, 0.0, 3.2};
		double value = 5.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double[] actualResult = seriesAnalysis.findTempsGreaterThen(value);
		assertEquals(actualResult.length, 0, 0.00001);
	}
	
	@Test
	public void testFindTempsGreaterThen_WithProperTempsInSeries() {
		double[] temperatureSeries = {-23.2, 13.2, -14.2, -15.0, 0.0, 2.1, -12.4, -3.4, 5.4, 2.3, 6.7, 20.2, 36.6, 23.5, 0.0, 2.1, -1.0, -1.0};
		double value = 1.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		double[] actualResult = seriesAnalysis.findTempsGreaterThen(value);
		double[] expResult = {13.2, 2.1, 5.4, 2.3, 6.7, 20.2, 36.6, 23.5, 2.1};
		for (int i = 0; i < expResult.length; i++)
			assertEquals(expResult[i], actualResult[i], 0.00001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFindTempsGreaterThen_EmptySeries() {
		double[] temperatureSeries = {};
		double value = 0.0;
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.findTempsGreaterThen(value);
	}
	
	@Test
	public void testSummaryStatistics() {
		double[] temperatureSeries = {-1.0, 2.0, -3.2, -1.4, 0.0, 3.5, 6.0};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		TempSummaryStatistics actualSummary = seriesAnalysis.summaryStatistics();
		double expAvg = seriesAnalysis.average();
		double expDev = seriesAnalysis.deviation();
		double expMin = seriesAnalysis.min();
		double expMax = seriesAnalysis.max();
		assertEquals(expAvg,actualSummary.getAverageTemp(), 0.00001);
		assertEquals(expDev,actualSummary.getDeviationTemp(), 0.00001);
		assertEquals(expMin,actualSummary.getMinTemp(), 0.00001);
		assertEquals(expMax,actualSummary.getMaxTemp(), 0.00001);
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public void testSummaryStatistics_EmptySeries() {
		double[] temperatureSeries = {};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		seriesAnalysis.summaryStatistics();
	}
		
	@Test
	public void testAddTemps_TestReturningValue() {
		double[] temperatureSeries = {0.0, 1.2, -4.5};
		double[] addedSeries = {-5.0, 3.2};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		int actualResult = seriesAnalysis.addTemps(addedSeries);
		int expResult = 5;
		assertEquals(expResult, actualResult, 0.00001);
	}
	
	@Test
	public void testAddTemps_WithForbiddenTempInArgument() {
		double[] addedSeries = {0.0, -2.3, 4.5, 120, -273.1};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
		try{
			seriesAnalysis.addTemps(addedSeries);
		} catch(InputMismatchException e) {}
		int expResult = 0;
		assertEquals(expResult, seriesAnalysis.length(), 0.00001);
	}

	@Test
	public void testAddTemps_WithAllowedTempsInArgument() {
		double[] temperatureSeries = {-5.3, 3.4, 0.0};
		double[] addedSeries = {2.9, 9.4, -3.5};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		int actualResult = seriesAnalysis.addTemps(addedSeries);
		int expResult = 6;
		assertEquals(expResult, actualResult, 0.00001);
	}
	
	@Test
	public void testAddTemps_WithReallocationMemory() {
		double[] temperatureSeries = {-5.0, 2.3, 1.5, 6.5, 0.0};
		double[] addedSeries = {3.4, 8.5, 34.2, 12.6, -20.0, 0.0, 2.3, 1.5};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
		int actualResult = seriesAnalysis.addTemps(addedSeries);
		int expResult = 13;
		assertEquals(expResult, actualResult, 0.00001);
	}

	@Test (expected = InputMismatchException.class)
	public void testAddTemps_WithNotAllowedTempInArgument() {
		double[] addedSeries = {0.0, -2.3, 4.5, 120, -273.1};
		TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
		seriesAnalysis.addTemps(addedSeries);
	}
}
