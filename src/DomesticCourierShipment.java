public class DomesticCourierShipment extends ShipmentOrder{
    double packageWeightKg;
    boolean weekendDelivery;

    public DomesticCourierShipment(int number, String name, int distance, double fee, boolean isInsured, double weight, boolean weekend){
        super(number, name, distance, fee, isInsured);
        packageWeightKg = weight;
        weekendDelivery = weekend;
    }

    public double getPackageWeightKg(){
        return this.packageWeightKg;
    }
    public boolean isWeekendDelivery(){
        return this.weekendDelivery;
    }


    @Override
    public String getShipmentType() {
        return "Domestic courier";
    }

    @Override
    protected double calculateBasePrice() {
        return this.baseFee + distanceKm * 1.20;
    }

    @Override
    protected double calculateAdditionalFee() {
        double weekendFee = 0;
        if(this.weekendDelivery){
            weekendFee = 25;
        }
        return this.packageWeightKg * 1.20 + weekendFee;
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        if(this.distanceKm >= 300.0){
            return price * 0.95;
        }
        return price;
    }
}
