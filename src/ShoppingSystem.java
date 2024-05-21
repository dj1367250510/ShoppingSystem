import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int number;

    public Product(String name, double price,int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

class Supermarket {
    private ArrayList<Product> products;

    public Supermarket() {
        products = new ArrayList<>();
    }

    public void addProduct(String name, double price,int number) {
        Product product = new Product(name, price, number);
        products.add(product);
        System.out.println("商品添加成功！");
    }

    public void updateProduct(String name, Scanner scanner) {
        Product productToUpdate = null;
        for (Product product : products) {
            if (product.getName().equals(name)) {
                productToUpdate = product;
                break;
            }
        }

        if (productToUpdate != null) {
            System.out.println("请选择要修改的属性：");
            System.out.println("1. 商品名称");
            System.out.println("2. 商品价格");
            System.out.println("3. 商品数量");
            System.out.println("请选择，输入数字或0返回上级菜单");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    return;//返回上一级
                case 1:
                    System.out.print("请输入新的商品名称：");
                    String newName = scanner.nextLine();
                    productToUpdate.setName(newName);
                    System.out.println("商品名称已更新！");
                    break;
                case 2:
                    System.out.print("请输入新的商品价格：");
                    double newPrice = scanner.nextDouble();
                    productToUpdate.setPrice(newPrice);
                    System.out.println("商品价格已更新！");
                    break;
                case 3:
                    System.out.print("请输入新的商品数量：");
                    int newNumber = scanner.nextInt();
                    productToUpdate.setNumber(newNumber);
                    System.out.println("商品数量已更新！");
                    break;
                default:
                    System.out.println("无效的选项！");
            }
        } else {
            System.out.println("找不到该商品！");
        }
    }

    public void deleteProduct(String name) {
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getName().equals(name)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            products.remove(productToRemove);
            System.out.println("商品删除成功！");
        } else {
            System.out.println("找不到该商品！");
        }
    }

    public void displayAllProducts() {
        System.out.println("商品名称 \t\t\t 商品价格 \t\t 商品数量 \t\t 备注");
        for (Product product : products) {
            String name = product.getName();
            double price = product.getPrice();
            int number = product.getNumber();

            String remark = "";
            if (number < 10) {
                remark = "*该商品已不足10件！";
            }

            System.out.printf(name+" \t\t\t "+price+" \t\t\t "+number+" \t\t\t\t "+remark+"\n");

        }
    }

    public Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}

public class ShoppingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Supermarket supermarket = new Supermarket();

        while (true) {
            System.out.println("            商品管理系统：          ");
            System.out.println("*********************************");
            System.out.println("            1. 添加商品            ");
            System.out.println("            2. 更改商品            ");
            System.out.println("            3. 删除商品            ");
            System.out.println("            4. 显示所有商品         ");
            System.out.println("            5. 查询商品            ");
            System.out.println("            6. 退出系统            ");
            System.out.println("*********************************");
            System.out.println("        请选择，输入数字进行操作：     ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    do {
                        System.out.print("请输入商品名称：");
                        String name = scanner.nextLine();
                        System.out.print("请输入商品价格：");
                        double price = scanner.nextDouble();
                        System.out.print("请输入商品数量：");
                        int stock = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        supermarket.addProduct(name, price, stock);

                        System.out.print("是否继续添加商品？（Y/N）: ");
                        String continueInput = scanner.nextLine();
                        if (!continueInput.equalsIgnoreCase("Y")) {
                            break;
                        }
                    } while (true);
                    break;
                case 2:
                    System.out.print("请输入要修改的商品名称：");
                    String productToUpdate = scanner.nextLine();
                    supermarket.updateProduct(productToUpdate, scanner);
                    break;
                case 3:
                    System.out.print("请输入要删除的商品名称：");
                    String productToDelete = scanner.nextLine();
                    supermarket.deleteProduct(productToDelete);
                    break;
                case 4:
                    supermarket.displayAllProducts();
                    break;
                case 5:
                    System.out.print("请输入要查询的商品名称：");
                    String productToFind = scanner.nextLine();
                    Product foundProduct = supermarket.findProduct(productToFind);
                    if (foundProduct != null) {
                        System.out.println("商品名称：" + foundProduct.getName());
                        System.out.println("商品价格：" + foundProduct.getPrice());
                        System.out.println("商品数量：" + foundProduct.getNumber());
                    } else {
                        System.out.println("找不到该商品！");
                    }
                    break;
                case 6:
                    System.out.println("感谢使用，再见！");
                    return;
                default:
                    System.out.println("无效的选项，请重新输入。");
            }
        }
    }
}
