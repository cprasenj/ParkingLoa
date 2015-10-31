import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void testIsSlotAvailableGivesTrueIfParkingIsAvailable() throws Exception {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(5, owner);
        assertTrue(parkingLot.isAvailable());
    }

    @Test
    public void testGetSlotShouldAllotAslotForTheCarIfItIsAvailable() throws Exception {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        assertNotNull(parkingLot.assignSlot("12345"));
        assertFalse(parkingLot.isAvailable());
    }

    @Test(expected= CarAlreadyParked.class)
    public void testItShouldNotAllowTheSameCarToParkTwice() throws Exception {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        assertNotNull(parkingLot.assignSlot("12345"));
        parkingLot.assignSlot("12345");
        assertTrue(parkingLot.isAvailable());
    }

    @Test
    public void testItShouldNotAllowParkIfSpaceIsavilable() throws Exception {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        assertNotNull(parkingLot.assignSlot("12345"));
        assertNotNull(parkingLot.assignSlot("12346"));
        assertFalse(parkingLot.isAvailable());
    }

    @Test(expected= ParkingFull.class)
    public void testItShouldNotAllowParkIfSpaceIsavilableButShouldThrowErrorIfNoSpaceButStillWantToPark() throws Exception {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        assertNotNull(parkingLot.assignSlot("12345"));
        assertFalse(parkingLot.isAvailable());
        parkingLot.assignSlot("123456");
    }

    @Test
    public void testUnassignSlotShouldFreeTheSlotIfTheCarIsParked() throws Exception {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Token parkingToken = parkingLot.assignSlot("12345");
        assertNotNull(parkingToken);
        assertFalse(parkingLot.isAvailable());
        assertEquals(parkingLot.unassignedSlot(parkingToken), "12345");
        assertTrue(parkingLot.isAvailable());
    }

    @Test(expected= InvalidToken.class)
    public void testUnassignSlotShouldThrowCarNotFoundIfCarIsNotParked() throws Exception {
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        parkingLot.unassignedSlot(new Token());
    }

    @Test
    public void testWillCallParkingLotOwnerNotifyWhenThereIsNoSlotAvailable() throws Exception {

        class ParkingLotOwner extends Owner {
            public boolean notifyParkingFull = false;
            public void setNotifyParkingFull() {
                this.notifyParkingFull = true;
            }
        }

        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        parkingLot.assignSlot("12345");
        assertTrue(owner.notifyParkingFull);

    }
}