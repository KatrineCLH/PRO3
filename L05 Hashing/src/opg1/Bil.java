package opg1;

import java.util.Objects;

public class Bil {
    private String registreringsnummer;
    private String mærke;
    private String model;
    private String farve;

    public Bil(String registreringsnummer, String mærke, String model, String farve) {
        this.registreringsnummer = registreringsnummer;
        this.mærke = mærke;
        this.model = model;
        this.farve = farve;
    }

    public String getRegistreringsnummer() {
        return registreringsnummer;
    }

    public void setRegistreringsnummer(String registreringsnummer) {
        this.registreringsnummer = registreringsnummer;
    }

    public String getMærke() {
        return mærke;
    }

    public void setMærke(String mærke) {
        this.mærke = mærke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFarve() {
        return farve;
    }

    public void setFarve(String farve) {
        this.farve = farve;
    }

    @Override
    public String toString(){
        return String.format("%s %s i %s med registreringsnummer %s", mærke, model, farve, registreringsnummer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bil bil = (Bil) o;
        return Objects.equals(registreringsnummer, bil.registreringsnummer) && Objects.equals(mærke, bil.mærke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registreringsnummer, mærke);
    }
}
