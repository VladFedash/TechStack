package enums;

public enum WaitTimes {
    SHORT(3),
    MIDDLE(7),
    LONG(15);

    private final int waitTime;

    WaitTimes(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getValue() {
        return waitTime;
    }
}
