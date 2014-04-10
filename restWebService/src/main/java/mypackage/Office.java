package mypackage;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Office {

	private String city;
	private String country;
	
	// Default constructor.
	public Office() {
	}

	// Full-blown constructor.
	public Office(String city, String country) {
		this.city = city;
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Office [city=" + city + ", country=" + country + "]";
	}
}
