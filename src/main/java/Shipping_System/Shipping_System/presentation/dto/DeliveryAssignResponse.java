package Shipping_System.Shipping_System.presentation.dto;

import java.time.LocalDateTime;

public class DeliveryAssignResponse {
    private Long id;
    private Long packageId;
    private Long deliveryPersonId;
    private LocalDateTime assignedAt;

    public DeliveryAssignResponse(Long id, Long packageId, Long deliveryPersonId, LocalDateTime assignedAt) {
        this.id = id;
        this.packageId = packageId;
        this.deliveryPersonId = deliveryPersonId;
        this.assignedAt = assignedAt;
    }

    // Getters
    public Long getId() { return id; }
    public Long getPackageId() { return packageId; }
    public Long getDeliveryPersonId() { return deliveryPersonId; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
}
