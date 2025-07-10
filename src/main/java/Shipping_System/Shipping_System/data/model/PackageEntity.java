package Shipping_System.Shipping_System.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "packages")
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String size;
    private double weight;

    private String pickupAddress;
    private String deliveryAddress;
    @Enumerated(EnumType.STRING)
    private PackageStatus status = PackageStatus.PENDING;

    public PackageEntity() {
        this.status = PackageStatus.PENDING;
    }

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private UserModel merchant;  // The relationship is with UserModel

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getMerchant() {
        return merchant;
    }

    public void setMerchant(UserModel merchant) {
        this.merchant = merchant;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


}

