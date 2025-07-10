package Shipping_System.Shipping_System.data.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_assignments")
public class DeliveryAssignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private PackageEntity aPackage;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private UserModel deliveryPerson;

    private LocalDateTime assignedAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PackageEntity getaPackage() { return aPackage; }
    public void setaPackage(PackageEntity aPackage) { this.aPackage = aPackage; }

    public UserModel getDeliveryPerson() { return deliveryPerson; }
    public void setDeliveryPerson(UserModel deliveryPerson) { this.deliveryPerson = deliveryPerson; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
}
