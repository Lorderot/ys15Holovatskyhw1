package ua.yandex.shad.tempseries;

public class TempSummaryStatistics {
    public final double avgTemp;
	public final double devTemp;
	public final double minTemp;
	public final double maxTemp;
	public TempSummaryStatistics(double average, double deviation,
	double min, double max) {
		this.avgTemp = average;
		this.devTemp = deviation;
		this.minTemp = min;
		this.maxTemp = max;
	}
}
