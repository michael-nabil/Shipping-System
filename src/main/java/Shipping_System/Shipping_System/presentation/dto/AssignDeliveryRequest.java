package Shipping_System.Shipping_System.presentation.dto;

public class AssignDeliveryRequest {
    private Long packageId;
    private Long deliveryPersonId;

    // Getters and setters
    public Long getPackageId() { return packageId; }
    public void setPackageId(Long packageId) { this.packageId = packageId; }

    public Long getDeliveryPersonId() { return deliveryPersonId; }
    public void setDeliveryPersonId(Long deliveryPersonId) { this.deliveryPersonId = deliveryPersonId; }
}
