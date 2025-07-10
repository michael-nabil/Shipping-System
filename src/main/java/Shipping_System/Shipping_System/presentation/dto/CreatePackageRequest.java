package Shipping_System.Shipping_System.presentation.dto;


import Shipping_System.Shipping_System.data.model.PackageStatus;
import Shipping_System.Shipping_System.domin.enitiy.Package;
public class CreatePackageRequest {
    // الحقول الموجودة بالفعل
    private String size;
    private double weight;
    private String pickupAddress;
    private String deliveryAddress;
    private PackageStatus status;
    private int merchantId;

    // Getters and Setters


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

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public Package toDomainModel() {
        Package p = new Package();
        p.setSize(this.size);
        p.setWeight(this.weight);
        p.setPickupAddress(this.pickupAddress);
        p.setDeliveryAddress(this.deliveryAddress);
        p.setStatus(this.status != null ? this.status : PackageStatus.PENDING); // default
        return p;
    }

}
