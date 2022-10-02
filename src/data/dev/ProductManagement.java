package data.dev;

import java.util.Scanner;

public class ProductManagement {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("********************QUẢN LÝ CỬA HÀNG*****************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Your choice is: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    Menu.menuCatalogManagement();
                    break;
                case 2:
                    Menu.menuProductManagement();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Please choose 1-3");
            }
        } while (true);
    }
}
