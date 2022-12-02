package nl.ultimateapps.TechItEasy.Dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WallBracketDto {

    private String size;
    private Boolean ajustable;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    private Double price;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAjustable() {
        return ajustable;
    }

    public void setAjustable(Boolean ajustable) {
        this.ajustable = ajustable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
