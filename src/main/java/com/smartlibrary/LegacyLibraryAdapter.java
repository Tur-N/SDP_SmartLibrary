package com.smartlibrary;

public class LegacyLibraryAdapter implements LibraryBackend {

    private final LegacyLibrarySystem legacySystem;

    public LegacyLibraryAdapter(LegacyLibrarySystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public OperationResult execute(LibraryRequest request) {

        int memberNumber = parseMemberId(request.memberId());
        long bookNumber = parseBookId(request.bookId());

        String command =
                request.action().name().toLowerCase();

        LegacyStatus status =
                legacySystem.process(
                        memberNumber,
                        bookNumber,
                        command
                );

        if (!status.isSuccess()) {
            throw translateFailure(
                    status.getCode(),
                    status.getMessage()
            );
        }

        return OperationResult.success(status.getMessage());
    }

    private int parseMemberId(String memberId) {
        try {
            return Integer.parseInt(
                    memberId.replaceAll("\\D", "")
            );
        } catch (Exception e) {
            throw new LibraryOperationException(
                    "Invalid member ID"
            );
        }
    }

    private long parseBookId(String bookId) {
        try {
            return Long.parseLong(
                    bookId.replaceAll("\\D", "")
            );
        } catch (Exception e) {
            throw new LibraryOperationException(
                    "Invalid book ID"
            );
        }
    }

    private LibraryOperationException translateFailure(
            int code,
            String message) {

        return switch (code) {
            case 400 ->
                    new LibraryOperationException(
                            "Invalid library operation"
                    );

            case 401 ->
                    new LibraryOperationException(
                            "Member is invalid"
                    );

            case 404 ->
                    new LibraryOperationException(
                            "Book is not available"
                    );

            default ->
                    new LibraryOperationException(
                            "Library backend operation failed"
                    );
        };
    }
}