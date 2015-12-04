package com.Nepian.NepianBukkitUtil.Player;

import org.bukkit.entity.Player;

import com.Nepian.NepianBukkitUtil.XP.XPUtil;

public class PlayerXPUtil {
	public static final int MAX_EXP = 2100000000;

	/**
	 * プレイヤーのXPを取得する
	 * @param player
	 * @return XP
	 */
	public static int getXP(Player player) {
		return player.getLevel();
	}
	
	/**
	 * プレイヤーのEXPを取得する
	 * @param player
	 * @return Exp
	 */
	public static float getExp(Player player) {
		return player.getExp();
	}
	
	/**
	 * プレイヤーの総EXP量を取得する
	 * @param player
	 * @return TotalExp Quantity
	 */
	public static int getTotalExp(Player player) {
		int xp = getXP(player);
		float exp = getExp(player);
		return XPUtil.convertXpToExp(xp) + XPUtil.convertExpPercentageToExp(xp, exp);
	}
	
	/**
	 * プレイヤーのXPを設定する
	 * @param player
	 * @param xp
	 * @return player
	 */
	public static Player setXP(Player player, int xp) {
		player.setLevel(xp);
		return player;
	}
	
	/**
	 * プレイヤーのEXPを設定する
	 * @param player
	 * @param exp
	 * @return player
	 */
	public static Player setExp(Player player, float exp) {
		player.setExp(exp);
		return player;
	}
	
	/**
	 * プレイヤーの総EXP量を設定する
	 * @param player
	 * @param quantity
	 * @return player
	 */
	public static Player setTotalExp(Player player, int quantity) {
		int amount = Math.min(MAX_EXP, Math.max(0, quantity));
		int xp = XPUtil.convertExpToXp(amount);

		float exp = (float) (amount - XPUtil.convertXpToExp(xp))
				/ (float) XPUtil.getExpNeededToLevelUp(xp);

		setXP(player, xp);
		setExp(player, exp);

		return player;
	}
	
	/**
	 * プレイヤーの次のXPレベルまでに必要な残りのExp量を取得する
	 * @param player
	 * @return Exp Quantity
	 */
	public static int getRestExpNeededToLevelUp(Player player) {
		int xp = getXP(player);
		int totalExp = getTotalExp(player);
		return XPUtil.convertXpToExp(xp + 1) - totalExp;
	}
	
	/**
	 * プレイヤーの次のXPレベルまでに必要なExp量を取得する
	 * @param player
	 * @return Exp Quantity
	 */
	public static int getExpNeededForNextLevel(Player player) {
		return XPUtil.getExpNeededToLevelUp(getXP(player));
	}
}
