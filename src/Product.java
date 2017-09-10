import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

class Product {

    private String mProductName;
    private double mProductPrice, mProductSalesTax, mProductImportTax;
    private int mQuantity;

    Product(ProductBuilder productBuilder) {
        mProductName = productBuilder.mProductName;
        mProductPrice = productBuilder.mProductPrice;
        mProductSalesTax = productBuilder.mProductSalesTax;
        mProductImportTax = productBuilder.mProductImportTax;
        mQuantity = productBuilder.mQuantity;
    }

    @Getter
    String getProductName() {
        return mProductName;
    }

    @Getter
    double getProductPrice() {
        return mProductPrice;
    }

    @Getter
    double getProductSalesTax() {
        return mProductSalesTax;
    }

    @Getter
    double getProductImportTax() {
        return mProductImportTax;
    }

    @Getter
    int getQuantity() {
        return mQuantity;
    }

    @Override
    public String toString() {
        return mQuantity+" "+ mProductName +" : "+mProductPrice;
    }

    static class ProductBuilder implements ProductContract {

        private String mProductName;
        private double mProductPrice, mProductSalesTax, mProductImportTax;
        private int mQuantity;

        @Setter
        @Override
        public ProductBuilder setName(@NotNull String productName) {
            this.mProductName = productName;
            return this;
        }

        @Setter
        @Override
        public ProductBuilder setPrice(double price) {
            this.mProductPrice = price;
            return this;
        }

        @Setter
        @Override
        public ProductBuilder setSalesTax(double salesTax) {
            this.mProductSalesTax = salesTax;
            return this;
        }

        @Setter
        @Override
        public ProductBuilder setImportTax(double importTax) {
            this.mProductImportTax = importTax;
            return this;
        }

        @Override
        public ProductBuilder setQuantity(int quantity) {
            this.mQuantity = quantity;
            return this;
        }

        @Override
        public Product build() {
            return new Product(this);
        }
    }

}
