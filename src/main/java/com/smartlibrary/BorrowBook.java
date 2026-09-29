package com.smartlibrary;

public class BorrowBook extends LibraryOperation {

    public BorrowBook(LibraryBackend backend) {
        super(backend);
    }

    @Override
    public OperationResult execute(String memberId, String bookId) {
        LibraryRequest request =
                request(
                        LibraryRequest.ActionType.BORROW,
                        memberId,
                        bookId
                );

        return backend.execute(request);
    }
}