package Shipping_System.Shipping_System.domin.enitiy;

import java.time.LocalDateTime;

public class DeliveryAssign {
    private Long id;
    private Long packageId;
    private Long deliveryPersonId;
    private LocalDateTime assignedAt;

    // Constructors, Getters, Setters
    public DeliveryAssign(Long id, Long packageId, Long deliveryPersonId, LocalDateTime assignedAt) {
        this.id = id;
        this.packageId = packageId;
        this.deliveryPersonId = deliveryPersonId;
        this.assignedAt = assignedAt;
    }

    public Long getId() { return id; }
    public Long getPackageId() { return packageId; }
    public Long getDeliveryPersonId() { return deliveryPersonId; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
}
