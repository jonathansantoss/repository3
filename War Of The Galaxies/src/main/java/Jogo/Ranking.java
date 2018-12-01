package Jogo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.CadastroDAO;

@SuppressWarnings("serial")
public class Ranking extends JPanel{
	private Image fundo;
	private CadastroDAO cadastroDAO;
	private JTable tabela;
	
	public Ranking() {
		configuracao();
		//tabela();
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
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableModel modelo = new DefaultTableModel(null,colunas);
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    	JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		    	
		        if (row == 0) {
		        	label.setBackground(Color.LIGHT_GRAY);
		        	label.setFont(new Font("arial", Font.BOLD, 12));
		        	}
		        else if (row >= 1)
		        	label.setBackground(Color.WHITE);
		        
		        return label;
		    }
		};
		
		modelo.addRow(new String[] {"Nome", "Score"});
		
		for (Cadastro cadastro : cadastros)
			modelo.addRow(new String[] { cadastro.nome, String.valueOf(cadastro.score) });

		GridBagConstraints regrasTabela = new GridBagConstraints();
		regrasTabela.anchor = GridBagConstraints.NORTHEAST;
		regrasTabela.gridx = 1;
		regrasTabela.gridy = 1;
		regrasTabela.insets = new Insets(70, 70, 0, 480);

		tabela.setModel(modelo);
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(0).setCellRenderer(renderer);;
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setCellRenderer(renderer);;
		
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
