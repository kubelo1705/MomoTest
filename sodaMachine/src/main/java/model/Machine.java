package model;

import java.util.ArrayList;
import java.util.List;

public class Machine {
	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Machine(List<Product> products) {
		super();
		this.products = products;
	}

	public Machine() {
		super();
		this.products = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Machine [products=" + products + "]";
	}
	
}
