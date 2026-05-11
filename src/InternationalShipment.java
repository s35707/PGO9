import java.util.Objects;

public class InternationalShipment extends ShipmentOrder{
    String destinationCountry;
    boolean customsDocumentsRequired;
    boolean expressDelivery;
    public InternationalShipment(String orderNumber, String customerName, int distanceKm, double baseFee, boolean insured, String destinationCountry, boolean customsDocumentsRequired, boolean expressDelivery){
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.destinationCountry = destinationCountry;
        this.customsDocumentsRequired = customsDocumentsRequired;
        this.expressDelivery = expressDelivery;
    }

    @Override
    public String getShipmentType() {
        return "International";
    }

    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 2.10;
    }

    @Override
    protected double calculateAdditionalFee() {
        double fee = 0;
        if(this.customsDocumentsRequired){
            fee += 45;
        }
        if(this.expressDelivery){
            fee += 80;
        }
        return fee;
    }

    @Override
    protected void validateSpecificRules() {
        if(Objects.equals(this.destinationCountry, "")){
            throw new IllegalArgumentException("Must provide a destination country!");
        }
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        if (!this.expressDelivery&&this.distanceKm > 1000){
            return price * 0.97;
        }
        return price;
    }
}
