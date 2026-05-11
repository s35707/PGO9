import java.util.Objects;

public class PickupPointShipment extends ShipmentOrder{
    String lockerSize;
    boolean fragile;
    public PickupPointShipment(int orderNumber, String customerName, int distanceKm, double baseFee, boolean insured, String lockerSize, boolean fragile){
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.lockerSize = lockerSize;
        this.fragile = fragile;
    }


    @Override
    protected void validateSpecificRules() {
        if(!Objects.equals(this.lockerSize, "S")&&!Objects.equals(this.lockerSize, "M")&&!Objects.equals(this.lockerSize, "L")){
            throw new IllegalArgumentException("lockerSize must be equal S,M or L!");
        }
    }

    @Override
    protected double calculateBasePrice() {
        return this.baseFee + this.distanceKm * 0.75;
    }

    @Override
    protected double calculateAdditionalFee() {
        double fee = 5;
        if(Objects.equals(this.lockerSize, "M")){
            fee += 5;
        }
        else if (Objects.equals(this.lockerSize, "L")) {
            fee += 13;
        }
        if(fragile){
            fee += 12;
        }
        return fee;
    }

    @Override
    public String getShipmentType() {
        return "Pickup point";
    }
}
