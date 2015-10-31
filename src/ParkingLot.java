import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParkingLot {

    private final int capacity;
    private final Owner owner;

    private Map<Integer, String> slots = new HashMap<>();
    private Map<Token, Integer> tokenizedSlots = new HashMap<>();

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.initiateSlot();
        this.owner = owner;
    }

    private void initiateSlot() {
        for(int i = 1; i <= capacity; i++) {
            this.slots.put(i, null);
        }
    }

    public boolean isAvailable() {
        return isCarParked(null);
    }

    public Token assignSlot(String number) throws ParkingFull, CarAlreadyParked {
        Set<Integer> slots = this.slots.keySet();
        Integer slot;
        if(isCarParked(number)) {
            throw new CarAlreadyParked();
        }
        slot = findSlots(number, slots);
        if (slot == null) {
            throw new ParkingFull();
        }
        Token parkingToken = new Token();
        tokenizedSlots.put(parkingToken, slot);
        if(tokenizedSlots.size() == this.capacity) {
            owner.setNotifyParkingFull();
        }
        return parkingToken;
    }

    private boolean isCarParked(String number) {
        return this.slots.containsValue(number);
    }

    private Integer findSlots(String number, Set<Integer> slots) {
        for (Integer slot : slots) {
            if(this.slots.get(slot) == null) {
                this.slots.put(slot, number);
                return  slot;
            }
        }
        return null;
    }

    public String unassignedSlot(Token parkingToken) throws InvalidToken {
        Integer slot = tokenizedSlots.get(parkingToken);
        if(slot == null) {
            throw new InvalidToken();
        }
        String carNumber = this.slots.get(slot);
        this.slots.put(slot, null);
        return carNumber;
    }
}
