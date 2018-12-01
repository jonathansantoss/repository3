package Jogo;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.CadastroDAO;

public class Ranking extends JPanel{
	private Image fundo;
	private CadastroDAO cadastroDAO;
	private JTable tabela;
	
	public Ranking() {
		configuracao();
		tabela();
	}
	
	public void configuracao() {
		ImageIcon ref = new ImageIcon("Imagens-Jogo\\Records.jpg");
		fundo = ref.getImage();
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2d = g.create();
		g2d.fillOval(100, 100, 200, 200);
		g2d.drawImage(fundo, 0, 0, null);
		g2d.dispose();
	}
}
