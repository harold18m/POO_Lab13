package procedimientosAlmacenados;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Aplicación extends JFrame {

	private JPanel contentPane;
	private JTextField textApellidos;
	private JTextField textNombres;
	private JTextField textDireccion;
	private JTextField textDistrito;

	Conexion instanciaMysql = Conexion.getInstance();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicación frame = new Aplicación();
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
	public Aplicación() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Aplicación alumnos");
		setLocationRelativeTo(null);
		setResizable(false);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 297, 243);
		contentPane.add(scrollPane);
		JTextArea area = new JTextArea();
		scrollPane.setViewportView(area);
		
		JButton btnMostrar = new JButton("Mostrar alumnos");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conexion = instanciaMysql.conectar();
					CallableStatement procedimientoAlmacenado = conexion.prepareCall("{call mostrarAlumnos} ");
					ResultSet consulta = procedimientoAlmacenado.executeQuery();
					
			
					while(consulta.next()){
						
						area.append((consulta.getInt(1) + " - "));
						area.append((consulta.getString(2) + " - "));
						area.append((consulta.getString(3) + " - "));
						area.append((consulta.getString(4) + " - "));
						area.append((consulta.getString(5) + ""));
						area.append("\n");
					} 
					
			            procedimientoAlmacenado.close();
			            instanciaMysql.cerrarConexion();
					
						
				}catch(SQLException error) {
					System.out.println(error);
				}
			}
		});
		btnMostrar.setBounds(10, 26, 141, 23);
		contentPane.add(btnMostrar);
		
		
		textApellidos = new JTextField();
		textApellidos.setBounds(336, 62, 131, 20);
		contentPane.add(textApellidos);
		textApellidos.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingresé los apellidos :");
		lblNewLabel.setBounds(336, 30, 131, 14);
		contentPane.add(lblNewLabel);
		
		textNombres = new JTextField();
		textNombres.setColumns(10);
		textNombres.setBounds(336, 118, 131, 20);
		contentPane.add(textNombres);
		
		JLabel lblIngresLosNombres = new JLabel("Ingresé los nombres:");
		lblIngresLosNombres.setBounds(336, 93, 131, 14);
		contentPane.add(lblIngresLosNombres);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(336, 174, 131, 20);
		contentPane.add(textDireccion);
		
		JLabel lblIngresLaDireccin = new JLabel("Ingresé la dirección :");
		lblIngresLaDireccin.setBounds(336, 149, 131, 14);
		contentPane.add(lblIngresLaDireccin);
		
		textDistrito = new JTextField();
		textDistrito.setColumns(10);
		textDistrito.setBounds(336, 230, 131, 20);
		contentPane.add(textDistrito);
		
		JLabel lblIngresElDistrito = new JLabel("Ingresé el distrito :");
		lblIngresElDistrito.setBounds(336, 205, 131, 14);
		contentPane.add(lblIngresElDistrito);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
		            
		            Connection conexion = instanciaMysql.conectar();
		            CallableStatement procedimientoAlmacenado = conexion.prepareCall("{call registrarAlumnos(?,?,?,?)}");
		            
		            procedimientoAlmacenado.setString(1,textApellidos.getText());
		            procedimientoAlmacenado.setString(2,textNombres.getText());
		            procedimientoAlmacenado.setString(3,textDireccion.getText());
		            procedimientoAlmacenado.setString(4,textDistrito.getText());
		            procedimientoAlmacenado.executeUpdate();
		            
		            JOptionPane.showMessageDialog(null,"Alumno registrado");
		            
		            procedimientoAlmacenado.close();
		            instanciaMysql.cerrarConexion();
		        }catch(SQLException error){
		            System.out.println(error);
		        }
			}
		});
		
		btnRegistrar.setBounds(336, 275, 131, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("");
			}
		});
		btnLimpiar.setBounds(218, 26, 89, 23);
		contentPane.add(btnLimpiar);
		
		
		
		
	}
}
