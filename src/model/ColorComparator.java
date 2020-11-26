package model;

import java.util.Comparator;

public class ColorComparator implements Comparator<Indicator>{

	@Override
	public int compare(Indicator i1, Indicator i2) {
		return i2.getType()-i1.getType();
	}

}
