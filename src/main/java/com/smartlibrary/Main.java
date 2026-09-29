package com.smartlibrary;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Smart Library"
        );

        System.out.println(
                "Format: <borrow|reserve> <memberId> <bookId> <backend>"
        );

        System.out.println(
                "Example: borrow M101 B1001 cloud"
        );

        System.out.print("> ");

        String input = scanner.nextLine();

        String[] parts = input.trim().split("\\s+");

        if (parts.length != 4) {
            System.out.println("Invalid input.");
            return;
        }

        String actionKey = parts[0];
        String memberId = parts[1];
        String bookId = parts[2];
        String backendKey = parts[3];

        try {
            LibraryBackend backend =
                    BackendSelector.select(backendKey);

            LibraryOperation operation =
                    ActionSelector.select(
                            actionKey,
                            backend
                    );

            OperationResult result =
                    operation.execute(
                            memberId,
                            bookId
                    );

            System.out.println(
                    result.message()
            );

        } catch (LibraryOperationException |
                 IllegalArgumentException e) {

            System.out.println(
                    "ERROR: " + e.getMessage()
            );
        }
    }
}