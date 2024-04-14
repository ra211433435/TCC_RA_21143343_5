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

import PastaDAO.CadastroFuncionarioDAO;
import PastaDAO.ConexaoDAO;
import PastaDTO.CadastroFuncionarioDTO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaFuncionario extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFuncionarioCargo;
	private JTextField txtFuncionarioEmpresa;
	private JTextField txtFuncionarioNome;
	private JTable tbListarFuncionario;
	private JTextField txtAbrirFuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFuncionario frame = new TelaFuncionario();
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
	public TelaFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblControleDeFuncionrios = new JLabel("Controle de Funcionários");
		lblControleDeFuncionrios.setFont(new Font("Dialog", Font.BOLD, 14));
		lblControleDeFuncionrios.setEnabled(true);
		lblControleDeFuncionrios.setBounds(173, 0, 200, 35);
		contentPane.add(lblControleDeFuncionrios);
		
		JLabel lblFuncionarioEmpresa = new JLabel("Empresa");
		lblFuncionarioEmpresa.setBounds(10, 187, 81, 14);
		contentPane.add(lblFuncionarioEmpresa);
		
		txtFuncionarioCargo = new JTextField();
		txtFuncionarioCargo.setColumns(10);
		txtFuncionarioCargo.setBounds(10, 156, 255, 20);
		contentPane.add(txtFuncionarioCargo);
		
		JButton btnFuncionarioSalvar = new JButton("Cadastrar");
		btnFuncionarioSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtFuncionarioNome.getText().equals("") ||txtFuncionarioEmpresa.getText().equals("")||txtFuncionarioCargo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor conferir se tem espaço em branco!!");
				
				}else {
					
				String cadastro_funcionarioempresa, cadastro_funcionarionome, cadastro_funcionariocargo;
				
				cadastro_funcionarioempresa = txtFuncionarioEmpresa.getText();
				cadastro_funcionarionome = txtFuncionarioNome.getText();
				cadastro_funcionariocargo = txtFuncionarioCargo.getText();
				
				CadastroFuncionarioDTO objcadastrofuncionariodto = new CadastroFuncionarioDTO();
				objcadastrofuncionariodto.setCadastro_funcionarioempresa(cadastro_funcionarioempresa);
				objcadastrofuncionariodto.setCadastro_funcionarionome(cadastro_funcionarionome);
				objcadastrofuncionariodto.setCadastro_funcionariocargo(cadastro_funcionariocargo);
				
				CadastroFuncionarioDAO objcfuncionariocargodao = new CadastroFuncionarioDAO();
				
				try {
					objcfuncionariocargodao.CadastrarFuncionario(objcadastrofuncionariodto);
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
					
					txtFuncionarioEmpresa.setText("");
					txtFuncionarioNome.setText("");
					txtFuncionarioCargo.setText("");
															
				}  catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "Erro cadastro Funcionario - tela!" + erro);
					// TODO Auto-generated catch block
					erro.printStackTrace();
				}
		
			}
			}
			});
		
		btnFuncionarioSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFuncionarioSalvar.setBounds(448, 89, 89, 23);
		contentPane.add(btnFuncionarioSalvar);
		
		txtFuncionarioEmpresa = new JTextField();
		txtFuncionarioEmpresa.setColumns(10);
		txtFuncionarioEmpresa.setBounds(10, 201, 255, 20);
		contentPane.add(txtFuncionarioEmpresa);
		
		JLabel lblFuncionarioCargo = new JLabel("Cargo/Função");
		lblFuncionarioCargo.setBounds(10, 142, 81, 14);
		contentPane.add(lblFuncionarioCargo);
		
		JLabel lblFuncionarioNome = new JLabel("Nome");
		lblFuncionarioNome.setBounds(10, 97, 81, 14);
		contentPane.add(lblFuncionarioNome);
		
		txtFuncionarioNome = new JTextField();
		txtFuncionarioNome.setColumns(10);
		txtFuncionarioNome.setBounds(10, 114, 366, 20);
		contentPane.add(txtFuncionarioNome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 253, 527, 125);
		contentPane.add(scrollPane);
		
		tbListarFuncionario = new JTable();
		tbListarFuncionario.setShowGrid(false);
		tbListarFuncionario.setShowHorizontalLines(false);
		tbListarFuncionario.setShowVerticalLines(false);
		tbListarFuncionario.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Id", "Nome", "Empresa", "Cargo"
			}
		));
		scrollPane.setViewportView(tbListarFuncionario);
		
		JButton btnListarFuncionario = new JButton("Listar");
		btnListarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;
					
					new ConexaoDAO();
					
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "Select * from funcionario";
					PreparedStatement pstm = con.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					
					DefaultTableModel model = (DefaultTableModel) tbListarFuncionario.getModel();
					model.setNumRows(0);
					
					while (rs.next()) {
						
						model.addRow(new Object[]{rs.getString("idfuncionario"), rs.getString("nome"), rs.getString("funcionarioempresa"), rs.getString("funcionariocargo")});
												
					}
					rs.close();
					con.close();
								
					
				} catch (Exception e2) {
					// TODO: handle exception
				} 		
			}
		});
		btnListarFuncionario.setBounds(448, 231, 89, 23);
		contentPane.add(btnListarFuncionario);
		
		txtAbrirFuncionario = new JTextField();
		txtAbrirFuncionario.setBounds(74, 57, 46, 20);
		contentPane.add(txtAbrirFuncionario);
		txtAbrirFuncionario.setColumns(10);
		
		JButton btnAbrirFuncionario = new JButton("Id");
		btnAbrirFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtAbrirFuncionario.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
					
				}else {
					try {
						Connection con;						
						new ConexaoDAO();
						con = ConexaoDAO.fazconexaoBD();
						
						String sql = "select * from funcionario where idfuncionario like?";
						
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setString(1, "%" + txtAbrirFuncionario.getText());
					
						ResultSet rs = pstm.executeQuery();
						
						while (rs.next()) {
							
							txtAbrirFuncionario.setText(rs.getString("idfuncionario"));
							txtFuncionarioNome.setText(rs.getString("nome"));
							txtFuncionarioEmpresa.setText(rs.getString("funcionarioempresa"));
							txtFuncionarioCargo.setText(rs.getString("funcionariocargo"));
						}
						
						rs.close();
						con.close();

					
				} catch (Exception e1) {
					// TODO: handle exception
				}
				
				}
				}
			});
		btnAbrirFuncionario.setBounds(10, 56, 64, 23);
		contentPane.add(btnAbrirFuncionario);
		
		JButton btnAtualizarFuncionario = new JButton("Atualizar");
		btnAtualizarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtAbrirFuncionario.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");					
					
				} else {
								
				try {
					Connection con;
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "update funcionario set nome=?, funcionarioempresa=?, funcionariocargo=? where idfuncionario=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
				
						pstm.setString(1, txtFuncionarioNome.getText());
						pstm.setString(2, txtFuncionarioCargo.getText());
						pstm.setString(3, txtFuncionarioEmpresa.getText());
						pstm.setString(4, txtAbrirFuncionario.getText());
												
						pstm.execute();
						
						pstm.close();						
						con.close();
								
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");	
					
						txtFuncionarioNome.setText("");
						txtFuncionarioCargo.setText("");
						txtFuncionarioEmpresa.setText("");
						txtAbrirFuncionario.setText("");
											
						
				} catch (Exception e2) {
					// TODO: handle exception
				} 		
				
				}
			
			}
		});
		btnAtualizarFuncionario.setBounds(448, 123, 89, 23);
		contentPane.add(btnAtualizarFuncionario);
		
		JButton btnExcluirFuncionario = new JButton("Excluir");
		btnExcluirFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					try {
						Connection con;						
						new ConexaoDAO();
						con = ConexaoDAO.fazconexaoBD();
						
						String sql = "delete from funcionario where idfuncionario=?";
						
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setString(1, txtAbrirFuncionario.getText());
						
						pstm.execute();
						
						pstm.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Dados excluidos");
						
						txtAbrirFuncionario.setText("");
						txtFuncionarioNome.setText("");
						txtFuncionarioCargo.setText("");
						txtFuncionarioEmpresa.setText("");
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

				}
				}
		});
		btnExcluirFuncionario.setBounds(448, 155, 89, 23);
		contentPane.add(btnExcluirFuncionario);
	}
}

