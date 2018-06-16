package botAssociacao;

public class Reuniao {

	private String mAta;
	private long mData;
	
	public String getAta() {
		return mAta;
	}
	
	public long getData() {
		return mData;
	}
	
	public void setAta(String ata) {
		mAta = ata;
	}
	
	public void setData(long data) {
		mData = data;
	}
	
	public Reuniao(long data, String ata) {
		mAta = ata;
		mData = data;
	}
	
}
