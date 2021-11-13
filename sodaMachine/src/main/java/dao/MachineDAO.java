package dao;

import mapping.ProductMapping;
import model.Machine;
import model.Product;

import java.util.List;

public class MachineDAO {
	private static Machine machine= new Machine();
	
	static {
		machine.getProducts().addAll(ProductMapping.getProductsFromFile());
	}
	
	public static void addProduct(Product product) {
		machine.getProducts().add(product);
	}
	
	public static void getAllProducts() {
		List<Product> products=machine.getProducts();
		System.out.println("List of products:");
		if(!products.isEmpty()) {
			products.forEach(product->System.out.println((products.indexOf(product)+1)+"."+product.show()));
		}else {
			System.out.println("\t-No product");
		}
	}
	
	public static int productCount() {
		return machine.getProducts().size();
	}
	
	public static int getProductPrice(int index) {
		return machine.getProducts().get(index-1).getPrice();
	}
	
	public static void getSale() {
		System.out.println("*NOTE: IF THERE ARE 3 CONSECUTIVE PURCHASES OF THE SAME PRODUCT,"
				+ " YOU WILL HAVE A CHANCE TO RECEIVE A PRODUCT FOR FREE.*");
	}
}
