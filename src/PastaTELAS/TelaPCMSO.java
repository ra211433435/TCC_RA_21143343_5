package PastaTELAS;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import PastaDAO.CadastroPcmsoDAO;
import PastaDAO.ConexaoDAO;
import PastaDTO.CadastroPcmsoDTO;

public class TelaPCMSO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPcmsoEmpresa;
	private JTextField txtPcmsoDataInicial;
	private JTextField txtPcmsoDataFinal;
	private JTable tbListarPcmso;
	private JTextField txtPcmsoId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPCMSO frame = new TelaPCMSO();
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
	public TelaPCMSO() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblControleDoPcmso = new JLabel("Controle do PCMSO");
		lblControleDoPcmso.setFont(new Font("Dialog", Font.BOLD, 14));
		lblControleDoPcmso.setEnabled(true);
		lblControleDoPcmso.setBounds(209, 0, 200, 35);
		contentPane.add(lblControleDoPcmso);
		
		JLabel lblPcmsoEmpresa = new JLabel("Empresa");
		lblPcmsoEmpresa.setBounds(21, 129, 81, 14);
		contentPane.add(lblPcmsoEmpresa);
		
		txtPcmsoEmpresa = new JTextField();
		txtPcmsoEmpresa.setColumns(10);
		txtPcmsoEmpresa.setBounds(21, 142, 255, 20);
		contentPane.add(txtPcmsoEmpresa);
		
		JLabel lblPcmsoInicial = new JLabel("Data Inicial");
		lblPcmsoInicial.setBounds(21, 173, 81, 14);
		contentPane.add(lblPcmsoInicial);
		
		txtPcmsoDataInicial = new JTextField();
		txtPcmsoDataInicial.setColumns(10);
		txtPcmsoDataInicial.setBounds(21, 186, 120, 20);
		contentPane.add(txtPcmsoDataInicial);
		
		JLabel lblPcmsoFinal = new JLabel("Data Final");
		lblPcmsoFinal.setBounds(164, 173, 81, 14);
		contentPane.add(lblPcmsoFinal);
		
		txtPcmsoDataFinal = new JTextField();
		txtPcmsoDataFinal.setColumns(10);
		txtPcmsoDataFinal.setBounds(164, 186, 120, 20);
		contentPane.add(txtPcmsoDataFinal);
		
		JButton btnPcmsoSalvar = new JButton("Cadastrar");
		btnPcmsoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtPcmsoEmpresa.getText().equals("") ||txtPcmsoDataInicial.getText().equals("") || txtPcmsoDataFinal.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Verifique se tem campos em branco!");
				
				}else {
					
				String cadastro_pcmsoempresa, cadastro_pcmsodatainicial, cadastro_pcmsodatafinal;
				
				cadastro_pcmsoempresa = txtPcmsoEmpresa.getText();
				cadastro_pcmsodatainicial = txtPcmsoDataInicial.getText();
				cadastro_pcmsodatafinal = txtPcmsoDataFinal.getText();
				
				CadastroPcmsoDTO objpcmsoempresadto = new CadastroPcmsoDTO();
				objpcmsoempresadto.setCadastro_pcmsoempresa(cadastro_pcmsoempresa);
				objpcmsoempresadto.setCadastro_pcmsodatainicial(cadastro_pcmsodatainicial);
				objpcmsoempresadto.setCadastro_pcmsodatafinal(cadastro_pcmsodatafinal);
				
				CadastroPcmsoDAO objpcmsoempresadao = new CadastroPcmsoDAO();
				
				try {
					objpcmsoempresadao.CadastrarPcmso(objpcmsoempresadto);
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
					
					txtPcmsoEmpresa.setText("");
					txtPcmsoDataInicial.setText("");
					txtPcmsoDataFinal.setText("");	
					
				}  catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "Erro cadastro PCMSO - tela!" + erro);
					// TODO Auto-generated catch block
					erro.printStackTrace();
				}
				
				
				}	
		
			}
		});
		btnPcmsoSalvar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnPcmsoSalvar.setBounds(451, 76, 89, 23);
		contentPane.add(btnPcmsoSalvar);
		
		JButton btnListarPcmso = new JButton("Listar");
		btnListarPcmso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;
					
					new ConexaoDAO();
					
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "Select * from pcmso";
					
					PreparedStatement pstm = con.prepareStatement(sql);
					
					ResultSet rs = pstm.executeQuery();
					
					DefaultTableModel model = (DefaultTableModel) tbListarPcmso.getModel();
					model.setNumRows(0);
					
					while (rs.next()) {
						
						model.addRow(new Object[]{rs.getString("idpcmso"), rs.getString("empresa"), rs.getString("datainicial"), rs.getString("datafinal")});
												
					}
					rs.close();
					con.close();
								
					
				} catch (Exception e2) {
					// TODO: handle exception
				} 	
				
				
			}
		});
		btnListarPcmso.setBounds(451, 217, 89, 23);
		contentPane.add(btnListarPcmso);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 240, 519, 109);
		contentPane.add(scrollPane);
		
		tbListarPcmso = new JTable();
		tbListarPcmso.setShowVerticalLines(false);
		tbListarPcmso.setShowHorizontalLines(false);
		tbListarPcmso.setShowGrid(false);
		tbListarPcmso.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Id", "Empresa", "Data Inicial", "Data Final"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbListarPcmso.getColumnModel().getColumn(0).setPreferredWidth(41);
		tbListarPcmso.getColumnModel().getColumn(1).setPreferredWidth(328);
		tbListarPcmso.getColumnModel().getColumn(2).setPreferredWidth(96);
		tbListarPcmso.getColumnModel().getColumn(3).setPreferredWidth(102);
		scrollPane.setViewportView(tbListarPcmso);
		
		JButton btnAtualizarcarPcmso = new JButton("Atualizar");
		btnAtualizarcarPcmso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtPcmsoId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");					
					
				} else {
								
				try {
					Connection con;
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "update pcmso set empresa=?, datainicial=?, datafinal=? where idpcmso=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
				
						pstm.setString(1, txtPcmsoEmpresa.getText());
						pstm.setString(2, txtPcmsoDataInicial.getText());
						pstm.setString(3, txtPcmsoDataFinal.getText());
						pstm.setString(4, txtPcmsoId.getText());
												
						pstm.execute();
						
						pstm.close();						
						con.close();
								
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");	
					
						txtPcmsoEmpresa.setText("");
						txtPcmsoDataInicial.setText("");
						txtPcmsoDataFinal.setText("");
						txtPcmsoId.setText("");
											
						
				} catch (Exception e2) {
					// TODO: handle exception
				}
				}
			}
		});
		btnAtualizarcarPcmso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarcarPcmso.setBounds(451, 110, 89, 23);
		contentPane.add(btnAtualizarcarPcmso);
		
		JButton btnExcluiPcmso = new JButton("Excluir");
		btnExcluiPcmso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;						
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "delete from pcmso where idpcmso=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1, txtPcmsoId.getText());
					
					pstm.execute();
					
					pstm.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dados excluidos");
					
					txtPcmsoId.setText("");
					txtPcmsoEmpresa.setText("");
					txtPcmsoDataInicial.setText("");
					txtPcmsoDataFinal.setText("");
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			
				}	
			}
		});
		btnExcluiPcmso.setBounds(451, 143, 89, 23);
		contentPane.add(btnExcluiPcmso);
		
		JButton btnPcmsoId = new JButton("Id");
		btnPcmsoId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtPcmsoId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
					
				}else {
					try {
						Connection con;						
						new ConexaoDAO();
						con = ConexaoDAO.fazconexaoBD();
						
						String sql = "select * from pcmso where idpcmso like?";
						
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setString(1, "%" + txtPcmsoId.getText());
					
						ResultSet rs = pstm.executeQuery();
						
						while (rs.next()) {
							
							txtPcmsoId.setText(rs.getString("idpcmso"));
							txtPcmsoEmpresa.setText(rs.getString("empresa"));
							txtPcmsoDataInicial.setText(rs.getString("datainicial"));
							txtPcmsoDataFinal.setText(rs.getString("datafinal"));
						}
						
						rs.close();
						con.close();

					} catch (Exception e1) {
					// TODO: handle exception
				}
				}
			}
		});
		btnPcmsoId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPcmsoId.setBounds(21, 89, 77, 23);
		contentPane.add(btnPcmsoId);
		
		txtPcmsoId = new JTextField();
		txtPcmsoId.setColumns(10);
		txtPcmsoId.setBounds(95, 90, 46, 21);
		contentPane.add(txtPcmsoId);
	}

}
