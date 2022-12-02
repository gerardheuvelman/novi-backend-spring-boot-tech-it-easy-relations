package nl.ultimateapps.TechItEasy.Dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RemoteControllerDto {

    private String compatibleWith;
    private String batteryType;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }
}
