package enums;

public enum WaitTimes {
    SHORT(5),
    MIDDLE(10),
    LONG(15);

    private final int waitTime;

    WaitTimes(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getValue() {
        return waitTime;
    }
}
