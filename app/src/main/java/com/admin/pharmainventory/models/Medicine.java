package com.admin.pharmainventory.models;

import java.io.Serializable;
/**
 * Represents a Medicine entity in the pharmacy inventory.
 * Contains details like identity, stock, pricing, expiry, and pharma-specific info.
 */
public class Medicine implements Serializable {

    // ===== Basic Info =====
    private String generic_name;
    private String brand_name;
    private String category;
    private String description;

    // ===== Stock Info =====
    private int quantity;
    private int minStockLevel;

    // ===== Pricing =====
    private double costPrice;
    private double mrp;

    // ===== Pharma Info =====
    private String expiry;
    private String batchNumber;
    private String manufacturer;

    private boolean prescriptionRequired;

    public Medicine(){}

    /**
     * Constructs a Medicine object with complete details.
     *
     * @param genericName          the generic (salt) name of the medicine
     * @param brandName            the brand name of the medicine
     * @param quantity             available stock quantity
     * @param minStockLevel        minimum threshold for low stock alert
     * @param costPrice            purchase price
     * @param mrp                  maximum retail price
     * @param expiry               LocalDate expiry date of the medicine
     * @param description          usage/description of the medicine
     * @param category             drug category (e.g., Antibiotic)
     * @param manufacturer         manufacturer company name
     * @param batchNumber          batch identifier
     * @param prescriptionRequired whether prescription is required
     */
    public Medicine(
                    String genericName,
                    String brandName,
                    int quantity,
                    int minStockLevel,
                    double costPrice,
                    double mrp,
                    String expiry,
                    String description,
                    String category,
                    String manufacturer,
                    String batchNumber,
                    boolean prescriptionRequired) {

        this.generic_name = genericName;
        this.brand_name = brandName;
        this.quantity = quantity;
        this.minStockLevel = minStockLevel;
        this.costPrice = costPrice;
        this.mrp = mrp;
        this.expiry = expiry;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.prescriptionRequired = prescriptionRequired;
    }


    public String getGeneric_name() {
        return generic_name;
    }

    public void setGeneric_name(String generic_name) {
        this.generic_name = generic_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(int minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isPrescriptionRequired() {
        return prescriptionRequired;
    }

    public void setPrescriptionRequired(boolean prescriptionRequired) {
        this.prescriptionRequired = prescriptionRequired;
    }


    /**
     * Checks if stock is low.
     */
    public boolean isLowStock() {
        return quantity <= minStockLevel;
    }

    /**
     * Checks if stock is completely empty.
     */
    public boolean isOutOfStock() {
        return quantity == 0;
    }

    /**
     * Calculates profit per unit.
     */
    public double getProfit() {
        return mrp - costPrice;
    }

    /**
     * Calculates profit percentage per unit.
     */
    public double getProfitPercentage() {
        if (costPrice == 0) return 0;
        return ((mrp - costPrice) / costPrice) * 100;
    }

    /**
     * Returns stock status for UI usage.
     */
    public String getStockStatus() {
        if (quantity == 0) return "OUT_OF_STOCK";
        if (quantity <= minStockLevel) return "LOW_STOCK";
        return "IN_STOCK";
    }

}