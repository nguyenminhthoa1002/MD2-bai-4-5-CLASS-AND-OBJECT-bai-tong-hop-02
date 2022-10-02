package data.dev;

import java.util.Scanner;

public class Menu {
    static Catalog[] dataCatalog = new Catalog[100];
    static int indexCatalog = 0;
    static Product[] dataProduct = new Product[1000];
    static int indexProduct = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void menuCatalogManagement() {
        do {
            System.out.println("********************QUẢN LÝ DANH MỤC******************");
            System.out.println("1. Nhập thông tin n danh mục");
            System.out.println("2. Hiển thị thông tin danh mục theo độ ưu tiên");
            System.out.println("3. Cập nhật thông tin danh mục theo mã danh mục");
            System.out.println("4. Thoát");
            System.out.println("Your choice is: ");

            int choice1 = Integer.parseInt(scanner.nextLine());
            switch (choice1){
                case 1:
                    Menu.inputCatalog(scanner);
                    break;
                case 2:
                    Menu.displayCatalogByPriority();
                    break;
                case 3:
                    Menu.updateCatalogInformationByCatalogId(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Please choose 1-4");
            }
        } while (true);
    }

    public static void inputCatalog(Scanner scanner){
        System.out.println("Enter the number of Catalog you want to input: ");
        int inputCatalog = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < inputCatalog; i++) {
            System.out.printf("Input Catalog %d\n",i+1);
            dataCatalog[i] = new Catalog();
            dataCatalog[i].inputCatalog();
            indexCatalog++;
        }
    }

    public static void displayCatalogByPriority() {
        System.out.printf("%-20s%-30s%-15s%-15s\n","Catalog ID","Catalog Name","Priority","Status");
        for (int i = 0; i < indexCatalog-1; i++) {
            for (int j = i+1; j < indexCatalog; j++) {
                if (dataCatalog[i].getPriority()>dataCatalog[j].getPriority()){
                    Catalog tempt = dataCatalog[i];
                    dataCatalog[i] = dataCatalog[j];
                    dataCatalog[j] = tempt;
                }
            }
        }
        for (int i = 0; i < indexCatalog; i++) {
            dataCatalog[i].displayCatalog();
        }
    }

    public static void updateCatalogInformationByCatalogId(Scanner scanner) {
        System.out.println("Enter the Catalog ID you want to update: ");
        int updateCatalog = Integer.parseInt(scanner.nextLine());
        boolean checkUpdateCatalog = false;
        for (int i = 0; i < indexCatalog; i++) {
            if (dataCatalog[i].getCatalogId()==updateCatalog) {
                dataCatalog[i].inputCatalog();
                checkUpdateCatalog = true;
                break;
            } else {
                checkUpdateCatalog = false;
            }
        }
        if (checkUpdateCatalog) {
            System.out.println("All information are updated");
        } else {
            System.err.println("This Catalog doesn't exist! Please try again");
        }
    }

    public static void menuProductManagement(){
        do {
            System.out.println("********************QUẢN LÝ SẢN PHẨM*******************");
            System.out.println("1. Nhập thông tin cho n sản phẩm (n nhập từ bàn phím)");
            System.out.println("2. Tính giá bán cho tất cả sản phẩm");
            System.out.println("3. Hiển thị thông tin các sản phẩm");
            System.out.println("4. Sắp xếp sản phẩm theo giá bán tăng dần");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("6. Thống kê số lượng và in thông tin các sản phẩm sắp hết hàng (quantity<=5)");
            System.out.println("7. Cập nhật trạng thái của sản phẩm theo mã sản phẩm");
            System.out.println("8. Thoát");
            System.out.println("Your choice is: ");

            int choiceProduct = Integer.parseInt(scanner.nextLine());
            switch (choiceProduct) {
                case 1:
                    Menu.inputProduct(scanner);
                    break;
                case 2:
                    Menu.calExportPrice();
                    break;
                case 3:
                    Menu.displayProduct();
                    break;
                case 4:
                    Menu.sortProductByExportPriceACS();
                    break;
                case 5:
                    Menu.searchProductByName(scanner);
                    break;
                case 6:
                    Menu.displayProductOutOfStock();
                    break;
                case 7:
                    Menu.changeProductStatus(scanner);
                    break;
                case 8:
                    return;
                default:
                    System.err.println("Please choose 1-8");
            }
        } while (true);
    }

    public static void inputProduct(Scanner scanner) {
        System.out.println("Enter the number of Product you want to input: ");
        int inputNumber = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < inputNumber; i++) {
            System.out.printf("Product %d\n",i+1);
            dataProduct[i] = new Product();
            dataProduct[i].inputProduct();
            indexProduct++;
        }
    }

    public static void calExportPrice() {
        for (int i = 0; i < indexProduct; i++) {
            dataProduct[i].calExportPrice();
        }
        System.out.println("All export price have been calculated!");
    }

    public static void displayProduct() {
        System.out.println("******************************Product Information*********************************");
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-15s%-20s%-15s%-20s\n","Product Id","Product Name","Title","Import Price","Export Price","Quantity","Descriptions","Status","Catalog");
        for (int i = 0; i < indexProduct; i++) {
            dataProduct[i].displayProduct();
        }
    }

    public static void sortProductByExportPriceACS() {
        System.out.println("******************************Product Information*********************************");
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-15s%-20s%-15s\n","Product Id","Product Name","Title","Import Price","Export Price","Quantity","Descriptions","Status");
        for (int i = 0; i < indexProduct-1; i++) {
            for (int j = i+1; j <indexProduct ; j++) {
                if (dataProduct[i].getExportPrice()>dataProduct[j].getExportPrice()) {
                    Product tempt = dataProduct[i];
                    dataProduct[i] = dataProduct[j];
                    dataProduct[j] = tempt;
                }
            }
        }
        for (int i = 0; i < indexProduct; i++) {
            dataProduct[i].displayProduct();
        }
    }

    public static void searchProductByName(Scanner scanner){
        System.out.println("Enter Product Name to Search: ");
        String searchProduct = scanner.nextLine();
        System.out.println("******************************Product Information*********************************");
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-15s%-20s%-15s\n","Product Id","Product Name","Title","Import Price","Export Price","Quantity","Descriptions","Status");
        for (int i = 0; i < indexProduct; i++) {
            if (dataProduct[i].getProductName().startsWith(searchProduct)){
                dataProduct[i].displayProduct();
            }
        }
    }

    public static void displayProductOutOfStock(){
        System.out.println("******************************Product Information*********************************");
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-15s%-20s%-15s\n","Product Id","Product Name","Title","Import Price","Export Price","Quantity","Descriptions","Status");
        for (int i = 0; i < indexProduct; i++) {
            if (dataProduct[i].getQuantity()<=5) {
                dataProduct[i].displayProduct();
            }
        }
    }

    public static void changeProductStatus(Scanner scanner) {
        System.out.println("Enter Product ID to change status: ");
        String changeProductStatus = scanner.nextLine();
        boolean checkChangeStatus = false;
        for (int i = 0; i < indexProduct; i++) {
            if (dataProduct[i].getProductId().equals(changeProductStatus)){
                dataProduct[i].setProductStatus(!dataProduct[i].isProductStatus());
                checkChangeStatus = true;
                break;
            } else {
                checkChangeStatus = false;
            }
        }
        if (checkChangeStatus){
            System.out.println("Change Product Status success!!!");
        } else {
            System.err.println("This Product doesn't exist! Please try again");
        }
    }
}
