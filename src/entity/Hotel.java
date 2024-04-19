package entity;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String area;
    private String adress;
    private String eposta;
    private String phone;
    private String stars;
    public Hotel() {

    }

    public Hotel(int id, String name, String city, String area, String adress, String eposta, String phone, String stars) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.area = area;
        this.adress = adress;
        this.eposta = eposta;
        this.phone = phone;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", adress='" + adress + '\'' +
                ", eposta='" + eposta + '\'' +
                ", phone='" + phone + '\'' +
                ", stars='" + stars + '\'' +
                '}';
    }
}
