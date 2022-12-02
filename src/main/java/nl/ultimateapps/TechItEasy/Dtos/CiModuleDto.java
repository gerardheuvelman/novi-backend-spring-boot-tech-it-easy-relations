package nl.ultimateapps.TechItEasy.Dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CiModuleDto {

    @NotNull
    @Size(min = 2, max = 30)
    private String  name;
    private String type;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
