package sporSalonuÜyelikVeAntrenmanPlatformu;

public class Uye extends Kullanici {
	
	private double boy;
	private double kilo;
	private int yas;
	private double yağOrani;

	public Uye(String email, String password,double boy,double kilo,int yas,double yağOrani) {
		super(email, password, Role.UYE);
		setBoy(boy);
		setKilo(kilo);
		setYas(yas);
		setYağOrani(yağOrani);
		// TODO Auto-generated constructor stub
	}
	
	public double getBoy() {
		return boy;
	}

	public void setBoy(double boy) {
		if(boy < 120.0) {
			throw new IllegalArgumentException("Boy için minumum girdi 120 cm'dir");
		}
		if(boy > 250.0) {
			throw new IllegalArgumentException("Boy için maksimum girdi 250 cm'dir.");
		}
		this.boy = boy;
	}

	public double getKilo() {
		return kilo;
	}

	public void setKilo(double kilo) {
		if(kilo < 40.0) {
			throw new IllegalArgumentException("Kilo için minumum girdi 40 kg'dir.");
		}
		if(kilo > 250.0) {
			throw new IllegalArgumentException("Kilo için maksimum girdi 250 kg'dir");
		}
		this.kilo = kilo;
	}

	public int getYas() {
		return yas;
	}

	public void setYas(int yas) {
		if(yas < 15) {
			throw new IllegalArgumentException("Minumum yas 15 olmalıdır!");
		}
		if(yas > 65) {
			throw new IllegalArgumentException("Maksimum yas 65 olmalıdır!");
		}
		this.yas = yas;
	}

	public double getYağOrani() {
		return yağOrani;
	}

	public void setYağOrani(double yağOrani) {
		if(yağOrani < 3.0) {
			throw new IllegalArgumentException("Yağ oranı 3'ün altında olamaz!");
		}
		if(yağOrani > 55.0) {
			throw new IllegalArgumentException("Yağ oranı 55'in altında olamaz!");
		}
		this.yağOrani = yağOrani;
	}

	@Override
	public void displayInfo() {
		// TODO Auto-generated method stub
		System.out.println("--- ÜYE PROFİLİ ---");
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
        System.out.println("Fiziksel Veriler: " + boy + " cm / " + kilo + " kg");
        System.out.println("Vücut Kitle Endeksi: " + String.format("%.2f", vucutKitleEndeksiHesapla()));
	}
	
	public double vucutKitleEndeksiHesapla() {
        double boyMetre = boy / 100.0;
        return kilo / (boyMetre * boyMetre);
    }

}
