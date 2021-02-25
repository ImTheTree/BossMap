package me.darklolly0312.map.BossMap;

public class Methods {
	public static double roundedDouble(double d) {
		double value = d;
        double roundedValue;
        //* 100 and / 100 depends on how many decimal points u want
        value = (int) (value * 10);
        roundedValue = value / 10;
		return roundedValue;
	}
}
