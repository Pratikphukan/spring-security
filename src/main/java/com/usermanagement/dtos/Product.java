package com.usermanagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private int productId;

	private String name;

	private int qty;

	private double price;

	private Product(Builder builder) {
		this.productId = builder.productId;
		this.name = builder.name;
		this.qty = builder.qty;
		this.price = builder.price;
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	@Getter
	public static class Builder {

		private int productId;

		private String name;

		private int qty;

		private double price;

		public Builder setProductId(int productId) {
			this.productId = productId;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setQty(int qty) {
			this.qty = qty;
			return this;
		}

		public Builder setPrice(double price) {
			this.price = price;
			return this;
		}

		public Product build() {
			return new Product(this);
		}

	}

}
