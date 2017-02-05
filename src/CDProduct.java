
public class CDProduct extends Product {

	private int numOfTracks;

	public CDProduct(String name, int price,int numOfTracks) {
		super(name, price);
		this.setNumOfTracks(numOfTracks);
	}

	public int getNumOfTracks() {
		return numOfTracks;
	}

	public void setNumOfTracks(int numOfTracks) {
		this.numOfTracks = numOfTracks;
	}
	

}
