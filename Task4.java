import java.util.*;

class Room {
    int number;
    String category;
    boolean isAvailable;
    double price;

    public Room(int number, String category, double price) {
        this.number = number;
        this.category = category;
        this.isAvailable = true;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room Number: " + number + ", Category: " + category + ", Price: $" + price + ", Available: "
                + isAvailable;
    }
}

class Booking {
    String guestName;
    Room room;
    int nights;
    double totalAmount;

    public Booking(String guestName, Room room, int nights) {
        this.guestName = guestName;
        this.room = room;
        this.nights = nights;
        this.totalAmount = room.price * nights;
        room.isAvailable = false;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + "\nRoom Number: " + room.number + "\nCategory: " + room.category +
                "\nNights: " + nights + "\nTotal Amount: $" + totalAmount;
    }
}

public class Task4 {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    processPayment();
                    break;
                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Double", 150));
        rooms.add(new Room(103, "Suite", 250));
        rooms.add(new Room(104, "Single", 100));
        rooms.add(new Room(105, "Suite", 250));
    }

    private static void showMenu() {
        System.out.println("\n--- Hotel Reservation System ---");
        System.out.println("1. Search for Available Rooms");
        System.out.println("2. Make a Reservation");
        System.out.println("3. View Booking Details");
        System.out.println("4. Process Payment");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void searchRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation() {
        System.out.print("\nEnter your name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.number == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            Booking booking = new Booking(guestName, selectedRoom, nights);
            bookings.add(booking);
            System.out.println("\nReservation successful!");
            System.out.println(booking);
        } else {
            System.out.println("\nRoom is either unavailable or does not exist.");
        }
    }

    private static void viewBookingDetails() {
        System.out.print("\nEnter your name to view booking: ");
        String guestName = scanner.nextLine();

        for (Booking booking : bookings) {
            if (booking.guestName.equalsIgnoreCase(guestName)) {
                System.out.println("\nBooking Details:");
                System.out.println(booking);
                return;
            }
        }

        System.out.println("\nNo booking found under this name.");
    }

    private static void processPayment() {
        System.out.print("\nEnter your name to process payment: ");
        String guestName = scanner.nextLine();

        for (Booking booking : bookings) {
            if (booking.guestName.equalsIgnoreCase(guestName)) {
                System.out.println("\nBooking Details:");
                System.out.println(booking);
                System.out.print("Confirm payment of $" + booking.totalAmount + "? (yes/no): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    // In a real system, payment processing logic would go here
                    System.out.println("Payment successful! Thank you for your payment.");
                } else {
                    System.out.println("Payment cancelled.");
                }
                return;
            }
        }

        System.out.println("\nNo booking found under this name.");
    }
}