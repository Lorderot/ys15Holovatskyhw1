package ua.yandex.shad.tempseries;

import java.util.*;

public class TemperatureSeriesAnalysis {
	private int currentLength;
	private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[10];
		currentLength = 0;
    }
    
    public TemperatureSeriesAnalysis(double[] temperatureSeries)
	throws IllegalArgumentException {
		for (int i = 0; i < temperatureSeries.length; i++) {
			if (temperatureSeries[i] < -273.0) {
				throw new IllegalArgumentException("The temperature is less then -273");
			}
		}
        if (temperatureSeries.length <= 5) {
			this.temperatureSeries = new double[10];
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
	
    public double average() throws IllegalArgumentException {
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double sum = 0.0;
		for (int i = 0; i < currentLength; i++) {
			sum += temperatureSeries[i];	
		}
        return sum/currentLength;
    }    
    
    public double deviation() throws IllegalArgumentException {
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double sum = 0.0;
		for (int i = 0; i < currentLength; i++) {
			sum += Math.pow(temperatureSeries[i], 2)/currentLength; 
		}
        return sum - Math.pow(average(), 2);
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
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double closestTemp = temperatureSeries[0];
		for (int i = 0; i < currentLength; i++) {
			if (Math.abs(temperatureSeries[i]) >
			Math.abs(closestTemp)) {
			continue;
			}
			else if (Math.abs(temperatureSeries[i]) 
				< Math.abs(closestTemp)) {
				closestTemp = temperatureSeries[i];
				}
			else if (temperatureSeries[i] > closestTemp) {
				closestTemp = temperatureSeries[i];
				}	
		}
        return closestTemp;
    }
    
    public double findTempClosestToValue(double tempValue) 
	throws IllegalArgumentException {
		if (currentLength == 0) {
			throw new IllegalArgumentException("Series is empty");
		}
		double closestTemp = temperatureSeries[0];
		for (int i = 1; i < currentLength; i++) {
			if (Math.abs(temperatureSeries[i] - tempValue) >
			Math.abs(closestTemp - tempValue)) {
				continue;
			}
			else if (Math.abs(temperatureSeries[i] - tempValue) <
			Math.abs(closestTemp - tempValue)) {
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
			if (temps[i] < -273.0) {
				throw new InputMismatchException("Temps less then -273.0 are forbidden");
			}
		}
		double[] newTempSeries;
		if (currentLength + temps.length > temperatureSeries.length) {
			if (temps.length <= temperatureSeries.length) {
				newTempSeries = new double[temperatureSeries.length * 2];
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
