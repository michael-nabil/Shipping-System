package Shipping_System.Shipping_System.presentation.dto;


import Shipping_System.Shipping_System.data.model.PackageStatus;

public class PackageResponse {

    private int id;
    private String size;
    private double weight;
    private String pickupAddress;
    private String deliveryAddress;
    private PackageStatus status;
    private int merchantId;

    // Constructor
    public PackageResponse(int id, String size, double weight, String pickupAddress, String deliveryAddress, PackageStatus status, int merchantId) {
        this.id = id;
        this.size = size;
        this.weight = weight;
        this.pickupAddress = pickupAddress;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.merchantId = merchantId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
