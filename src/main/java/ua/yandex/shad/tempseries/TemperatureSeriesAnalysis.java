package ua.yandex.shad.tempseries;

public class TemperatureSeriesAnalysis {    
	private double[] temperatureSeries;
	private int currentLength;

    public TemperatureSeriesAnalysis() {
        double[] temperatureSeries = new double[10];
		currentLength = 0;
    }
    
    public TemperatureSeriesAnalysis(double[] temperatureSeries) throws IllegalArgumentException {
		for (int i = 0; i < temperatureSeries.length; i++)
			if (temperatureSeries[i] < -273.0) throw new IllegalArgumentException("The temperature can't be less then -273");
        if (temperatureSeries.length < 10) this.temperatureSeries = new double[10];
		else this.temperatureSeries = new double[temperatureSeries.length];
		for (int i = 0; i < temperatureSeries.length; i++)
			this.temperatureSeries[i] = temperatureSeries[i];
		currentLength = temperatureSeries.length;
    }
    
    public double average() throws IllegalArgumentException {
		if (currentLength == 0) throw new IllegalArgumentException("Series is empty");
		double sum = 0.0;
		for (int i = 0; i < currentLength; i++)
			sum += temperatureSeries[i];		
        return sum/currentLength;
    }    
    
    public double deviation() throws IllegalArgumentException {
		if (currentLength == 0) throw new IllegalArgumentException("Series is empty");
		double sum = 0.0;
		for (int i = 0; i < currentLength; i++)
			sum += Math.pow((temperatureSeries[i]),2)/currentLength; 
        return sum - Math.pow((average()),2);
    }
    
    public double min() throws IllegalArgumentException {
		if (currentLength == 0) throw new IllegalArgumentException("Series is empty");
		double min = temperatureSeries[0];
		for (int i = 1; i < currentLength; i++)
			if (min > temperatureSeries[i]) min = temperatureSeries[i];
        return min;
    }
     
    public double max() {
        if (currentLength == 0) throw new IllegalArgumentException("Series is empty");
		double max = temperatureSeries[0];
		for (int i = 1; i < currentLength; i++)
			if (max < temperatureSeries[i]) max = temperatureSeries[i];
        return max;
    }
    
    public double findTempClosestToZero() throws IllegalArgumentException {
		if (currentLength == 0) throw new IllegalArgumentException("Series is empty");
		double closestTemp = temperatureSeries[0];
		for (int i = 0; i < currentLength; i++)
			if (Math.abs(temperatureSeries[i]) > Math.abs(closestTemp)) continue;
			else if (Math.abs(temperatureSeries[i]) < Math.abs(closestTemp)) closestTemp = temperatureSeries[i];
			else if (temperatureSeries[i] > closestTemp) closestTemp = temperatureSeries[i];
        return closestTemp;
    }
    
    public double findTempClosestToValue(double tempValue) throws IllegalArgumentException {
		if (currentLength == 0) throw new IllegalArgumentException("Series is empty");
		double closestTemp = temperatureSeries[0];
		for (int i = 1; i < currentLength; i++)
			if (Math.abs(temperatureSeries[i] - tempValue) > Math.abs(closestTemp - tempValue)) continue;
			else if (Math.abs(temperatureSeries[i] - tempValue) < Math.abs(closestTemp - tempValue)) closestTemp = temperatureSeries[i];
			else if (temperatureSeries[i] > closestTemp) closestTemp = temperatureSeries[i];
        return closestTemp;
	}
    
    public double[] findTempsLessThen(double tempValue) throws IllegalArgumentException {
		if (currentLength == 0) throw new IllegalArgumentException("Series is empty");
		double[] tempsLessThen = new double[currentLength];
		int j = 0;
		for (int i = 0; i < currentLength; i++)
			if (temperatureSeries[i] < tempValue) {
				tempsLessThen[j] = temperatureSeries[i];
				j++;
			}
		if (j == 0) return new double[0];
        return tempsLessThen;
    }
    
    public double[] findTempsGreaterThen(double tempValue) {
        return null;
    }
    
    public TempSummaryStatistics summaryStatistics() {
        return null;
    }
    
    public int addTemps(double ... temps) {
        return 0;
    }
}
