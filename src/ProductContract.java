public interface ProductContract {
    Product.ProductBuilder setName(String productName);
    Product.ProductBuilder setPrice(double price);
    Product.ProductBuilder setSalesTax(double salesTax);
    Product.ProductBuilder setImportTax(double importTax);
    Product.ProductBuilder setQuantity(int quantity);
    Product build();
}
