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

import PastaDAO.CadastroEmpresaDAO;
import PastaDAO.ConexaoDAO;
import PastaDTO.CadastroEmpresaDTO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class TelaEmpresa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCnpjNumero;
	private JTextField txtCnpjEmpresa;
	private JTable tbListarEmpresa;
	private JTextField txtAbrirEmpresa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEmpresa frame = new TelaEmpresa();
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
	public TelaEmpresa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblControleDasEmpresas = new JLabel("Controle das Empresas");
		lblControleDasEmpresas.setFont(new Font("Dialog", Font.BOLD, 14));
		lblControleDasEmpresas.setEnabled(true);
		lblControleDasEmpresas.setBounds(175, 0, 180, 35);
		contentPane.add(lblControleDasEmpresas);
		
		JLabel lblCnpjEmpresa = new JLabel("CNPJ");
		lblCnpjEmpresa.setBounds(31, 109, 42, 14);
		contentPane.add(lblCnpjEmpresa);
		
		JLabel lblNomeEmpresa = new JLabel("Empresa");
		lblNomeEmpresa.setBounds(31, 157, 57, 14);
		contentPane.add(lblNomeEmpresa);
		
		txtCnpjNumero = new JTextField();
		txtCnpjNumero.setColumns(10);
		txtCnpjNumero.setBounds(31, 123, 135, 23);
		contentPane.add(txtCnpjNumero);
		
		txtCnpjEmpresa = new JTextField();
		txtCnpjEmpresa.setColumns(10);
		txtCnpjEmpresa.setBounds(31, 171, 224, 23);
		contentPane.add(txtCnpjEmpresa);
		
		JButton btnCadastrarEmpresa = new JButton("Cadastrar");
		btnCadastrarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtCnpjNumero.getText().equals("") ||txtCnpjEmpresa.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O CNPJ ou Nome da Empresa está em branco!");
				
				}else {
					
				String cadastro_cnpj, cadastro_nomeempresa;
				
				cadastro_cnpj = txtCnpjNumero.getText();
				cadastro_nomeempresa = txtCnpjEmpresa.getText();
				
				CadastroEmpresaDTO objcadastroempresadto = new CadastroEmpresaDTO();
				objcadastroempresadto.setCadastro_cnpjempresa(cadastro_cnpj);
				objcadastroempresadto.setCadastro_nomeempresa(cadastro_nomeempresa);
				
				CadastroEmpresaDAO objcadastroempresadao = new CadastroEmpresaDAO();
				
				try {
					objcadastroempresadao.CadastrarEmpresa(objcadastroempresadto);
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
					
					txtCnpjNumero.setText("");
					txtCnpjEmpresa.setText("");
					
	
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "Erro cadastro Empresa - tela!" + erro);
					// TODO Auto-generated catch block
					erro.printStackTrace();
				}
				}
				
			}
		});
		btnCadastrarEmpresa.setBounds(440, 75, 89, 23);
		contentPane.add(btnCadastrarEmpresa);
		
		JButton btnAbrirEmpresa = new JButton("Id");
		btnAbrirEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAbrirEmpresa.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
					
				}else {
					try {
						Connection con;						
						new ConexaoDAO();
						con = ConexaoDAO.fazconexaoBD();
						
						String sql = "select * from empresa where idempresa like?";
						
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setString(1, "%" + txtAbrirEmpresa.getText());
					
						ResultSet rs = pstm.executeQuery();
						
						while (rs.next()) {
							
							txtAbrirEmpresa.setText(rs.getString("idempresa"));
							txtCnpjNumero.setText(rs.getString("cnpj"));
							txtCnpjEmpresa.setText(rs.getString("nome"));
							
						}
						rs.close();
						con.close();
	
				} catch (Exception e1) {
					// TODO: handle exception
				} 		
			}	
			}
		});
		btnAbrirEmpresa.setBounds(31, 75, 64, 23);
		contentPane.add(btnAbrirEmpresa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 238, 498, 141);
		contentPane.add(scrollPane);
		
		tbListarEmpresa = new JTable();
		tbListarEmpresa.setForeground(new Color(0, 0, 0));
		tbListarEmpresa.setShowGrid(false);
		tbListarEmpresa.setShowVerticalLines(false);
		tbListarEmpresa.setShowHorizontalLines(false);
		tbListarEmpresa.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "CNPJ", "Nome"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbListarEmpresa.getColumnModel().getColumn(0).setPreferredWidth(41);
		tbListarEmpresa.getColumnModel().getColumn(1).setPreferredWidth(175);
		tbListarEmpresa.getColumnModel().getColumn(2).setPreferredWidth(350);
		scrollPane.setViewportView(tbListarEmpresa);
		
		JButton btnAtualizarEmpresa = new JButton("Atualizar");
		btnAtualizarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtAbrirEmpresa.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");					
					
				} else {
								
				try {
					Connection con;
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "update empresa set cnpj=?, nome=? where idempresa=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
				
						pstm.setString(1, txtCnpjNumero.getText());
						pstm.setString(2, txtCnpjEmpresa.getText());
						pstm.setString(3, txtAbrirEmpresa.getText());
												
						pstm.execute();
						
						pstm.close();						
						con.close();
								
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");	
					
						txtCnpjNumero.setText("");
						txtCnpjEmpresa.setText("");
						txtAbrirEmpresa.setText("");
											
						
				} catch (Exception e2) {
					// TODO: handle exception
				}
				}
				}	
		});
		btnAtualizarEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarEmpresa.setBounds(440, 109, 89, 23);
		contentPane.add(btnAtualizarEmpresa);
		
		JButton btnExcluirEmpresa = new JButton("Excluir");
		btnExcluirEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;						
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "delete from empresa where idempresa=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1, txtAbrirEmpresa.getText());
					
					pstm.execute();
					
					pstm.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dados excluidos");
					
					txtAbrirEmpresa.setText("");
					txtCnpjNumero.setText("");
					txtCnpjEmpresa.setText("");
								
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExcluirEmpresa.setBounds(440, 142, 89, 23);
		contentPane.add(btnExcluirEmpresa);
		
		txtAbrirEmpresa = new JTextField();
		txtAbrirEmpresa.setColumns(10);
		txtAbrirEmpresa.setBounds(97, 76, 46, 21);
		contentPane.add(txtAbrirEmpresa);
		
		JButton btnListarCnpj = new JButton("Listar");
		btnListarCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;
					
					new ConexaoDAO();
					
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "Select * from empresa";
					PreparedStatement pstm = con.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					
					DefaultTableModel model = (DefaultTableModel) tbListarEmpresa.getModel();
					model.setNumRows(0);
					
					while (rs.next()) {
						
						model.addRow(new Object[]{rs.getString("idempresa"), rs.getString("cnpj"), rs.getString("nome")});
												
					}
					rs.close();
					con.close();
								
					
				} catch (Exception e2) {
					// TODO: handle exception				
				}	
			}
		});
		btnListarCnpj.setBounds(440, 214, 89, 23);
		contentPane.add(btnListarCnpj);
	}
}
