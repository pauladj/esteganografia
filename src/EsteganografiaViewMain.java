import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class EsteganografiaViewMain extends JFrame {

	private JPanel contentPane;
	private JTextField txtCuserspauladjdesktopimagenpng;
	private JTextField txtCuserspauladjdesktop;
	private JLabel lblInformationToHide;
	private JTextField txtCuserspauladjdesktopimagenpng_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EsteganografiaViewMain frame = new EsteganografiaViewMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EsteganografiaViewMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 527, 283);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Esconder Texto", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblPathImagen = new JLabel("Path imagen PNG");
		lblPathImagen.setBounds(36, 7, 98, 16);
		panel.add(lblPathImagen);
		
		txtCuserspauladjdesktopimagenpng = new JTextField();
		txtCuserspauladjdesktopimagenpng.setBounds(177, 5, 333, 20);
		panel.add(txtCuserspauladjdesktopimagenpng);
		
		txtCuserspauladjdesktopimagenpng.setText("C:/Users/Pauladj/Desktop/imagen.png");
		txtCuserspauladjdesktopimagenpng.setColumns(10);
		
		JLabel lblPathImagenNueva = new JLabel("Path imagen nueva");
		lblPathImagenNueva.setBounds(36, 39, 108, 16);
		panel.add(lblPathImagenNueva);
		
		txtCuserspauladjdesktop = new JTextField();
		txtCuserspauladjdesktop.setBounds(177, 37, 333, 20);
		panel.add(txtCuserspauladjdesktop);
		txtCuserspauladjdesktop.setText("C:/Users/Pauladj/Desktop/imagen2.png");
		txtCuserspauladjdesktop.setColumns(10);
		
		lblInformationToHide = new JLabel("- Informaci\u00F3n a esconder -");
		lblInformationToHide.setBounds(180, 71, 149, 16);
		panel.add(lblInformationToHide);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 92, 498, 126);
		panel.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JButton btnNewButton = new JButton("Esconder");
		btnNewButton.setBounds(12, 217, 498, 26);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Obtener mensaje", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Path imagen png");
		lblNewLabel.setBounds(22, 23, 133, 16);
		panel_1.add(lblNewLabel);
		
		txtCuserspauladjdesktopimagenpng_1 = new JTextField();
		txtCuserspauladjdesktopimagenpng_1.setText("C:/Users/Pauladj/Desktop/imagen2.png");
		txtCuserspauladjdesktopimagenpng_1.setBounds(135, 21, 375, 20);
		panel_1.add(txtCuserspauladjdesktopimagenpng_1);
		txtCuserspauladjdesktopimagenpng_1.setColumns(10);
		
		JLabel lblInformacinOculta = new JLabel("- Informaci\u00F3n oculta - ");
		lblInformacinOculta.setBounds(188, 63, 143, 16);
		panel_1.add(lblInformacinOculta);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(12, 82, 498, 138);
		panel_1.add(scrollPane_1);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		
		JButton btnNewButton_1 = new JButton("Obtener texto oculto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String texto = EsteganografiaPrograma.getEsteganografiaPrograma().obtenerTexto(txtCuserspauladjdesktopimagenpng_1.getText());
					textPane_1.setText(texto);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(12, 217, 498, 26);
		panel_1.add(btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					EsteganografiaPrograma.getEsteganografiaPrograma().esconderTexto(txtCuserspauladjdesktopimagenpng.getText(), textPane.getText(), txtCuserspauladjdesktop.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
