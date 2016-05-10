import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JButton;

import jssc.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.JComboBox;

public class GUI_main {
	private int cor_x = 0;
	private int cor_y = 0;
	private int point_x = 0;
	private int point_y = 0;
	private int draw_flag = 0;
	private int open_flag = 0;
	private int lines = 48;
	private int rows = 64;

	private ArrayList<Color> colors = new ArrayList<Color>();
	private SerialPort_communicator com;
	private Temp_helper temp;
	private Webcam webcam = null;
	private WebcamPicker picker = null;
	private JPanel contentPane;
	public JFrame jframe;
	private int width;
	private int heigh;
	private SerialPort serialPort;// = new SerialPort("COM3");
	private ArrayList<Double> temperature = new ArrayList<Double>();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					GUI_main frame = new GUI_main();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_main() {
		com = new SerialPort_communicator();
		temp = new Temp_helper();
		jframe = new JFrame("Thermocam");
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setBounds(100, 100, 757, 589);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jframe.setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(617, 413, 114, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox(com.return_port());
		comboBox.setBounds(617, 11, 114, 20);
		contentPane.add(comboBox);

		JButton button_MUP = new JButton("ВВЕРХ mid");
		button_MUP.setBounds(617, 76, 114, 23);
		contentPane.add(button_MUP);
		button_MUP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {

					com.Send('e');

				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton button = new JButton("ВНИЗ mid");
		button.setBounds(617, 110, 114, 23);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {

					com.Send('d');

				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton button_1 = new JButton("ВЛЕВО mid");
		button_1.setBounds(617, 144, 114, 23);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {

					com.Send('b');

				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JButton button_2 = new JButton("ВПРАВО mid");
		button_2.setBounds(617, 178, 114, 23);
		contentPane.add(button_2);
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {

					com.Send('c');

				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton button_3 = new JButton("Выход");
		button_3.setBounds(617, 379, 114, 23);
		contentPane.add(button_3);
		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (open_flag == 1) {
						com.close();
					}
					System.exit(0);
				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		JPanel panel_1 = new JPanel() {
			public void paintComponent(Graphics h) {
				super.paintComponent(h);
				for (int i = 0; i < 16; i++) {
					switch (i) {
					case 0:
						h.setColor(new Color(255, 0, 0));
						break;
					case 1:
						h.setColor(new Color(255, 64, 0));
						break;
					case 2:
						h.setColor(new Color(255, 128, 0));
						break;
					case 3:
						h.setColor(new Color(255, 192, 0));
						break;
					case 4:
						h.setColor(new Color(255, 255, 0));
						break;
					case 5:
						h.setColor(new Color(192, 255, 0));
						break;
					case 6:
						h.setColor(new Color(128, 255, 0));
						break;
					case 7:
						h.setColor(new Color(64, 255, 0));
						break;
					case 8:
						h.setColor(new Color(0, 255, 0));
						break;
					case 9:
						h.setColor(new Color(0, 255, 64));
						break;
					case 10:
						h.setColor(new Color(0, 255, 128));
						break;
					case 11:
						h.setColor(new Color(0, 255, 192));
						break;
					case 12:
						h.setColor(new Color(0, 255, 255));
						break;
					case 13:
						h.setColor(new Color(0, 192, 255));
						break;
					case 14:
						h.setColor(new Color(0, 128, 255));
						break;
					case 15:
						h.setColor(new Color(0, 64, 255));
						break;
					case 16:
						h.setColor(new Color(0, 0, 255));
						break;
					}
					h.fillRect(20 * i, 10, 20, 20);

				}
				h.setColor(Color.BLACK);
				h.drawString(Double.toString(temp.max), 0, 40);
				h.drawString(Double.toString(temp.min), 20 * 15, 40);
			}
		};
		panel_1.setBounds(10, 480, 569, 71);
		contentPane.add(panel_1);
		webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		WebcamPanel panel = new WebcamPanel(webcam) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width = 9;
				heigh = 460 / lines;// 429
				if (colors.isEmpty()) {// true esli ni4ego net
					return;
				} else {
					int x = 0;
					int y = heigh*48;
					int count = 0;
					for (int i = 0; i < rows; i++) {
						y = heigh*48;
						for (int j = 0; j < lines; j++) {
							g.setColor(colors.get(count));
							g.fillRect(x, y, width, heigh);
							y -= heigh;
							count++;
						}
						x += width;
					}
				}
				if (draw_flag == 1 && colors.size() > 0) {
					g.setColor(Color.white);
					g.drawString(
							Double.toString(com.get_temp().get(48-cor_y+cor_x*48
									/*cor_x * 48 + cor_y*/)), point_x, point_y);
					System.out.println(point_x + "," + point_y);
				}

			}
		};
		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);
		panel.setBounds(10, 11, 569, 449);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (colors.size() == 0) {
					draw_flag = 0;
					return;
				}
				point_x = e.getX();
				point_y = e.getY();
				cor_x = point_x / 9;
				cor_y = point_y / (460 / 48);
				draw_flag = 1;
				panel.repaint();
			}
		});
		contentPane.add(panel);

		JButton btnCom = new JButton("Открыть com ");
		btnCom.setBounds(617, 42, 114, 23);
		contentPane.add(btnCom);
		btnCom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					com.open(comboBox.getSelectedItem().toString());
					open_flag = 1;
				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton button_4 = new JButton("ВПРАВО bot");
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {

					com.Send('h');

				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_4.setBounds(617, 314, 114, 23);
		contentPane.add(button_4);

		JButton button_5 = new JButton("ВВЕРХ bot");
		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {

					com.Send('j');

				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_5.setBounds(617, 212, 114, 23);
		contentPane.add(button_5);

		JButton button_6 = new JButton("ВНИЗ bot");
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {

					com.Send('i');

				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_6.setBounds(617, 246, 114, 23);
		contentPane.add(button_6);

		JButton button_7 = new JButton("ВЛЕВО bot");
		button_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {

					com.Send('g');

				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_7.setBounds(617, 280, 114, 23);
		contentPane.add(button_7);

		JButton button_8 = new JButton("Сканинг");
		button_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ���������� ���������. ����� ������������ � ����� ������
				// serialPort.setParams(9600, 8, 1, 0);
				try {
					com.get_temp().clear();
					colors.clear();
					panel.repaint();
					com.Send('a');
					DecimalFormat a=new DecimalFormat("#.##");
					Thread myThready = new Thread(new Runnable() {
						public void run() // Этот метод будет выполняться в
											// побочном потоке
						{

							while (com.get_temp().size() != 3072) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								textField.setText(a.format(((double)com
										.get_temp().size()/3072*100))+" %");
							}
							textField.setText(Integer.toString(com.get_temp()
									.size()));
							if (com.get_temp().size() > 0) {
								colors = temp.Temp_to_color(com.get_temp(),
										rows, lines);
								
								panel.repaint();
								panel_1.repaint();
								textField.setText("Данные получены");
								
							}

						}
					});
					myThready.start();
					
				} catch (SerialPortException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		});
		button_8.setBounds(617, 345, 114, 23);
		contentPane.add(button_8);
		
	}
}
