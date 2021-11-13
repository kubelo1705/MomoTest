package dao;

import java.util.ArrayList;
import java.util.List;

import model.Coin;

public class CoinDAO {
	private static List<Integer> listAcceptedCoin=new ArrayList<Integer>();
	
	static {
		listAcceptedCoin.add(200000);
		listAcceptedCoin.add(100000);
		listAcceptedCoin.add(50000);
		listAcceptedCoin.add(20000);
		listAcceptedCoin.add(10000);
	}
	
	public static boolean isAcceptedCoin(Coin coin) {
		return listAcceptedCoin.contains(coin.getValue());
	}
	
	public static String changeCoin(int changeCoin) {
		StringBuilder change=new StringBuilder();
		for (Integer coin : listAcceptedCoin) {
			while(changeCoin>=coin && changeCoin!=0) {
				changeCoin-=coin;
				change.append("\n"+String.valueOf(coin));
			}
		}
		return change.toString();
	}
}
