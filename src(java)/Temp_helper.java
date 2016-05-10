import java.awt.Color;
import java.util.ArrayList;

public class Temp_helper {
	public double max=0;
	private double average;
	public double min=0;
	private double tmin=0;
	private double tmax=0;
	private double step;
	private int redvalues;
	private int greenvalues;
	private int bluevalues;

	Temp_helper() {

	}

	public void Find_Limits(ArrayList<Double> temp, int rows, int lines) {
		int maxpos = 0;
		int minpos = 0;
		this.max = temp.get(0);
		this.min = temp.get(0);
		for (int i = 0; i < (rows * lines); i++) {
			for (int j = i + 1; j < (rows * lines); j++) {
				if (this.max < temp.get(j) && maxpos != j) {
					this.max = temp.get(j);
					maxpos = j;
				}
				if (this.min > temp.get(j) && minpos != j) {
					this.min = temp.get(j);
					minpos = j;
				}
			}
		}
	}

	public int CalculateRGB(int rgb1, int rgb2, double t1, double t2, double t) {
		double faktor = (((t - t1) * 100.0) / (t2 - t1)) / 100.0;
		double drgb = rgb1 + (faktor * (rgb2 - rgb1));
		int rgb = new Double(drgb).intValue();
		return rgb;
	}

	public void IntToRGB(double temperatures) {
		step = (((max - min) * 100.0) / 10) / 100.0;

		if ((temperatures >= min) && (temperatures <= (min + step))) {
			redvalues = CalculateRGB(28, 31, min, (min + step), temperatures);
			greenvalues = CalculateRGB(1, 17, min, (min + step), temperatures);
			bluevalues = CalculateRGB(108, 218, min, (min + step), temperatures);
		}
		if ((temperatures > (min + step)) && (temperatures <= (min + 2 * step))) {
			redvalues = CalculateRGB(31, 50, (min + step), (min + 2 * step),
					temperatures);
			greenvalues = CalculateRGB(17, 111, (min + step), (min + 2 * step),
					temperatures);
			bluevalues = CalculateRGB(218, 238, (min + step), (min + 2 * step),
					temperatures);
		}
		if ((temperatures > (min + 2 * step))
				&& (temperatures <= (min + 3 * step))) {
			redvalues = CalculateRGB(50, 63, (min + 2 * step),
					(min + 3 * step), temperatures);
			greenvalues = CalculateRGB(111, 196, (min + 2 * step),
					(min + 3 * step), temperatures);
			bluevalues = CalculateRGB(238, 229, (min + 2 * step),
					(min + 3 * step), temperatures);
		}
		if ((temperatures > (min + 3 * step))
				&& (temperatures <= (min + 4 * step))) {
			redvalues = CalculateRGB(63, 64, (min + 3 * step),
					(min + 4 * step), temperatures);
			greenvalues = CalculateRGB(196, 222, (min + 3 * step),
					(min + 4 * step), temperatures);
			bluevalues = CalculateRGB(229, 135, (min + 3 * step),
					(min + 4 * step), temperatures);
		}
		if ((temperatures > (min + 4 * step))
				&& (temperatures <= (min + 5 * step))) {
			redvalues = CalculateRGB(64, 192, (min + 4 * step),
					(min + 5 * step), temperatures);
			greenvalues = CalculateRGB(222, 240, (min + 4 * step),
					(min + 5 * step), temperatures);
			bluevalues = CalculateRGB(135, 14, (min + 4 * step),
					(min + 5 * step), temperatures);
		}
		if ((temperatures > (min + 5 * step))
				&& (temperatures <= (min + 6 * step))) {
			redvalues = CalculateRGB(192, 223, (min + 5 * step),
					(min + 6 * step), temperatures);
			greenvalues = CalculateRGB(240, 172, (min + 5 * step),
					(min + 6 * step), temperatures);
			bluevalues = CalculateRGB(14, 18, (min + 5 * step),
					(min + 6 * step), temperatures);
		}
		if ((temperatures > (min + 6 * step))
				&& (temperatures <= (min + 7 * step))) {
			redvalues = CalculateRGB(223, 209, (min + 6 * step),
					(min + 7 * step), temperatures);
			greenvalues = CalculateRGB(172, 111, (min + 6 * step),
					(min + 7 * step), temperatures);
			bluevalues = CalculateRGB(18, 14, (min + 6 * step),
					(min + 7 * step), temperatures);
		}
		if ((temperatures > (min + 7 * step))
				&& (temperatures <= (min + 8 * step))) {
			redvalues = CalculateRGB(209, 210, (min + 7 * step),
					(min + 8 * step), temperatures);
			greenvalues = CalculateRGB(111, 50, (min + 7 * step),
					(min + 8 * step), temperatures);
			bluevalues = CalculateRGB(14, 28, (min + 7 * step),
					(min + 8 * step), temperatures);
		}
		if ((temperatures > (min + 8 * step))
				&& (temperatures <= (min + 9 * step))) {
			redvalues = CalculateRGB(210, 194, (min + 8 * step),
					(min + 9 * step), temperatures);
			greenvalues = CalculateRGB(50, 26, (min + 8 * step),
					(min + 9 * step), temperatures);
			bluevalues = CalculateRGB(28, 0, (min + 8 * step),
					(min + 9 * step), temperatures);
		}
		if ((temperatures > (min + 9 * step)) && (temperatures <= max)) {
			redvalues = CalculateRGB(194, 132, (min + 9 * step), max,
					temperatures);
			greenvalues = CalculateRGB(26, 26, (min + 9 * step), max,
					temperatures);
			bluevalues = CalculateRGB(0, 0, (min + 9 * step), max, temperatures);
		}
		if (temperatures < min) {
			redvalues = 28;
			greenvalues = 1;
			bluevalues = 108;
		}
		if (temperatures > max) {
			redvalues = 132;
			greenvalues = 26;
			bluevalues = 0;
		}
	}
	
	public ArrayList<Color> Temp_to_color(ArrayList<Double> temp, int rows, int lines)
	{
		ArrayList<Color> col=new ArrayList<Color>();
		Find_Limits(temp, rows, lines);
		for(int i=0;i<rows*lines;i++)
		{
			IntToRGB(temp.get(i));
			col.add(new Color(redvalues,greenvalues,bluevalues,255));
		}
		return col;
		
	}

}
