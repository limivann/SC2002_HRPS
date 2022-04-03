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
        Helper.clearScreen();
        printBreadCrumbs("Admin View > Payment View");
        System.out.println("Please select an option (1-4)");
        System.out.println("(1) Manage Room Price");
        System.out.println("(2) Manage Tax Rate");
        System.out.println("(3) Manage Discount Rate");
        System.out.println("(4) Exit Payment View");
    }

    @Override
    public void viewapp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.readInt(1, 4);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Admin View > Payment View > Manage Room Price");
                    if (promptEditRoomPrice()) {
                        System.out.println("Edit Room Price Successful!");
                    } else {
                        System.out.println("Edit Room Price Unsuccessful!");
                    }
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Admin View > Payment View > Manage Tax Rate");
                    if (promptEditTaxRate()) {
                        System.out.println("Edit Tax Rate Successful!");
                    } else {
                        System.out.println("Edit Tax Rate Unsuccessful!");
                    }
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Admin View > Payment View > Manage Discount Rate");
                    if (promptEditDiscountRate()) {
                        System.out.println("Edit Discount Rate Successful!");
                    } else {
                        System.out.println("Edit Discount Rate Unsuccessful!");
                    }
                    break;
                case 4:
                    break;
            }
            if (opt != 4) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 4);

    }
    
    public boolean promptEditRoomPrice() {
        int opt = -1;
        printEditRoomPriceMenu();
        opt = Helper.readInt(1, 4);
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
        }
        return false;
    }

    public boolean promptEditTaxRate() {
        printOldTaxRate();
        double newTaxRate = -1;
        System.out.println("Please enter a new tax rate (0-1)");
        newTaxRate = Helper.readDouble();
        return PromotionManager.editTaxRate(newTaxRate);
    }

    public boolean promptEditDiscountRate() {
        printOldDiscountRate();
        double newDiscountRate = -1;
        System.out.println("Please enter a new discount rate (0-1)");
        newDiscountRate = Helper.readDouble();
        return PromotionManager.editDiscountRate(newDiscountRate);
    }

    public double promptNewPrice(RoomType roomType) {
        System.out.println("Please enter a new price for " + roomType.roomTypeAsStr);
        double newPrice = -1;
        newPrice = Helper.readDouble();
        return newPrice;
    }

    public double promptNewTaxRate(RoomType roomType) {
        System.out.println("Please enter a new tax rate for " + roomType.roomTypeAsStr);
        double newTaxRate = -1;
        newTaxRate = Helper.readDouble();
        return newTaxRate;
    }
    
    

    // Print old prices / taxes / discount rates
    public void printOldPrice(RoomType roomType) {
        System.out.println(String.format("The old price of %s is %.2f", roomType.roomTypeAsStr,
                PromotionManager.getRoomPrice(roomType, false)));
    }
    
    public void printOldTaxRate() {
        System.out.println(String.format("The old tax rate is %.2f", PromotionManager.getTaxRate()));
    }

    public void printOldDiscountRate() {
        System.out.println(String.format("The old discount rate is %.2f", PromotionManager.getDiscountRate()));
    }


    

    public void printEditRoomPriceMenu() {
        System.out.println("Please select a room to edit its price (1-4)");
        System.out.println("(1) Single Room");
        System.out.println("(2) Double Room");
        System.out.println("(3) Deluxe Room");
        System.out.println("(4) Vip Suite");
    }
}
