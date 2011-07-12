/**
 * 
 */
package no.mesan.ejafjallajokull.pojo;

/**
 * @author oleh
 *
 */
public class Huskelapp {
	
	private String tittel;
	private String innhold;
	private String brukernavn;
	private long timestamp;
	
	public Huskelapp(){
		super();
	}
	
	public Huskelapp(String tittel, String innhold, String brukernavn, long timestamp) {
		super();
		this.tittel = tittel;
		this.innhold = innhold;
		this.brukernavn = brukernavn;
		this.timestamp = timestamp;
	}
	
	
	
	/**
	 * @param tittel the tittel to set
	 */
	public void setTittel(String tittel) {
		this.tittel = tittel;
	}
	/**
	 * @return the tittel
	 */
	public String getTittel() {
		return tittel;
	}
	/**
	 * @param innhold the innhold to set
	 */
	public void setInnhold(String innhold) {
		this.innhold = innhold;
	}
	/**
	 * @return the innhold
	 */
	public String getInnhold() {
		return innhold;
	}

	/**
	 * @param brukernavn the brukernavn to set
	 */
	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	/**
	 * @return the brukernavn
	 */
	public String getBrukernavn() {
		return brukernavn;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	

}
