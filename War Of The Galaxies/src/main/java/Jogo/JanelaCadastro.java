package Jogo;

import java.awt.Color;
import java.awt.Dimension;
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
	private JTextField txtNome;
	private JTable tabela;
	private JPanel painel;
	private Sounds sound;

	private boolean cadastroFase;

	public JanelaCadastro() {
		configuracao();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnNovoJogo) {
			btnNovoJogo.setVisible(false);
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
			tabela();
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
	
	//public void paint(Graphics g) {
	//	// Metodo para pintar na tela do jogo
	//	Graphics2D graficos = (Graphics2D) g;
	//	graficos.drawImage(fundo, 0, 0, null);
	//}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2d =  g.create(); //Faz a cópia
        g2d.fillOval(100, 100, 200, 200);
        g2d.drawImage(fundo, 0, 0, null);
        g2d.dispose(); //libera a cópia
    }
	
	public void configuracao() {
		sound = new Sounds();
		
		
		SpringLayout layout = new SpringLayout();
		
		ImageIcon ref = new ImageIcon("Imagens-Jogo\\fundo-Cadastro.jpeg");
		fundo = ref.getImage();
		
		cadastroDAO = new CadastroDAO();

		btnNovoJogo = new JButton("Novo Jogo");
		btnNovoJogo.setBounds(10, 10, 100, 100);

		painel = new JPanel(layout);
		painel.setPreferredSize(new Dimension(500, 500));
		painel.setBackground(Color.BLACK);
		
		
		btnRecords = new JButton("Records");
		btnRecords.setBounds(100, 100, 100, 100);
		
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

		GridBagConstraints regras = new GridBagConstraints();
		GridBagLayout grid = new GridBagLayout();
	
		setLayout(grid);
		setOpaque(true);
		
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
		
		
		GridBagConstraints regras2 = new GridBagConstraints();
		regras2.anchor = GridBagConstraints.NORTHEAST;
		regras2.gridx = 1;
		regras2.gridy = 1;
		regras2.weightx= 5;
		regras2.insets = new Insets(270, 270, 0, 450);
		add(btnEntrar,regras2);
       
		regras2.fill = GridBagConstraints.HORIZONTAL;
		regras2.gridx = 1;
		regras2.gridy = 1;
		regras2.weightx= 2;
		regras2.weighty= 2;
		regras2.insets = new Insets(275, 400, 0, 540);
		add(txtNome, regras2);
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
