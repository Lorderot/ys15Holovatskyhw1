package ua.yandex.shad.tempseries;

public class TempSummaryStatistics {
	private final double avgTemp;
	private final double devTemp;
	private final double minTemp;
	private final double maxTemp;
	public TempSummaryStatistics(double average, double deviation,
	double min, double max) {
		this.avgTemp = average;
		this.devTemp = deviation;
		this.minTemp = min;
		this.maxTemp = max;
	}
	
	public double getAverageTemp() {
		return avgTemp;
	}
	
	public double getDeviationTemp() {
		return devTemp;
	}
	
	public double getMinTemp() {
		return minTemp;
	}
	
	public double getMaxTemp() {
		return maxTemp;
	}
	
}
