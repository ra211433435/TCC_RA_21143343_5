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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import PastaDAO.CadastroPgrDAO;
import PastaDAO.ConexaoDAO;
import PastaDTO.CadastroPgrDTO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaPGR extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPgrEmpresa;
	private JTextField txtPgrInicial;
	private JTextField txtPgrFinal;
	private JTable tbPgr;
	private JTextField txtPgrId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPGR frame = new TelaPGR();
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
	public TelaPGR() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPgrEmpresa = new JLabel("Empresa");
		lblPgrEmpresa.setBounds(10, 111, 81, 14);
		contentPane.add(lblPgrEmpresa);
		
		txtPgrEmpresa = new JTextField();
		txtPgrEmpresa.setColumns(10);
		txtPgrEmpresa.setBounds(10, 126, 262, 20);
		contentPane.add(txtPgrEmpresa);
		
		JLabel lblPgrInicial = new JLabel("Data Inicial");
		lblPgrInicial.setBounds(10, 157, 81, 14);
		contentPane.add(lblPgrInicial);
		
		txtPgrInicial = new JTextField();
		txtPgrInicial.setColumns(10);
		txtPgrInicial.setBounds(10, 170, 120, 20);
		contentPane.add(txtPgrInicial);
		
		JLabel lblPgrFinal = new JLabel("Data Final");
		lblPgrFinal.setBounds(152, 157, 81, 14);
		contentPane.add(lblPgrFinal);
		
		txtPgrFinal = new JTextField();
		txtPgrFinal.setColumns(10);
		txtPgrFinal.setBounds(152, 170, 120, 20);
		contentPane.add(txtPgrFinal);
		
		JButton btnPgrSalvar = new JButton("Cadastrar");
		btnPgrSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtPgrEmpresa.getText().equals("") ||txtPgrInicial.getText().equals("") || txtPgrFinal.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Verifique se tem campos em branco!");
				
				}else {
										
				String cadastro_pgrempresa, cadastro_pgrdatainicial, cadastro_pgrdatafinal;
				
				cadastro_pgrempresa = txtPgrEmpresa.getText();
				cadastro_pgrdatainicial = txtPgrInicial.getText();
				cadastro_pgrdatafinal = txtPgrFinal.getText();
				
				CadastroPgrDTO objpgrempresadto = new CadastroPgrDTO();
				objpgrempresadto.setCadastro_pgrempresa(cadastro_pgrempresa);
				objpgrempresadto.setCadastro_pgrdatainicial(cadastro_pgrdatainicial);
				objpgrempresadto.setCadastro_pgrdatafinal(cadastro_pgrdatafinal);
				
				CadastroPgrDAO objpgrempresadao = new CadastroPgrDAO();
				
				try {
					objpgrempresadao.CadastrarPgr(objpgrempresadto);
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
					
					txtPgrEmpresa.setText("");
					txtPgrInicial.setText("");
					txtPgrFinal.setText("");
					
										
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				}	
			}
		});
		btnPgrSalvar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnPgrSalvar.setBounds(410, 90, 89, 23);
		contentPane.add(btnPgrSalvar);
		
		JLabel lblControleDoPgr = new JLabel("Controle do PGR");
		lblControleDoPgr.setFont(new Font("Dialog", Font.BOLD, 14));
		lblControleDoPgr.setEnabled(true);
		lblControleDoPgr.setBounds(190, 0, 200, 35);
		contentPane.add(lblControleDoPgr);
		
		JButton btnListarPgr = new JButton("Listar");
		btnListarPgr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;
					
					new ConexaoDAO();
					
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "Select * from pgr";
					
					PreparedStatement pstm = con.prepareStatement(sql);
					
					ResultSet rs = pstm.executeQuery();
					
					DefaultTableModel model = (DefaultTableModel) tbPgr.getModel();
					model.setNumRows(0);
					
					while (rs.next()) {
						
						model.addRow(new Object[]{rs.getString("idpgr"), rs.getString("empresa"), rs.getString("datainicial"), rs.getString("datafinal")});
												
					}
					rs.close();
					con.close();
								
					
				} catch (Exception e2) {
					// TODO: handle exception
				} 	
				
				
				
				
				
				
				
			}
		});
		btnListarPgr.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnListarPgr.setBounds(429, 219, 89, 23);
		contentPane.add(btnListarPgr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 240, 508, 143);
		contentPane.add(scrollPane);
		
		tbPgr = new JTable();
		tbPgr.setShowVerticalLines(false);
		tbPgr.setShowHorizontalLines(false);
		tbPgr.setShowGrid(false);
		tbPgr.setModel(new DefaultTableModel(
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
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbPgr.getColumnModel().getColumn(0).setPreferredWidth(46);
		tbPgr.getColumnModel().getColumn(1).setPreferredWidth(343);
		tbPgr.getColumnModel().getColumn(2).setPreferredWidth(97);
		tbPgr.getColumnModel().getColumn(3).setPreferredWidth(98);
		tbPgr.setFillsViewportHeight(true);
		scrollPane.setViewportView(tbPgr);
		
		JButton btnAtualizarPgr = new JButton("Atualizar");
		btnAtualizarPgr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtPgrId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");					
					
				} else {
								
				try {
					Connection con;
					
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "update pgr set empresa=?, datainicial=?, datafinal=? where idpgr=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
				
						pstm.setString(1, txtPgrEmpresa.getText());
						pstm.setString(2, txtPgrInicial.getText());
						pstm.setString(3, txtPgrFinal.getText());
						pstm.setString(4, txtPgrId.getText());
												
						pstm.execute();
						
						pstm.close();						
						con.close();
								
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");	
					
						txtPgrEmpresa.setText("");
						txtPgrInicial.setText("");
						txtPgrFinal.setText("");
						
											
						
				} catch (Exception e2) {
					// TODO: handle exception
				}
				}
			}
		});
		btnAtualizarPgr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarPgr.setBounds(410, 121, 93, 23);
		contentPane.add(btnAtualizarPgr);
		
		JButton btnExcluirPgr = new JButton("Excluir");
		btnExcluirPgr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;						
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "delete from pgr where idpgr=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1, txtPgrId.getText());
					
					pstm.execute();
					
					pstm.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dados excluidos");
					
					txtPgrId.setText("");
					txtPgrEmpresa.setText("");
					txtPgrInicial.setText("");
					txtPgrFinal.setText("");
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExcluirPgr.setBounds(410, 153, 89, 23);
		contentPane.add(btnExcluirPgr);
		
		JButton btnAbrirPgrId = new JButton("Id");
		btnAbrirPgrId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtPgrId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
					
				}else {
					try {
						Connection con;						
						new ConexaoDAO();
						con = ConexaoDAO.fazconexaoBD();
						
						String sql = "select * from pgr where idpgr like?";
						
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setString(1, "%" + txtPgrId.getText());
					
						ResultSet rs = pstm.executeQuery();
						
						while (rs.next()) {
							
							txtPgrId.setText(rs.getString("idpgr"));
							txtPgrEmpresa.setText(rs.getString("empresa"));
							txtPgrInicial.setText(rs.getString("datainicial"));
							txtPgrFinal.setText(rs.getString("datafinal"));
						}
						
						rs.close();
						con.close();

					} catch (Exception e1) {
					// TODO: handle exception
				}
				}
			}
		});
		btnAbrirPgrId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAbrirPgrId.setBounds(10, 77, 72, 23);
		contentPane.add(btnAbrirPgrId);
		
		txtPgrId = new JTextField();
		txtPgrId.setColumns(10);
		txtPgrId.setBounds(84, 79, 46, 21);
		contentPane.add(txtPgrId);
	}
}
