package com.smartlibrary;

public record LibraryRequest(
        ActionType action,
        String memberId,
        String bookId
) {
    public enum ActionType {
        BORROW,
        RESERVE
    }
}