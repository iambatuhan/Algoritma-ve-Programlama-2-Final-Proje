import java.util.Scanner;

// İlac abstract sınıfı
abstract class Ilac {
    private String ilacAdi;
    private String ilacTuru;

    public Ilac(String ilacAdi, String ilacTuru) {
        this.ilacAdi = ilacAdi;
        this.ilacTuru = ilacTuru;
    }

    public String getIlacAdi() {
        return ilacAdi;
    }

    public String getIlacTuru() {
        return ilacTuru;
    }

    // Bilgileri gösterme soyut metodu
    public abstract void bilgileriGoster();
}

// Aspirin sınıfı, Ilac sınıfından türetildi
class Aspirin extends Ilac {
    public Aspirin(String ilacAdi, String ilacTuru) {
        super(ilacAdi, ilacTuru);
    }

    // Bilgileri gösterme metodu
    public void bilgileriGoster() {
        System.out.println("İlaç Adı: " + getIlacAdi());
        System.out.println("İlaç Türü: " + getIlacTuru());
        System.out.println("Aspirin bilgileri gösteriliyor...");
    }
}

// Antibiyotik sınıfı, Ilac sınıfından türetildi
class Antibiyotik extends Ilac {
    public Antibiyotik(String ilacAdi, String ilacTuru) {
        super(ilacAdi, ilacTuru);
    }

    // Bilgileri gösterme metodu
    public void bilgileriGoster() {
        System.out.println("İlaç Adı: " + getIlacAdi());
        System.out.println("İlaç Türü: " + getIlacTuru());
        System.out.println("Antibiyotik bilgileri gösteriliyor...");
    }
}

// Hasta abstract sınıfı
abstract class Hasta {
    private String hastaAdi;
    private String hastaSoyadi;

    public Hasta(String hastaAdi, String hastaSoyadi) {
        this.hastaAdi = hastaAdi;
        this.hastaSoyadi = hastaSoyadi;
    }

    public String getHastaAdi() {
        return hastaAdi;
    }

    public String getHastaSoyadi() {
        return hastaSoyadi;
    }

    // Reçeteye ilaç ekleme soyut metodu
    public abstract void receteyeIlacEkle(Ilac ilac);

    // Reçeteyi gösterme soyut metodu
    public abstract void receteyiGoster();

    // Hastayı tedavi etme soyut metodu
    public abstract void tedaviEt();
}

// StandartHasta sınıfı, Hasta sınıfından türetilmiş
class NormalHasta extends Hasta {
    private Ilac recete;

    public NormalHasta(String hastaAdi, String hastaSoyadi) {
        super(hastaAdi, hastaSoyadi);
    }

    // Reçeteye ilaç ekleme metodu
    public void receteyeIlacEkle(Ilac ilac) {
        recete = ilac;
    }

    // Reçeteyi gösterme metodu
    public void receteyiGoster() {
        System.out.println("Hasta Adı: " + getHastaAdi());
        System.out.println("Hasta Soyadı: " + getHastaSoyadi());
        System.out.println("Recete Bilgileri:");
        recete.bilgileriGoster();
    }

    // Hastayı tedavi etme metodu
    public void tedaviEt() {
        System.out.println("Hasta tedavi ediliyor...");
        // Tedavi işlemleri
    }
}

// OdemeIslemi arayüzü
interface OdemeIslemi {
    void odemeYap();
}

// KrediKarti sınıfı, OdemeIslemi arayüzünden türetildi
class KrediKarti implements OdemeIslemi {
    private double odemeMiktari;

    public KrediKarti(double odemeMiktari) {
        this.odemeMiktari = odemeMiktari;
    }

    // Ödeme yapma metodu
    public void odemeYap() {
        double komisyon = odemeMiktari * 0.05;
        double toplamOdeme = odemeMiktari + komisyon;
        System.out.println("Kredi Kartı ile ödeme yapılıyor. Ödeme Miktarı: " + odemeMiktari + " TL");
        System.out.println("Komisyon: " + komisyon + " TL");
        System.out.println("Toplam Ödeme: " + toplamOdeme + " TL");
    }
}

// NakitOdeme sınıfı, OdemeIslemi arayüzünden türetildi
class NakitOdeme implements OdemeIslemi {
    private double odemeMiktari;

    public NakitOdeme(double odemeMiktari) {
        this.odemeMiktari = odemeMiktari;
    }

    // Ödeme yapma metodu
    public void odemeYap() {
        System.out.println("Nakit ile ödeme yapılıyor. Ödeme Miktarı: " + odemeMiktari + " TL");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hasta bilgilerini alma
        System.out.print("Hasta Adı: ");
        String hastaAdi = scanner.nextLine();

        System.out.print("Hasta Soyadı: ");
        String hastaSoyadi = scanner.nextLine();

        // Hasta nesnesi oluşturma
        Hasta hasta = new NormalHasta(hastaAdi, hastaSoyadi);

        System.out.println("Hasta bilgileri kaydedildi.");

        // İlaç bilgilerini alma
        System.out.print("İlaç Adı: ");
        String ilacAdi = scanner.nextLine();

        System.out.print("İlaç Türü: ");
        String ilacTuru = scanner.nextLine();

        System.out.print("Tutar: ");
        double tutar = scanner.nextDouble();

        // İlaç nesnesi oluşturma ve reçeteye ekleme
        Ilac ilac = new Antibiyotik(ilacAdi, ilacTuru);
        hasta.receteyeIlacEkle(ilac);

        System.out.println("İlaç bilgileri kaydedildi.");

        // Reçete bilgilerini gösterme
        System.out.println("Recete Bilgileri:");
        hasta.receteyiGoster();

        // Hasta tedavi etme
        System.out.println("Hasta tedavi ediliyor...");
        hasta.tedaviEt();

        // Ödeme işlemi
        System.out.println("Ödeme İşlemi:");
        OdemeIslemi odeme;

        if (tutar > 0) {
            // Ödeme türünü seçme
            System.out.println("Ödeme Türünü Seçin:");
            System.out.println("1. Kredi Kartı");
            System.out.println("2. Nakit");

            int secim = scanner.nextInt();

            if (secim == 1) {
                odeme = new KrediKarti(tutar);
                odeme.odemeYap();
            } else if (secim == 2) {
                odeme = new NakitOdeme(tutar);
                odeme.odemeYap();
            } else {
                System.out.println("Geçersiz seçim!");
            }
        } else {
            System.out.println("Tutar sıfır veya negatif olamaz!");
        }

        scanner.close();
    }
}
