package com.smartlibrary;

public class ReserveBook extends LibraryOperation {

    public ReserveBook(LibraryBackend backend) {
        super(backend);
    }

    @Override
    public OperationResult execute(String memberId, String bookId) {
        LibraryRequest request =
                request(
                        LibraryRequest.ActionType.RESERVE,
                        memberId,
                        bookId
                );

        return backend.execute(request);
    }
}