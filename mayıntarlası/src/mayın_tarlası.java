
public class may�n_tarlas� implements Runnable {

	grafik gui = new grafik();

	public static void main(String[] args) {
		new Thread(new may�n_tarlas�()).start();
	}

	@Override
	public void run() {
		while (true) {
			gui.repaint();
			if (gui.resetleyici == false) {
				gui.zafer_durumu();
				// System.out.println("Zafer :" +gui.zafer +" , Yenilgi :" +gui.yenilgi);

			}

		}

	}

}
