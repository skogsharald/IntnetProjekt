package kth.intnet.projekt.model;

/**
 * Created by Ludde on 2014-04-16.
 */
public class Country {
    private String countryName;
    private String currency;

    public Country(){

    }

    /**
     * The setters below most likely will not ever be used.
     *
     */
    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public String getCountryName(){
        return countryName;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public String getCurrency(){
        return currency;
    }
}
