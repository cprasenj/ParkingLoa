public class Car {
    private final String number;
    private boolean isParked = false;

    public Car(String carNumber) {
        this.number = carNumber;
    }

    public String giveIdentity() {
        return this.number;
    }
}

