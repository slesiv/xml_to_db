package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Org {

    private String name;
    private long inn;
    private long ogrn;
    private String address;
    private int postcode;
    private LocalDate dateOpen;
    private LocalDateTime dateCreated;

    public Org() {
    }

    public Org(long inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public long getOgrn() {
        return ogrn;
    }

    public void setOgrn(long ogrn) {
        this.ogrn = ogrn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public LocalDate getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(LocalDate dateOpen) {
        this.dateOpen = dateOpen;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Org{" +
                "name='" + name + '\'' +
                ", inn=" + inn +
                ", ogrn=" + ogrn +
                ", address='" + address + '\'' +
                ", postcode=" + postcode +
                ", openDate=" + dateOpen +
                ", created=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Org org = (Org) o;
        return inn == org.inn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inn);
    }
}