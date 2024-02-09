package app.prod.enumeration;

import app.prod.exception.NoSuchTransactionTypeException;

public enum TransactionType {
    INCOME(1L,"Income"),
    EXPENSE(2L,"Expense");

    private final Long id;
    private final String name;

    TransactionType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    /**
     * Optional: A method to find an enum value by its ID.
     * Useful when you need to convert from database IDs back to enum values.
     *
     * @param id The ID of the TransactionType.
     * @return The matching TransactionType or null.
     */
    public static TransactionType findById(Long id) throws NoSuchTransactionTypeException {
        for (TransactionType type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        throw new NoSuchTransactionTypeException("Transaction type not defined.");
    }
}
