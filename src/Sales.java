/*
 *  Copyright (C) 2017 nsnikhil
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

/*
 * QUESTION :
 *
 * BASIC SALES TAX IS APPLICABLE AT A RATE OF 10% ON ALL GOODS, EXCEPT BOOKS, FOOD, AND MEDICAL PRODUCTS
 * THAT ARE EXEMPT. IMPORT DUTY IS AN ADDITIONAL SALES TAX APPLICABLE ON ALL IMPORTED GOODS AT A RATE OF 5%,
 * WITH NO EXEMPTIONS.
 * WHEN I PURCHASE ITEMS I RECEIVE A RECEIPT WHICH LISTS THE NAME OF ALL THE ITEMS AND THEIR PRICE
 * (INCLUDING TAX), FINISHING WITH THE TOTAL COST OF THE ITEMS, AND THE TOTAL AMOUNTS OF SALES TAXES PAID.
 * THE ROUNDING RULES FOR SALES TAX ARE THAT FOR A TAX RATE OF N%, A SHELF PRICE OF P CONTAINS
 * (NP/100 ROUNDED UP TO THE NEAREST 0.05) AMOUNT OF SALES TAX.
 *
 * INPUT FORMAT :
 *
 * FIRST LINE OF INPUT IS A INTEGER N
 * N == 1 IF YOU WANT KEEP ADDING PRODUCTS
 * N == 2 IF YOU WANT TO DISPLAY THE PRICE AND TAX OF CURRENT PRODUCT LIST
 * N == 3 IF YOU WANT TO EXIT THE PROGRAM
 *
 * IF YOU CHOOSE TO ENTER A PRODUCT THEN ENTER ITS
 * NAME, PRICE, ITS TYPE, AND IMPORT STATUS
 *
 * INPUT :
 *
 * ENTER 1 TO ADD PRODUCTS
 * ENTER 2 TO CALCULATE PRICE AND SALES TAX
 * ENTER 0 TO EXIT
 *
 * 1
 *
 * ENTER THE PRODUCT NAME
 * BOOK
 *
 * ENTER THE PRODUCT PRICE
 * 12.49
 *
 * ENTER THE PRODUCT QUANTITY
 * 1
 *
 * IS THE PRODUCT A BOOK, FOOD OR MEDICAL PRODUCT ENTER 0 IF YES ELSE 1
 * 0
 *
 * ENTER 1 IF THE PRODUCT IS IMPORTED ELSE 0
 * 0
 *
 *
 *
 * ENTER 1 TO ADD PRODUCTS
 * ENTER 2 TO CALCULATE PRICE AND SALES TAX
 * ENTER 0 TO EXIT
 *
 * 1
 *
 * ENTER THE PRODUCT NAME
 * MUSICCD
 *
 * ENTER THE PRODUCT PRICE
 * 14.99
 *
 * ENTER THE PRODUCT QUANTITY
 * 1
 *
 * IS THE PRODUCT A BOOK, FOOD OR MEDICAL PRODUCT ENTER 0 IF YES ELSE 1
 * 1
 *
 * ENTER 1 IF THE PRODUCT IS IMPORTED ELSE 0
 * 0
 *
 *
 *
 *
 * ENTER 1 TO ADD PRODUCTS
 * ENTER 2 TO CALCULATE PRICE AND SALES TAX
 * ENTER 0 TO EXIT
 *
 * 1
 *
 * ENTER THE PRODUCT NAME
 * CHOCOLATE
 *
 * ENTER THE PRODUCT PRICE
 * 0.85
 *
 * ENTER THE PRODUCT QUANTITY
 * 1
 *
 * IS THE PRODUCT A BOOK, FOOD OR MEDICAL PRODUCT ENTER 0 IF YES ELSE 1
 * 0
 *
 * ENTER 1 IF THE PRODUCT IS IMPORTED ELSE 0
 * 0
 *
 *
 *
 *
 * ENTER 1 TO ADD PRODUCTS
 * ENTER 2 TO CALCULATE PRICE AND SALES TAX
 * ENTER 0 TO EXIT
 *
 * 2
 *
 * OUTPUT :
 *
 * 1 BOOK : 12.49
 * 1 MUSICCD : 16.49
 * 1 CHOCOLATE : .85
 * SALES TAX : 1.5
 * TOTLA : 29.83
 *
 * ENTER 1 TO ADD PRODUCTS
 * ENTER 2 TO CALCULATE PRICE AND SALES TAX
 * ENTER 0 TO EXIT
 *
 * 0
 *
 * PROCESS FINISHED WITH EXIT CODE 0
 */


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sales {

    private List<Product> mProductList = new ArrayList<>();


    public static void main(String... args) {
        Sales sales = new Sales();
        sales.purchaseOperation();
    }

    private void purchaseOperation() {
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\nEnter 1 to add products" +
                    "\nEnter 2 to calculate price and sales tax" +
                    "\nEnter 0 to exit\n");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    String productName;
                    double productPrice, productSalesTax = 0, productImportTax = 0;
                    int quantity;

                    System.out.println("\nEnter the product name\n");
                    productName = scanner.next();

                    System.out.println("\nEnter the product price\n");
                    productPrice = scanner.nextDouble();

                    System.out.println("\nEnter the product quantity\n");
                    quantity = scanner.nextInt();

                    System.out.println("\nIs the product a book, food or medical product enter 0 if yes else 1\n");
                    int hasSalesTax = scanner.nextInt();

                    if (hasSalesTax == 1) {
                        productSalesTax = 10;
                    }

                    System.out.println("\nEnter 1 if the product is imported else 0\n");
                    int isImported = scanner.nextInt();

                    if (isImported == 1) {
                        productImportTax = 5;
                    }

                    Product product = new Product.ProductBuilder()
                            .setName(productName)
                            .setPrice(productPrice)
                            .setSalesTax(productSalesTax)
                            .setImportTax(productImportTax)
                            .setQuantity(quantity)
                            .build();

                    mProductList.add(product);
                    break;
                case 2:
                    displayTotal();
                    break;
            }

        } while (choice != 0);
    }

    /**
     * ITERATES THROUGH EVERY PRODUCT IN PRODUCT LIST
     * AND PRINTS THE PRODUCT ACCORDING TO THE OUTPUT
     * FORMAT AND ALSO DISPLAY THE TOTAL COST AND
     * ALSO THE TOTAL SALES TAX
     */
    private void displayTotal() {
        double salesTax = 0, price = 0;
        for (Product product : mProductList) {

            price += product.getProductPrice();

            double currentItemTax = roundTax(calculateTax(product));

            salesTax += currentItemTax;

            System.out.println(printOutput(product, currentItemTax));

        }
        salesTax = roundTax(salesTax);
        System.out.println("Sales Tax : " + salesTax);
        System.out.println("Total : " + toTwoDecimal().format(price + salesTax));
    }

    /**
     * @param product THE PRODUCT FOR WHICH TAX HAS TO BE CALCULATED
     * @return THE TOTAL SALES TAX CALCULATED FOR THE PRODUCT
     */
    @Contract(pure = true)
    private double calculateTax(Product product) {
        double price = product.getProductPrice();
        double salesTax = (price * product.getProductSalesTax()) / 100;
        double importTax = (price * product.getProductImportTax()) / 100;
        return salesTax + importTax;
    }

    /**
     * @param tax THE TAX AMOUNT
     * @return THE TAX AMOUNT ROUNDED TO NEAREST 0.05
     */
    @Contract(pure = true)
    private double roundTax(double tax) {
        return Math.round(tax * 20.0) / 20.0;
    }

    /**
     * @return DECIMAL FORMATTER TO FORMAT DECIMAL TO TWO PLACES
     */
    @NotNull
    private DecimalFormat toTwoDecimal() {
        return new DecimalFormat("#.00");
    }


    /**
     * @param product THE PRODUCT WHOSE OUTPUT IS TO BE WRITTEN
     * @param tax     THE TOTAL TAX FOR THAT PRODUCT
     * @return THE FORMATTED OUTPUT STRING
     */
    @NotNull
    private String printOutput(Product product, double tax) {
        return product.getQuantity() + " " + product.getProductName() + " : " + toTwoDecimal().format(product.getProductPrice() + tax);
    }

}
