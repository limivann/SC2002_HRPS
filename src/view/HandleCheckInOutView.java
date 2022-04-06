package src.view;

import java.lang.Thread;
import java.util.ArrayList;

import javax.naming.spi.ResolveResult;

import src.controller.GuestManager;
import src.controller.OrderManager;
import src.controller.PaymentManager;
import src.controller.ReservationManager;
import src.controller.RoomManager;
import src.helper.Helper;
/**
 * The Class checks in and checks out a reservation.
 * @author Lim Kang Wei
 * @version 1.0
 * @since 2020-03-29
 */
public class HandleCheckInOutView extends MainView {
    public HandleCheckInOutView() {
        super();
    }
    /**
     * View Menu for Check In and Check Out
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Check In / Check Out View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Check In Room");
        System.out.println("(2) Check Out Room");
        System.out.println("(3) Exit Check In / Check Out View");
    }
    /**
     * View Application for Check In and Check Out
     */
    @Override
    public void viewapp() {  
        int opt = -1;
        do{
            printMenu();
            opt = Helper.readInt(1,3);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Check In / Check Out View > Check In Room");
                    checkin();
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Check In / Check Out View > Check Out Room");
                    checkout();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice ... Please try again");
                    break;
            }
            if (opt != 3) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 3);
        
    }
    /**
     * View for Check In reservation
     * see {@link ReservationManager} for more reservation management details.
     */
    public void checkin() {
        ReservationManager.checkReservationStatus();
        System.out.println("Please enter reservation id (RXXXX)");
        String reservationId = Helper.sc.nextLine();
        if (!ReservationManager.validateReservationId(reservationId)) {
            return;
        }
        if (ReservationManager.checkInReservation(reservationId)) {
            System.out.println(String.format("Check in complete for reservation id: %s", reservationId));
        }
    }
    /**
     * View for Check Out reservation
     * see {@link ReservationManager} for more reservation management details. 
     */
    public void checkout() {
        System.out.println("Please enter reservation id (RXXXX)");
        String reservationId = Helper.sc.nextLine();
        if (!ReservationManager.validateReservationId(reservationId)) {
            return;
        }
        if (ReservationManager.checkOutReservation(reservationId)) {
            System.out.println(String.format("Check out complete for reservation id: %s", reservationId));
        } else {
            return;
        }
        String paymentOptStr = promptPayment() == 1 ? "Cash" : "Credit Card";
        System.out.println("You have chosen to pay by " + paymentOptStr);

        ArrayList<String> guestsToPay = ReservationManager.search(reservationId).getGuestIds();
        System.out.println("Please select which guest to make the payment ");
        for (int i = 1; i <= guestsToPay.size(); i++) {
            System.out.println(String.format("(%d) %s: %s", i, guestsToPay.get(i - 1),
                    GuestManager.searchGuestById(guestsToPay.get(i - 1)).get(0).getName()));
        }
        if (guestsToPay.size() == 0) {
            System.out.println("This reservation has no guest");
            return;
        }
        int opt = Helper.readInt(1, guestsToPay.size());

        PaymentManager.handlePayment(reservationId, guestsToPay.get(opt-1));

        //  remove all order details of that room
        RoomManager.updateRoomOrders(ReservationManager.getRoomIdFromReservationId(reservationId), null, true);

        // in orders, update all orders made by that room to be delivered
        OrderManager.updateAllRoomOrderToDelivered(ReservationManager.getRoomIdFromReservationId(reservationId));
    }
    /**
     * Prompt Payment option 
     * @return choice of payment method 
     */
    public int promptPayment() {
        System.out.println("Please select a payment method (1-2)");
        System.out.println("(1) Cash");
        System.out.println("(2) Credit Card");
        int opt = Helper.sc.nextInt();
        return opt;
    }
}
