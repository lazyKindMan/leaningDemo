package entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Apple extends testP{
    private Integer price;
    private List<String> priceTag;
    private Map<String, testEntity> testMap;

    public void setPrice(Integer p){
        price = p;
    }
    public Integer getPrice(){
        return price;
    }

    class testEntity{
        private Integer test1;
        private String test2;
        private Map<String,String> test3;
    }
}
