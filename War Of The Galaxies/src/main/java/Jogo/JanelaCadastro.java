package Jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
	private JButton btnVoltar;

	private JLabel desenvol;
	private JLabel desenvolvedor1;
	private JLabel desenvolvedor2;
	private JLabel desenvolvedor3;
	private JLabel desenvolvedor4;
	private JLabel versao;
	
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
		} else if (e.getSource() == btnEntrar) {
			cadastroFase = true;
			nome = txtNome.getText();
			cadastroDAO.save(nome);
		} else if (e.getSource() == btnRecords) {
			btnNovoJogo.setVisible(false);
			btnRecords.setVisible(false);
			btnInfo.setVisible(false);
			tabela();
		} else if (e.getSource() == btnInfo) {
			btnNovoJogo.setVisible(false);
			btnRecords.setVisible(false);
			btnInfo.setVisible(false);
			btnVoltar.setVisible(true);
			desenvolvedores(true);
		} else if (e.getSource() == btnVoltar) {
			btnNovoJogo.setVisible(true);
			btnRecords.setVisible(true);
			btnInfo.setVisible(true);
			btnVoltar.setVisible(false);
			desenvolvedores(false);
		}
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnNovoJogo) {
			btnNovoJogo.setBackground(new Color(180, 230, 230));
		}
		else if (e.getSource() == btnRecords) {
			btnRecords.setBackground(new Color(180, 230, 230));
		}
		else if (e.getSource() == btnInfo) {
			btnInfo.setBackground(new Color(180, 230, 230));
		}
		else if (e.getSource() == btnEntrar) {
			btnEntrar.setBackground(new Color(180, 230, 230));
		}
		else if (e.getSource() == btnVoltar) {
			btnVoltar.setBackground(new Color(180, 230, 230));
		}
		
	}

	public void mouseExited(MouseEvent e) {
		JButton botaoCor = new JButton(); // criado botão só para colocar a cor original. Verificar outro forma
		if (e.getSource() == btnNovoJogo) {
			btnNovoJogo.setBackground(botaoCor.getBackground());
		}
		if (e.getSource() == btnRecords) {
			btnRecords.setBackground(botaoCor.getBackground());
		}
		if (e.getSource() == btnInfo) {
			btnInfo.setBackground(botaoCor.getBackground());
		}
		if (e.getSource() == btnEntrar) {
			btnEntrar.setBackground(botaoCor.getBackground());
		}
		if (e.getSource() == btnVoltar) {
			btnVoltar.setBackground(botaoCor.getBackground());
		}
	}

	public void mousePressed(MouseEvent e) 		{ }
	public void mouseReleased(MouseEvent e)  	{ }

	public boolean isCadastroFase() {
		return cadastroFase;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2d = g.create();
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

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(100, 100, 100, 100);
		btnVoltar.setVisible(false);

		btnInfo = new JButton("Informações");
		btnInfo.setBounds(100, 100, 100, 100);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(100, 100, 100, 100);
		btnEntrar.setVisible(false);

		desenvol = new JLabel("DESENVOLVEDORES");
		desenvolvedor1 = new JLabel("FABIANO ALBINO FERREIRA");
		desenvolvedor2 = new JLabel("HELTON CARLOS DE MOURA");
		desenvolvedor3 = new JLabel("JONATHAN GOMES DOS SANTOS");
		desenvolvedor4 = new JLabel("VALDECIR MUNHAM JUNIOR");

		Font fonteVersao = new Font("arial", Font.BOLD, 15);
		versao = new JLabel("Versão 2.0");
		versao.setFont(fonteVersao);
		versao.setForeground(Color.RED);

		txtNome = new JTextField("Nome");
		txtNome.setVisible(false);
		txtNome.setBounds(10, 10, 10, 10);

		cadastroFase = false;

		btnNovoJogo.addMouseListener(this);
		btnEntrar.addMouseListener(this);
		btnRecords.addMouseListener(this);
		btnInfo.addMouseListener(this);
		btnVoltar.addMouseListener(this);

		GridBagConstraints regras = new GridBagConstraints();
		GridBagLayout grid = new GridBagLayout();

		setLayout(grid);

		regras.fill = GridBagConstraints.NONE;
		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 100, 200);
		add(btnNovoJogo, regras);

		regras.anchor = GridBagConstraints.SOUTH;
		regras.weightx = 7;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 60, 200);
		add(btnRecords, regras);

		regras.anchor = GridBagConstraints.SOUTH;
		regras.weightx = 7;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 20, 200);
		add(btnInfo, regras);

		regras.anchor = GridBagConstraints.NORTHEAST;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.weightx = 5;
		regras.insets = new Insets(270, 270, 0, 450);
		add(btnEntrar, regras);

		regras.anchor = GridBagConstraints.SOUTH;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.weightx = 5;
		regras.insets = new Insets(420, 270, 0, 270);
		add(btnVoltar, regras);

		regras.fill = GridBagConstraints.HORIZONTAL;
		regras.anchor = GridBagConstraints.NORTHEAST;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.weightx = 2;
		regras.weighty = 2;
		regras.insets = new Insets(273, 370, 0, 540);
		add(txtNome, regras);
	}

	public void desenvolvedores(boolean visivel) {
		GridBagConstraints regras = new GridBagConstraints();
		Font fonte = new Font("arial", Font.BOLD, 20);

		desenvol.setFont(fonte);
		desenvol.setForeground(new Color(255, 140, 0));
		desenvol.setVisible(visivel);

		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 180, 200);
		add(desenvol, regras);

		desenvolvedor1.setFont(fonte);
		desenvolvedor1.setForeground(new Color(255, 140, 0));
		desenvolvedor1.setVisible(visivel);

		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 100, 200);
		add(desenvolvedor1, regras);

		desenvolvedor2.setFont(fonte);
		desenvolvedor2.setForeground(new Color(255, 140, 0));
		desenvolvedor2.setVisible(visivel);

		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 60, 200);
		add(desenvolvedor2, regras);

		desenvolvedor3.setFont(fonte);
		desenvolvedor3.setForeground(new Color(255, 140, 0));
		desenvolvedor3.setVisible(visivel);

		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, 20, 200);
		add(desenvolvedor3, regras);

		desenvolvedor4.setFont(fonte);
		desenvolvedor4.setForeground(new Color(255, 140, 0));
		desenvolvedor4.setVisible(visivel);

		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 4;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.insets = new Insets(0, 200, -20, 200);
		add(desenvolvedor4, regras);
	}

	@SuppressWarnings("deprecation")
	public void tabela() {
		LinkedList<Cadastro> cadastros = new LinkedList<Cadastro>();
		cadastros = cadastroDAO.buscar();
		String colunas[] = { "Nome", "Score" };

		tabela = new JTable(cadastros.size(), 2);
		tabela.enable(false);

		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

		for (Cadastro cadastro : cadastros)
			modelo.addRow(new String[] { cadastro.nome, String.valueOf(cadastro.score) });

		GridBagConstraints regrasTabela = new GridBagConstraints();
		regrasTabela.anchor = GridBagConstraints.NORTHEAST;
		regrasTabela.gridx = 1;
		regrasTabela.gridy = 1;
		regrasTabela.weightx = 10;
		regrasTabela.insets = new Insets(70, 70, 0, 450);

		tabela.setModel(modelo);
		add(tabela, regrasTabela);
	}
}
