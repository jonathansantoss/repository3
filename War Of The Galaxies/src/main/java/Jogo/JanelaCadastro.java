package Jogo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import DAO.CadastroDAO;

@SuppressWarnings("serial")
public class JanelaCadastro extends JPanel implements MouseListener {
	
	public int score;
	public String nome;
	
	private CadastroDAO cadastroDAO;
	private Image fundo;
	
	private JButton btnNovoJogo;
	private JButton btnEntrar;
	private JButton btnRecords;
	private JButton btnInfo;
	
	private JLabel nomes;
	
	private JTextField txtNome;
	private JTable tabela;

	private boolean cadastroFase;

	public JanelaCadastro() {
		configuracao();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnNovoJogo) {
			btnNovoJogo.setVisible(false);
			btnRecords.setVisible(false);
			btnInfo.setVisible(false);
			btnEntrar.setVisible(true);
			txtNome.setVisible(true);
		}
		else if (e.getSource() == btnEntrar) {
			cadastroFase = true;
			nome = txtNome.getText();
			cadastroDAO.save(nome);
		}
		else if (e.getSource() == btnRecords) {
			btnNovoJogo.setVisible(false);
			btnRecords.setVisible(false);
			btnInfo.setVisible(false);
			tabela();
		}
		else if (e.getSource() == btnInfo) {
			btnNovoJogo.setVisible(false);
			btnRecords.setVisible(false);
			btnInfo.setVisible(false);
			desenvolvedores();
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean isCadastroFase() {
		return cadastroFase;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2d =  g.create();
        g2d.fillOval(100, 100, 200, 200);
        g2d.drawImage(fundo, 0, 0, null);
        g2d.dispose(); 
    }
	
	public void configuracao() {		
		ImageIcon ref = new ImageIcon("Imagens-Jogo\\fundo-Cadastro.jpeg");
		fundo = ref.getImage();
		
		cadastroDAO = new CadastroDAO();

		btnNovoJogo = new JButton("Novo Jogo");
		btnNovoJogo.setBounds(10, 10, 100, 100);
		
		btnRecords = new JButton("Records");
		btnRecords.setBounds(100, 100, 100, 100);
		
		btnInfo = new JButton("Informações");
		btnInfo.setBounds(100, 100, 100, 100);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(100, 100, 100, 100);
		btnEntrar.setVisible(false);
		
		txtNome = new JTextField(); 
		txtNome.setVisible(false);
		txtNome.setBounds(10, 10, 10, 10);

		cadastroFase = false;

		btnNovoJogo.addMouseListener(this);
		btnEntrar.addMouseListener(this);
		btnRecords.addMouseListener(this);
		btnInfo.addMouseListener(this);

		GridBagConstraints regras = new GridBagConstraints();
		GridBagLayout grid = new GridBagLayout();
	
		setLayout(grid);
		
		regras.fill = GridBagConstraints.NONE;
		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 100, 200);
		add(btnNovoJogo,regras);
		
		regras.anchor = GridBagConstraints.SOUTH;
		regras.weightx = 7;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 50, 200);
		add(btnRecords,regras);
		
		regras.anchor = GridBagConstraints.SOUTH;
		regras.weightx = 7;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 10, 200);
		add(btnInfo,regras);
		
		regras.anchor = GridBagConstraints.NORTHEAST;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.weightx= 5;
		regras.insets = new Insets(270, 270, 0, 450);
		add(btnEntrar,regras);
       
		regras.fill = GridBagConstraints.HORIZONTAL;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.weightx= 2;
		regras.weighty= 2;
		regras.insets = new Insets(275, 400, 0, 540);
		add(txtNome, regras);
	}
	
	
	public void desenvolvedores() {
		
		GridBagConstraints regras = new GridBagConstraints();
		Font fonte = new Font("arial", Font.BOLD, 20);
		
		nomes = new JLabel("DESENVOLVEDORES");
		nomes.setFont(fonte);
		nomes.setForeground(new Color(255, 140, 0));
		
		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 180, 200);
		add(nomes,regras);
		
		
		nomes = new JLabel("FABIANO ALBINO FERREIRA");
		nomes.setFont(fonte);
		nomes.setForeground(new Color(255, 140, 0));
		
		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 100, 200);
		add(nomes,regras);
		
		
		nomes = new JLabel("HELTON CARLOS DE MOURA");
		nomes.setFont(fonte);
		nomes.setForeground(new Color(255, 140, 0));
		
		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 20, 200);
		add(nomes,regras);
		
		
		nomes = new JLabel("JONATHAN GOMES DOS SANTOS");
		nomes.setFont(fonte);
		nomes.setForeground(new Color(255, 140, 0));
		
		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, -40, 200);
		add(nomes,regras);
		
		nomes = new JLabel("VALDECIR MUNHAM JUNIOR");
		nomes.setFont(fonte);
		nomes.setForeground(new Color(255, 140, 0));
		
		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, -80, 200);
		add(nomes,regras);
	}
	
	
	@SuppressWarnings("deprecation")
	public void tabela() {
		LinkedList<Cadastro> cadastros = new LinkedList<Cadastro>();
		cadastros = cadastroDAO.buscar();
		String colunas[] = {"Nome","Score"};

		tabela = new JTable(cadastros.size(),2);
		tabela.enable(false);
		
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		
		for (Cadastro cadastro : cadastros) 
			modelo.addRow(new String[] {cadastro.nome,String.valueOf(cadastro.score)});
		
		GridBagConstraints regrasTabela = new GridBagConstraints();
		regrasTabela.anchor = GridBagConstraints.NORTHEAST;
		regrasTabela.gridx = 1;
		regrasTabela.gridy = 1;
		regrasTabela.weightx= 10;
		regrasTabela.insets = new Insets(70, 70, 0, 450);

		tabela.setModel(modelo);
		add(tabela, regrasTabela);
	}
}
