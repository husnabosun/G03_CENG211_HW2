package enums;

public enum RejectionReason {
    NONE(0, "No rejection reason has been set yet."),
    MISSING_ENROLLMENT_CERTIFICATE(1, "Missing Enrollment Certificate"),
    MISSING_TRANSCRIPT(2, "Missing Transcript"),
    GPA_BELOW_2_5(3, "GPA below 2.5"),
    MISSING_MANDATORY_DOCUMENT(4, "Missing mandatory document"),
    FINANCIAL_STATUS_UNSTABLE(5, "Financial status unstable"),
    MISSING_PUBLICATION_OR_PROPOSAL(6, "Missing publication or proposal"),
    PUBLICATION_IMPACT_TOO_LOW(7, "Publication impact too low"),
    GPA_BELOW_3_0(8, "GPA below 3.0");

    private final int priority;
    private final String message;

    RejectionReason(int priority, String message) {
        this.priority = priority;
        this.message = message;
    }

    public int getPriority() {
        return priority;
    }

    public String getMessage() {
        return message;
    }
}
