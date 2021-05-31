package List;

public class Menu {
	private String KodeMenu, NamaMenu, HargaMenu;
	private int StockMenu;

	public Menu( String namaMenu, String hargaMenu, int stockMenu) {
		super();
		this.NamaMenu = namaMenu;
		this.HargaMenu = hargaMenu;
		this.StockMenu = stockMenu;
	}

	public String getKodeMenu() {
		return KodeMenu;
	}
	
	public String getNamaMenu() {
		return NamaMenu;
	}

	public String getHargaMenu() {
		return HargaMenu;
	}

	public int getStockMenu() {
		return StockMenu;
	}

	public void SetKodeMenu(String kodeMenu) {
		this.KodeMenu = kodeMenu;
	}
}
