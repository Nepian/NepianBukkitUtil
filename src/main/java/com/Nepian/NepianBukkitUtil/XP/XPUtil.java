package com.Nepian.NepianBukkitUtil.XP;

import java.util.Arrays;

public class XPUtil {
	private static int[] totalExpToReachLevel;
	
	static { initExpTable(40); }
	
	private static void initExpTable(int maxLevel) {
		totalExpToReachLevel = new int[maxLevel];
		for (int i = 0; i < totalExpToReachLevel.length; i++) {
			totalExpToReachLevel[i] = convertXpToExp(i);
		}
	}
	
	public static int convertXpToExp(int xp) {
		if (xp >= 30) return (int) (3.5 * xp * xp - 151.5 * xp + 2220);
		if (xp >= 16) return (int) (1.5 * xp * xp - 29.5 * xp + 360);
		if (xp >= 0)  return 17 * xp;
		return 0;
	}
	
	public static int convertExpToXp(int exp) {
		if (!(0 < exp)) return 0;

		while (exp > totalExpToReachLevel[totalExpToReachLevel.length - 1]) {
			int newMax = totalExpToReachLevel.length + 40;
			initExpTable(newMax);
		}

		int pos = Arrays.binarySearch(totalExpToReachLevel, exp);
		int xp = (pos < 0) ? -pos - 2 : pos;

		return xp;
	}
	
	public static int convertExpPercentageToExp(int xp, float expPercentage) {
		return (int) (Math.round(getExpNeededToLevelUp(xp)) * expPercentage);
	}
	
	public static int getExpNeededToLevelUp(int xp) {
		if (xp >= 30) return 62 + (xp - 30) * 7;
		if (xp >= 15) return 17 + (xp - 15) * 3;
		if (xp >= 0)  return 17;
		return 0;
	}

	public static int getExpXpToXp(int xp1, int xp2) {
		return convertXpToExp(xp1) - convertXpToExp(xp2);
	}
}
