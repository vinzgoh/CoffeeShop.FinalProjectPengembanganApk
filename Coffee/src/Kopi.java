
import java.util.Objects;

public class Kopi {

    private String namapembeli;
    private String kode;
    private String namabrg;
    private String jumlah;

    public Kopi(String kode, String namapembeli, String namabrg, String jumlah) {
        this.kode = kode;
        this.namapembeli = namapembeli;
        this.namabrg = namabrg;
        this.jumlah = jumlah;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamabrg() {
        return namabrg;
    }

    public void setNamabrg(String namabrg) {
        this.namabrg = namabrg;
    }

    public String getNamapembeli() {
        return namapembeli;
    }

    public void setNamapembeli(String namapembeli) {
        this.namapembeli = namapembeli;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return "Kopi{" + "kode=" + kode + ", namabrg=" + namabrg + ", namapembeli=" + namapembeli + ", jumlah=" + jumlah + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.kode);
        hash = 89 * hash + Objects.hashCode(this.namabrg);
        hash = 89 * hash + Objects.hashCode(this.namapembeli);
        hash = 89 * hash + Objects.hashCode(this.jumlah);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kopi other = (Kopi) obj;
        if (!Objects.equals(this.namabrg, other.namabrg)) {
            return false;
        }
        if (!Objects.equals(this.namapembeli, other.namapembeli)) {
            return false;
        }
        if (!Objects.equals(this.kode, other.kode)) {
            return false;
        }
        if (!Objects.equals(this.jumlah, other.jumlah)) {
            return false;
        }
        return true;
    }

}
