package src.view;

import src.helper.Helper;

import src.model.enums.RoomType;
import src.controller.PromotionManager;
public class ManagePaymentView extends MainView {
    
    public ManagePaymentView() {
        super();
    }

    @Override
    public void printMenu() {
        System.out.println("=== Manage Payment View ===");
        System.out.println("Please select an option (1-4)");
        System.out.println("(1) Manage Room Price");
        System.out.println("(2) Manage Tax Rate");
        System.out.println("(3) Manage Discount Rate");
        System.out.println("(4) Exit Manage Payment View");
    }

    @Override
    public void viewapp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.sc.nextInt();
            switch (opt) {
                case 1:
                    if (promptEditRoomPrice()) {
                        System.out.println("Edit Room Price Successful!");
                    } else {
                        System.out.println("Edit Room Price Unsuccessful!");
                    }
                    break;
                case 2:
                    if (promptEditTaxRate()) {
                        System.out.println("Edit Tax Rate Successful!");
                    } else {
                        System.out.println("Edit Tax Rate Unsuccessful!");
                    }
                    break;
                case 3:
                    if (promptEditDiscountRate()) {
                        System.out.println("Edit Discount Rate Successful!");
                    } else {
                        System.out.println("Edit Discount Rate Unsuccessful!");
                    }
                    break;
                case 4:
                    break;
            }
        } while (opt != 4);

    }
    
    public boolean promptEditRoomPrice() {
        int opt = -1;
        printEditRoomPriceMenu();
        opt = Helper.sc.nextInt();
        double newPrice = -1;
        switch (opt) {
            case 1:
                printOldPrice(RoomType.SINGLE);
                newPrice = promptNewPrice(RoomType.SINGLE);
                return PromotionManager.editRoomPrice(RoomType.SINGLE, newPrice);
            case 2:
                printOldPrice(RoomType.DOUBLE);
                newPrice = promptNewPrice(RoomType.DOUBLE);
                return PromotionManager.editRoomPrice(RoomType.DOUBLE, newPrice);
            case 3:
                printOldPrice(RoomType.DELUXE);
                newPrice = promptNewPrice(RoomType.DELUXE);
                return PromotionManager.editRoomPrice(RoomType.DELUXE, newPrice);
            case 4:
                printOldPrice(RoomType.VIP_SUITE);
                newPrice = promptNewPrice(RoomType.VIP_SUITE);
                return PromotionManager.editRoomPrice(RoomType.VIP_SUITE, newPrice);
            case 5:
                return false;
        }
        return false;
    }

    public boolean promptEditTaxRate() {
        int opt = -1;
        printEditRoomTaxRateMenu();
        opt = Helper.sc.nextInt();
        double newTaxRate = -1;
        switch (opt) {
            case 1:
                printOldTaxRate(RoomType.SINGLE);
                newTaxRate = promptNewTaxRate(RoomType.SINGLE);
                return PromotionManager.editTaxRate(RoomType.SINGLE, newTaxRate);
            case 2:
                printOldTaxRate(RoomType.DOUBLE);
                newTaxRate = promptNewTaxRate(RoomType.DOUBLE);
                return PromotionManager.editTaxRate(RoomType.DOUBLE, newTaxRate);
            case 3:
                printOldTaxRate(RoomType.DELUXE);
                newTaxRate = promptNewTaxRate(RoomType.DELUXE);
                return PromotionManager.editTaxRate(RoomType.DELUXE, newTaxRate);
            case 4:
                printOldTaxRate(RoomType.VIP_SUITE);
                newTaxRate = promptNewTaxRate(RoomType.VIP_SUITE);
                return PromotionManager.editTaxRate(RoomType.VIP_SUITE, newTaxRate);
        }
        return false;
    }

    public boolean promptEditDiscountRate() {
        double newDiscountRate = -1;
        System.out.println("Please enter a new discount rate");
        newDiscountRate = Helper.sc.nextDouble();
        return PromotionManager.editDiscountRate(newDiscountRate);
    }

    public double promptNewPrice(RoomType roomType) {
        System.out.println("Please enter a new price for " + roomType.roomTypeAsStr);
        double newPrice = -1;
        newPrice = Helper.sc.nextDouble();
        return newPrice;
    }

    public double promptNewTaxRate(RoomType roomType) {
        System.out.println("Please enter a new tax rate for " + roomType.roomTypeAsStr);
        double newTaxRate = -1;
        newTaxRate = Helper.sc.nextDouble();
        return newTaxRate;
    }
    
    

    // Print old prices / taxes / discount rates
    public void printOldPrice(RoomType roomType) {
        System.out.println(String.format("The old price of %s is %.2f", roomType.roomTypeAsStr,
                PromotionManager.getRoomPrice(roomType)));
    }
    
    public void printOldTaxRate(RoomType roomType) {
        System.out.println(String.format("The old tax rate of %s is %.2f", roomType.roomTypeAsStr,
                PromotionManager.getRoomTaxRate(roomType)));
    }

    public void printOldDiscountRate() {
        System.out.println(String.format("The old discount rate is %.2f", PromotionManager.getDiscountRate()));
    }


    

    public void printEditRoomPriceMenu() {
        System.out.println("--- Manage Room Price ---");
        System.out.println("Please select a room to edit its price (1-5)");
        System.out.println("(1) Single Room");
        System.out.println("(2) Double Room");
        System.out.println("(3) Deluxe Room");
        System.out.println("(4) Vip Suite");
        System.out.println("(5) Back");
    }

    public void printEditRoomTaxRateMenu() {
        System.out.println("--- Manage Tax Rate ---");
        System.out.println("Please select a room to edit its tax rate (1-5)");
        System.out.println("(1) Single Room");
        System.out.println("(2) Double Room");
        System.out.println("(3) Deluxe Room");
        System.out.println("(4) Vip Suite");
        System.out.println("(5) Back");
    }

}