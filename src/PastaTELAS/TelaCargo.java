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

import PastaDAO.CadastroCargoDAO;
import PastaDAO.ConexaoDAO;
import PastaDTO.CadastroCargoDTO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class TelaCargo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCadastroCargo;
	private JTextField txtCadastroDescricao;
	private JTable tbListarCargo;
	private JTextField txtBuscaId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCargo frame = new TelaCargo();
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
	public TelaCargo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblControleDosCargos = new JLabel("CONTROLE DOS CARGOS/FUNÇÕES");
		lblControleDosCargos.setFont(new Font("Dialog", Font.BOLD, 12));
		lblControleDosCargos.setEnabled(true);
		lblControleDosCargos.setBounds(175, 11, 229, 35);
		contentPane.add(lblControleDosCargos);
		
		JLabel lblCadastroCargo = new JLabel("Cargo/Função:");
		lblCadastroCargo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCadastroCargo.setBounds(22, 97, 81, 14);
		contentPane.add(lblCadastroCargo);
		
		JLabel lblCAdastroDescricao = new JLabel("Descrição:");
		lblCAdastroDescricao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCAdastroDescricao.setBounds(22, 146, 81, 14);
		contentPane.add(lblCAdastroDescricao);
		
		txtCadastroCargo = new JTextField();
		txtCadastroCargo.setBounds(22, 110, 293, 20);
		contentPane.add(txtCadastroCargo);
		txtCadastroCargo.setColumns(10);
		
		txtCadastroDescricao = new JTextField();
		txtCadastroDescricao.setBounds(22, 160, 293, 51);
		contentPane.add(txtCadastroDescricao);
		txtCadastroDescricao.setColumns(10);
		
		JButton btnSalvarDescricao = new JButton("Cadastrar");
		btnSalvarDescricao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSalvarDescricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if(txtCadastroCargo.getText().equals("") ||txtCadastroDescricao.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nome do Cargo ou Descrição em branco!");
					
				}else {
				
				String cadastro_nomecargo, cadastro_descricaocargo;
				
				cadastro_nomecargo = txtCadastroCargo.getText();
				cadastro_descricaocargo = txtCadastroDescricao.getText();
				
				CadastroCargoDTO objcadastrocargodto = new CadastroCargoDTO();
				objcadastrocargodto.setCadastro_nomecargo(cadastro_nomecargo);
				objcadastrocargodto.setCadastro_descricaocargo(cadastro_descricaocargo);
				
				CadastroCargoDAO objcadastrcargodao = new CadastroCargoDAO();
				
				try {
					objcadastrcargodao.CadastrarCargo(objcadastrocargodto);
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
					
					txtCadastroCargo.setText("");
					txtCadastroDescricao.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			}
		});
		btnSalvarDescricao.setBounds(398, 94, 117, 21);
		contentPane.add(btnSalvarDescricao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(22, 242, 478, 142);
		contentPane.add(scrollPane);
		
		tbListarCargo = new JTable();
		tbListarCargo.setBackground(new Color(255, 255, 255));
		tbListarCargo.setForeground(new Color(255, 255, 255));
		tbListarCargo.setSurrendersFocusOnKeystroke(true);
		tbListarCargo.setShowVerticalLines(false);
		tbListarCargo.setShowHorizontalLines(false);
		tbListarCargo.setShowGrid(false);
		tbListarCargo.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "Cargo", "Descrição"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbListarCargo.getColumnModel().getColumn(0).setPreferredWidth(36);
		tbListarCargo.getColumnModel().getColumn(1).setPreferredWidth(224);
		tbListarCargo.getColumnModel().getColumn(2).setPreferredWidth(358);
		scrollPane.setViewportView(tbListarCargo);
		
		JButton btnListarCargo = new JButton("Listar");
		btnListarCargo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnListarCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
						Connection con;
							
						new ConexaoDAO();
						con = ConexaoDAO.fazconexaoBD();
						
						String sql = "Select * from cargo";
						PreparedStatement pstm = con.prepareStatement(sql);
						
						ResultSet rs = pstm.executeQuery();
						
						DefaultTableModel model = (DefaultTableModel) tbListarCargo.getModel();
						model.setNumRows(0);
						
						while (rs.next()) {
							
							model.addRow(new Object[]{rs.getString("idcargo"), rs.getString("cargo"), rs.getString("descricao")});
													
						}
						rs.close();
						con.close();
									
					} catch (Exception e2) {
						// TODO: handle exception
					} 		
				
			}
		});
		btnListarCargo.setBounds(411, 221, 89, 23);
		contentPane.add(btnListarCargo);
		
		JButton btnAtualizarcargo = new JButton("Atualizar");
		btnAtualizarcargo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarcargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtBuscaId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");					
					
				} else {
								
				try {
					Connection con;
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "update cargo set cargo=?, descricao=? where idcargo=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
				
						pstm.setString(1, txtCadastroCargo.getText());
						pstm.setString(2, txtCadastroDescricao.getText());
						pstm.setString(3, txtBuscaId.getText());
												
						pstm.execute();
						
						pstm.close();						
						con.close();
								
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");	
					
						txtBuscaId.setText("");
						txtCadastroCargo.setText("");
						txtCadastroDescricao.setText("");
					
											
						
				} catch (Exception e2) {
					// TODO: handle exception
				} 		
				
				}
			}
		});
		btnAtualizarcargo.setBounds(398, 126, 117, 23);
		contentPane.add(btnAtualizarcargo);
		
		txtBuscaId = new JTextField();
		txtBuscaId.setColumns(10);
		txtBuscaId.setBounds(69, 65, 46, 21);
		contentPane.add(txtBuscaId);
		
		JButton btnBuscaId = new JButton("id");
		btnBuscaId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscaId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtBuscaId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
					
				} else {

				}
				
				try {
					Connection con;						
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "select * from cargo where idcargo like ?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1, "%" + txtBuscaId.getText());
				
					ResultSet rs = pstm.executeQuery();
						
						while (rs.next()) {
							txtBuscaId.setText(rs.getString("idcargo"));
							txtCadastroCargo.setText(rs.getString("cargo"));
							txtCadastroDescricao.setText(rs.getString("descricao"));
						}
						
						rs.close();
						con.close();

					
						
						
						JOptionPane.showMessageDialog(null, "!");	
												
				} catch (Exception e2) {
					// TODO: handle exception
				}
								
			}
		});
		btnBuscaId.setBounds(22, 63, 46, 23);
		contentPane.add(btnBuscaId);
		
		JButton btnExcluiCargo = new JButton("Excluir");
		btnExcluiCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;						
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "delete from cargo where idcargo=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1, txtBuscaId.getText());
					
					pstm.execute();
					
					pstm.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dados excluidos");
					
					txtBuscaId.setText("");
					txtCadastroCargo.setText("");
					txtCadastroDescricao.setText("");
								
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnExcluiCargo.setBounds(398, 159, 117, 23);
		contentPane.add(btnExcluiCargo);
	
			}
}