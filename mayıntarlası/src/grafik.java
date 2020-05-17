import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class grafik extends JFrame {

	public boolean resetleyici = false;

	public boolean muhbir = false;
	Date startDate = new Date();
	Date endDate;

	int spacing = 5;
	int neighs = 0;

	String vicMes = "Hen�z de�il!";

	public int mx = -100;
	public int my = -100;

	public int gulenX = 605;
	public int gulenY = 5;

	public int gln_yuzX = gulenX + 35;
	public int gln_yuzY = gulenY + 35;

	public int muhbirX = 445;
	public int muhbirY = 5;

	public int muhbirmerkeziX = muhbirX + 35;
	public int muhbirmerkeziY = muhbirY + 35;

	public int zamanX = 1130;
	public int zamanY = 5;
	public int vicMesX = 700;
	public int vicMesY = -50;

	public int saniye = 0;

	public boolean gulen_yuz = true;

	public boolean zafer = false;
	public boolean yenilgi = false;

	Random rand = new Random();

	int[][] mayinlar = new int[16][9];
	int[][] komsular = new int[16][9];
	boolean[][] �ikarmak = new boolean[16][9];
	boolean[][] isaretlenmis = new boolean[16][9];

	public grafik() {
		this.setTitle("May�n Tarlas�");
		this.setSize(1286, 829);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 9; j++) {
				if (rand.nextInt(100) < 20) {
					mayinlar[i][j] = 1;
				} else {
					mayinlar[i][j] = 0;
				}
				�ikarmak[i][j] = false;
			}
		}

		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 9; j++) {
				neighs = 0;
				for (int m = 0; m < 16; m++) {
					for (int n = 0; n < 9; n++) {
						if (!(m == i && n == j)) {
							if (isN(i, j, m, n) == true)
								neighs++;
						}
					}
				}
				komsular[i][j] = neighs;
			}
		}

		Tahta tahta = new Tahta();
		this.setContentPane(tahta);

		hareket move = new hareket();
		this.addMouseMotionListener(move);

		t�klanma click = new t�klanma();
		this.addMouseListener(click);
	}

	public class Tahta extends JPanel {
		public void paintComponent(Graphics M_tahta) {
			M_tahta.setColor(Color.DARK_GRAY);
			M_tahta.fillRect(0, 0, 1280, 800);

			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 9; j++) {
					M_tahta.setColor(Color.gray);

					if (�ikarmak[i][j] == true) {
						M_tahta.setColor(Color.white);
						if (mayinlar[i][j] == 1) {
							M_tahta.setColor(Color.red);
						}
					}

					if (mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing
							&& my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
						M_tahta.setColor(Color.lightGray);
					}
					M_tahta.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);
					if (�ikarmak[i][j] == true) {
						M_tahta.setColor(Color.black);
						if (mayinlar[i][j] == 0 && komsular[i][j] != 0) {
							if (komsular[i][j] == 1) {
								M_tahta.setColor(Color.blue);
							} else if (komsular[i][j] == 2) {
								M_tahta.setColor(Color.green);
							} else if (komsular[i][j] == 3) {
								M_tahta.setColor(Color.red);
							} else if (komsular[i][j] == 4) {
								M_tahta.setColor(new Color(0, 0, 128));
							} else if (komsular[i][j] == 5) {
								M_tahta.setColor(new Color(178, 34, 34));
							} else if (komsular[i][j] == 6) {
								M_tahta.setColor(new Color(72, 209, 204));
							} else if (komsular[i][j] == 7) {
								M_tahta.setColor(Color.black);
							} else if (komsular[i][j] == 8) {
								M_tahta.setColor(Color.darkGray);
							}

							M_tahta.setFont(new Font("Tahoma", Font.BOLD, 40));
							M_tahta.drawString(Integer.toString(komsular[i][j]), i * 80 + 27, j * 80 + 80 + 55);
						} else if (mayinlar[i][j] == 1) {
							M_tahta.fillRect(i * 80 + 10 + 20, j * 80 + 80 + 20, 20, 40);
							M_tahta.fillRect(i * 80 + 20, j * 80 + 80 + 10 + 20, 40, 20);
							M_tahta.fillRect(i * 80 + 5 + 20, j * 80 + 80 + 5 + 20, 30, 30);
							M_tahta.fillRect(i * 80 + 38, j * 80 + 80 + 15, 4, 50);
							M_tahta.fillRect(i * 80 + 15, j * 80 + 80 + 38, 50, 4);
						}
					}

				}
			}
			M_tahta.setColor(Color.yellow);
			M_tahta.fillOval(gulenX, gulenY, 70, 70);
			M_tahta.setColor(Color.BLACK);
			M_tahta.fillOval(gulenX + 15, gulenY + 20, 10, 10);
			M_tahta.fillOval(gulenX + 45, gulenY + 20, 10, 10);
			if (gulen_yuz == true) {
				M_tahta.fillRect(gulenX + 20, gulenY + 50, 30, 5);
				M_tahta.fillRect(gulenX + 17, gulenY + 45, 5, 5);
				M_tahta.fillRect(gulenX + 48, gulenY + 45, 5, 5);

			} else {
				M_tahta.fillRect(gulenX + 20, gulenY + 45, 30, 5);
				M_tahta.fillRect(gulenX + 17, gulenY + 50, 5, 5);
				M_tahta.fillRect(gulenX + 48, gulenY + 45, 5, 5);

			}
			M_tahta.setColor(Color.BLACK);
			M_tahta.fillRect(muhbirX + 32, muhbirY + 15, 7, 40);
			M_tahta.fillRect(muhbirX + 20, muhbirY + 50, 30, 10);
			M_tahta.setColor(Color.red);
			M_tahta.fillRect(muhbirX + 15, muhbirY + 15, 20, 15);
			M_tahta.setColor(Color.BLACK);
			M_tahta.drawRect(muhbirX + 15, muhbirY + 15, 20, 15);
			M_tahta.drawRect(muhbirX + 17, muhbirY + 15, 18, 13);
			M_tahta.drawRect(muhbirX + 18, muhbirY + 17, 16, 11);
			M_tahta.drawOval(muhbirX, muhbirY, 70, 70);
			M_tahta.drawOval(muhbirX + 1, muhbirY + 1, 68, 68);
			M_tahta.drawOval(muhbirX + 2, muhbirY + 2, 66, 66);

			if (muhbir == true) {
				M_tahta.setColor(Color.red);
			}

			M_tahta.setColor(Color.black);
			M_tahta.fillRect(zamanX, zamanY, 140, 70);
			if (yenilgi == false && zafer == false) {
				saniye = (int) ((new Date().getTime() - startDate.getTime()) / 1000);
			}
			if (saniye > 999) {
				saniye = 999;
			}
			M_tahta.setColor(Color.WHITE);
			if (zafer == true) {
				M_tahta.setColor(Color.green);
			} else if (yenilgi == true) {
				M_tahta.setColor(Color.red);
			}
			M_tahta.setFont(new Font("Tahoma", Font.PLAIN, 80));
			if (saniye < 10) {
				M_tahta.drawString("00" + Integer.toString(saniye), zamanX, zamanY + 65);
			} else if (saniye < 100) {
				M_tahta.drawString("0" + Integer.toString(saniye), zamanX, zamanY + 65);
			} else {
				M_tahta.drawString(Integer.toString(saniye), zamanX, zamanY + 65);
			}

			if (zafer == true) {
				M_tahta.setColor(Color.GREEN);
				vicMes = "KAZANDIN";
			} else if (yenilgi == true) {
				M_tahta.setColor(Color.red);
				vicMes = "KAYBETT�N";
			}
			if (zafer == true || yenilgi == true) {
				vicMesY = -50 + (int) (new Date().getTime() - endDate.getTime()) / 10;
				if (vicMesY > 67) {
					vicMesY = 67;
				}
				M_tahta.setFont(new Font("Tahoma", Font.PLAIN, 70));
				M_tahta.drawString(vicMes, vicMesX, vicMesY);
			}

		}
	}

	public class hareket implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
			/*
			 * System.out.println("Fare Ta��nd�!");
			 * 
			 * System.out.println("X:" + mx+",Y:"+my);
			 */
		}

	}

	public class t�klanma implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			mx = e.getX();
			my = e.getY();

			if (k�t�X() != -1 && k�t�Y() != -1) {
				�ikarmak[k�t�X()][k�t�Y()] = true;
			}

			if (k�t�X() != -1 && k�t�Y() != -1) {

				System.out.println(
						"Fare [" + k�t�X() + "," + k�t�Y() + "] , May�n kom�usu say�s� :" + komsular[k�t�X()][k�t�Y()]);
			} else {
				System.out.println("��aret�i herhangi bir kutunun i�inde de�il!");

			}
			if (ig_yuz() == true) {
				resetleyici();
				System.out.println("G�len y�z = Do�ru!");

			}

			if (ig_muhbir() == true) {
				muhbir = true;
				System.out.println("Bayrak�� do�ru!");
			}
		}

		private void resetleyici() {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void zafer_durumu() {

		if (yenilgi == false) {

			for (int i = 0; i < 16; i++) {
				for (int j = 0; j < 9; j++) {
					if (�ikarmak[i][j] == true && mayinlar[i][j] == 1) {
						yenilgi = true;
						gulen_yuz = false;
						endDate = new Date();
					}
				}
			}
		}

		if (toplamk�t�() >= 144 - toplam_may�n() && zafer == false) {

			zafer = true;
			endDate = new Date();

		}
	}

	public int toplam_may�n() {
		int toplam = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 9; j++) {
				if (mayinlar[i][j] == 1) {
					toplam++;

				}
			}
		}

		return toplam;
	}

	public int toplamk�t�() {
		int toplam = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 9; j++) {
				if (�ikarmak[i][j] == true) {
					toplam++;

				}
			}
		}

		return toplam;
	}

	public void sifirlama() {

		resetleyici = true;
		startDate = new Date();
		vicMesY = -50;
		vicMes = "Hen�z de�il!";

		gulen_yuz = true;
		zafer = false;
		yenilgi = false;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 9; j++) {
				if (rand.nextInt(100) < 20) {
					mayinlar[i][j] = 1;
				} else {
					mayinlar[i][j] = 0;
				}
				�ikarmak[i][j] = false;
				isaretlenmis[i][j] = false;
			}
		}

		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 9; j++) {
				neighs = 0;
				for (int m = 0; m < 16; m++) {
					for (int n = 0; n < 9; n++) {
						if (!(m == i && n == j)) {
							if (isN(i, j, m, n) == true)
								neighs++;
						}
					}
				}
				komsular[i][j] = neighs;
			}
		}
		resetleyici = false;

	}

	public boolean ig_yuz() {
		int dif = (int) Math.sqrt(
				Math.abs(mx - gln_yuzX) * Math.abs(mx - gln_yuzX) + Math.abs(my - gln_yuzY) * Math.abs(my - gln_yuzY));
		if (dif < 35) {
			return true;
		}
		return false;

	}

	public boolean ig_muhbir() {
		int dif = (int) Math.sqrt(Math.abs(mx - muhbirmerkeziX) * Math.abs(mx - muhbirmerkeziX)
				+ Math.abs(my - muhbirmerkeziY) * Math.abs(my - muhbirmerkeziY));
		if (dif < 35) {
			return true;
		}
		return false;

	}

	public int k�t�X() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 9; j++) {

				if (mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing
						&& my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
					return i;
				}

			}
		}
		return -1;

	}

	public int k�t�Y() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 9; j++) {

				if (mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing
						&& my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
					return j;
				}
			}
		}
		return -1;
	}

	public boolean isN(int mX, int mY, int cX, int cY) {
		if (mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mayinlar[cX][cY] == 1) {

			return true;
		}

		return false;

	}

}