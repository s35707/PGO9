public abstract class ShipmentOrder implements SummaryPrintable{
    String orderNumber;
    String customerName;
    int distanceKm;
    double baseFee;
    boolean insured;
    double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, int distanceKm, double baseFee, boolean insured){
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

    public String getOrderNumber(){
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
