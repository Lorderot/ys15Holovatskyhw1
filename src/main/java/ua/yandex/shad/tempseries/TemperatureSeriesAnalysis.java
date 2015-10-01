package ua.yandex.shad.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
	final int initialMemoryLimit = 10;
	private int currentLength;
	final static double ZERO = -273.0;	
	private double[] temperatureSeries;
	
    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[initialMemoryLimit];
		currentLength = 0;
    }
    
    public TemperatureSeriesAnalysis(double[] temperatureSeries)
	throws InputMismatchException {
		for (int i = 0; i < temperatureSeries.length; i++) {
			if (temperatureSeries[i] < ZERO) {
				throw new InputMismatchException("The temperature is"
				+ "less then -273");
			}
		}
        if (temperatureSeries.length <= initialMemoryLimit / 2) {
			this.temperatureSeries = new double[initialMemoryLimit];
		}
		else {
			this.temperatureSeries = 
			new double[2 * temperatureSeries.length];
		}
		for (int i = 0; i < temperatureSeries.length; i++) {
			this.temperatureSeries[i] = temperatureSeries[i];
		}
		currentLength = temperatureSeries.length;
    }
    
	public int length() {
		return currentLength;
	}
	
	double sum(int power) throws IllegalArgumentException {
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double sumTemps = 0.0;
		for (int i = 0; i < currentLength; i++) {
			sumTemps += Math.pow(temperatureSeries[i], power);	
		}
        return sumTemps;
	}
	
    public double average() throws IllegalArgumentException {
        return sum(1)/currentLength;
    }    
    
    public double deviation() throws IllegalArgumentException {
        return sum(2)/currentLength - Math.pow(average(), 2);
    }
    
    public double min() throws IllegalArgumentException {
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double min = temperatureSeries[0];
		for (int i = 1; i < currentLength; i++) {
			if (min > temperatureSeries[i]) {
				min = temperatureSeries[i];
			}
		}
        return min;
    }
     
    public double max() {
        if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double max = temperatureSeries[0];
		for (int i = 1; i < currentLength; i++) {
			if (max < temperatureSeries[i]) {
				max = temperatureSeries[i];
			}
		}
        return max;
    }
    
    public double findTempClosestToZero() 
	throws IllegalArgumentException {
		return findTempClosestToValue(0);
    }
    
    public double findTempClosestToValue(double tempValue) 
	throws IllegalArgumentException {
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double closestTemp = temperatureSeries[0];
		for (int i = 1; i < currentLength; i++) {
			if (Math.abs(temperatureSeries[i] - tempValue)
			> Math.abs(closestTemp - tempValue)) {
				continue;
			}
			else if (Math.abs(temperatureSeries[i] - tempValue)
			< Math.abs(closestTemp - tempValue)) {
				closestTemp = temperatureSeries[i];
				}
			else if (temperatureSeries[i] > closestTemp) {
				closestTemp = temperatureSeries[i];
				}
		}
        return closestTemp;
	}
    
    public double[] findTempsLessThen(double tempValue) 
	throws IllegalArgumentException {
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double[] tempsLessThen = new double[currentLength];
		int j = 0;
		for (int i = 0; i < currentLength; i++) {
			if (temperatureSeries[i] < tempValue) {
				tempsLessThen[j] = temperatureSeries[i];
				j++;
			}
		}
		if (j == 0) {
			return new double[0];
		}
        return tempsLessThen;
    }
    
    public double[] findTempsGreaterThen(double tempValue) 
	throws IllegalArgumentException {
        if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double[] tempsGreaterThen = new double[currentLength];
		int j = 0;
		for (int i = 0; i < currentLength; i++) {
			if (temperatureSeries[i] > tempValue) {
				tempsGreaterThen[j] = temperatureSeries[i];
				j++;
			}
		}
		if (j == 0) {
			return new double[0];
		}
		return tempsGreaterThen;
    }
    
    public TempSummaryStatistics summaryStatistics() 
	throws IllegalArgumentException {
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }
    
    public int addTemps(double ... temps) throws InputMismatchException {
		for (int i = 0; i < temps.length; i++) {
			if (temps[i] < ZERO) {
				throw new 
				InputMismatchException("Temps less then "
				+ ZERO + " are forbidden");
			}
		}
		double[] newTempSeries;
		if (currentLength + temps.length > temperatureSeries.length) {
			if (temps.length <= temperatureSeries.length) {
				newTempSeries =
				new double[temperatureSeries.length * 2];
			}
			else {
				newTempSeries = new double[temps.length * 2];
			}
			for (int i = 0; i < currentLength; i++) {
				newTempSeries[i] = temperatureSeries[i];
			}
			temperatureSeries = newTempSeries;
		}
		for (int i = 0; i < temps.length; i++) {
			temperatureSeries[i + currentLength] = temps[i];
		}
		currentLength += temps.length;
        return currentLength;
    }
}
