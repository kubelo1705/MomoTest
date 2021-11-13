import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import dao.CoinDAO;
import dao.MachineDAO;
import mapping.CommonMapping;
import model.Coin;

public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static int inputInt() {
		int value;

		try {
			value = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			value = 0;
		}
		return value;
	}

	public static void main(String[] args) throws IOException {
		int choose = 0;
		int coin;
		String sure;
		int totalLastDay = CommonMapping.getTotalLastDayFromFile();
		int totalToday = 0;
		int lastDay = CommonMapping.getLastDayFromFile();
		int range;
		if (totalLastDay < 50000) {
			range = 10;
		} else {
			range = 2;
		}
		int count = 0;
		while (true) {
			MachineDAO.getAllProducts();
			System.out.println("0.Exit");
			MachineDAO.getSale();

			System.out.print("Choose product:");
			choose = inputInt();
			if (choose != 0 && choose <= MachineDAO.productCount()) {
				System.out.print("Input coin:");
				coin = inputInt();
				if (CoinDAO.isAcceptedCoin(new Coin(coin))) {
					if (coin >= MachineDAO.getProductPrice(choose)) {
						System.out.println("Are you sure to buy this product?(Y or N)");
						sure = scanner.nextLine();
						if (sure.equalsIgnoreCase("Y")) {
							int price = MachineDAO.getProductPrice(choose);
							if (count == 3 && totalToday <= 50000) {
								count = 0;
								if (new Random().nextInt(range) == 0) {
									coin += price;
									totalToday += price;
								}
							} else {
								count++;
							}
							System.out.println("Product was Released");
							count++;
							String changeCoin = CoinDAO.changeCoin(coin - price);
							if (!changeCoin.isEmpty()) {
								System.out.print("Returned change coin:" + (coin - price));
								System.out.println(changeCoin);
							}
						} else {
							System.out.println("Returned full coin");
						}
					} else {
						System.out.println("Coin is not enough");
						System.out.println("Returned full coin");
					}
				} else {
					System.out.println("Invalid coin");
				}
			} else if (choose == 0) {
				break;
			} else {
				System.out.println("Invalid product");
			}
			System.out.println("------------------------------------");

		}
		CommonMapping.writeTodayToFile(lastDay, totalToday);
	}
}
