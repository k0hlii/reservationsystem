package model;

public class Country {
    int id;
    String country;
    String countryShort;
    int area;

    public Country(int id, String country, String countryShort, int area) {
        this.id = id;
        this.country = country;
        this.countryShort = countryShort;
        this.area = area;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryShort() {
        return countryShort;
    }

    public void setCountryShort(String countryShort) {
        this.countryShort = countryShort;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", countryShort='" + countryShort + '\'' +
                ", area=" + area +
                '}';
    }
}
