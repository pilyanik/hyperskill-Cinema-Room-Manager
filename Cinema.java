import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of rows and seats
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();

        // Create the seating arrangement
        char[][] seats = new char[numRows][numSeats];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numSeats; j++) {
                seats[i][j] = 'S';  // Initialize all seats as empty
            }
        }

        boolean exit = false;
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = calculateTotalIncome(numRows, numSeats);
        int totalNumSeats = numRows * numSeats;

        while (!exit) {
            // Print the menu
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showSeats(seats);
                    break;
                case 2:
                    int ticketPrice = buyTicket(seats);
                    if (ticketPrice > 0) {
                        purchasedTickets++;
                        currentIncome += ticketPrice;
                    }
                    break;
                case 3:
                    printStatistics(purchasedTickets, currentIncome, totalIncome, totalNumSeats);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void showSeats(char[][] seats) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int buyTicket(char[][] seats) {
        Scanner scanner = new Scanner(System.in);

        int numRows = seats.length;
        int numSeats = seats[0].length;

        while (true) {
            System.out.println("\nEnter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row < 1 || row > numRows || seat < 1 || seat > numSeats) {
                System.out.println("Wrong input!");
            } else if (seats[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                // Calculate the ticket price (you can define your own logic here)
                int ticketPrice = calculateTicketPrice(numRows, numSeats, row);

                System.out.println("Ticket price: $" + ticketPrice);
                seats[row - 1][seat - 1] = 'B';  // Mark the seat as taken
                return ticketPrice;
            }
        }
    }
    public static void printStatistics(int purchasedTickets, int currentIncome, int totalIncome, int totalNumSeats) {
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        //double percentage = (double) purchasedTickets / (totalIncome == 0 ? 1 : totalIncome) * 100;
        double percentage = (double) purchasedTickets * 100 / totalNumSeats;

        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }


    public static int calculateTicketPrice(int numRows, int numSeats, int row) {
        int totalSeats = numRows * numSeats;

        if (totalSeats <= 60) {
            return 10;
        } else {
            int frontHalf = numRows / 2;
            if (row <= frontHalf) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    public static int calculateTotalIncome(int numRows, int numSeats) {
        int totalSeats = numRows * numSeats;

        if (totalSeats <= 60) {
            return totalSeats * 10;
        } else {
            int frontHalf = numRows / 2;
            int backHalf = numRows - frontHalf;
            return (frontHalf * numSeats * 10) + (backHalf * numSeats * 8);
        }
    }
}


