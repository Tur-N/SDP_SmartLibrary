package com.smartlibrary;

public class LegacyLibrarySystem {

    public LegacyStatus process(
            int memberNumber,
            long legacyBookNumber,
            String command) {

        if (memberNumber <= 0) {
            return new LegacyStatus(
                    false,
                    401,
                    "Invalid member number"
            );
        }

        if (legacyBookNumber <= 0) {
            return new LegacyStatus(
                    false,
                    404,
                    "Legacy book not found"
            );
        }

        if (!command.equalsIgnoreCase("borrow")
                && !command.equalsIgnoreCase("reserve")) {

            return new LegacyStatus(
                    false,
                    400,
                    "Unsupported command"
            );
        }

        return new LegacyStatus(
                true,
                200,
                "Legacy system completed " + command
        );
    }
}