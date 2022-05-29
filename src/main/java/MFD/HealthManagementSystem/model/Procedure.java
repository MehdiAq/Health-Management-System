package MFD.HealthManagementSystem.model;

public enum Procedure {

    CHECK_UP("Regular Check-Up"),
    BIOPSY("Biopsy"),
    JOINT_REPLACEMENT("Joint Replacement"),
    HYSTERECTOMY("Hysterectomy"),
    EYE_SURGERY("Eye Surgery"),
    HEART_SURGERY("Heart Surgery");

    private final String displayName;

    Procedure(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName; }

    @Override public String toString() {
        return displayName; }
}