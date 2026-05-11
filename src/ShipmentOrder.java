public abstract class ShipmentOrder implements SummaryPrintable{
    int orderNumber;
    String customerName;
    int distanceKm;
    double baseFee;
    boolean insured;
    double lastCalculatedPrice;

    public ShipmentOrder(int number, String name, int distance, double fee, boolean isInsured){
        orderNumber = number;
        customerName = name;
        distanceKm = distance;
        baseFee = fee;
        insured = isInsured;
    }

    public int getOrderNumber(){
        return this.orderNumber;
    }
    public String getCustomerName(){
        return this.customerName;
    }
    public int getDistanceKm(){
        return this.distanceKm;
    }
    public double getBaseFee(){
        return this.baseFee;
    }
    public boolean isInsured(){
        return this.insured;
    }
    public double getLastCalculatedPrice(){
        return this.lastCalculatedPrice;
    }

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder(){
        boolean isInvalid = this.distanceKm < 0;
        if(isInvalid){
            System.out.println("Order is invalid");
        }
    }

    protected void validateSpecificRules(){
    }
    private double applyInsurance(double price){
        if(this.insured){
            return price * 1.07;
        }
        return price;
    }
    protected double applyBusinessDiscount(double price){
        return price;
    }

    private void printProcessingResult(){
        System.out.println("Calculated price: "+this.lastCalculatedPrice);
    }

    public String buildSummaryLine(){
        return "Order number: "+this.orderNumber+", Customer: "+this.customerName+", Type: "+this.getShipmentType()+", price "+this.lastCalculatedPrice;
    }

    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();

}
