package Jogo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.CadastroDAO;

@SuppressWarnings("serial")
public class Cadastro extends JPanel implements MouseListener {
	
	public int score;
	public String nome;
	
	private CadastroDAO cadastroDAO;
	private JButton btnNovoJogo;
	private JButton btnEntrar;
	private JTextField txtNome;

	private boolean cadastroFase;

	public Cadastro() {
		// Falta alinhar os botões e txt
		// verificar como pode aumentar o componente TXT , em vez de colocar espeço. 
		
		
		
		cadastroDAO = new CadastroDAO();

		btnNovoJogo = new JButton("Novo Jogo");
		btnNovoJogo.setBounds(10, 10, 100, 100);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(100, 100, 100, 100);
		btnEntrar.setVisible(false);

		String texto = "                                        ";
		txtNome = new JTextField(texto); 
		txtNome.setVisible(false);

		cadastroFase = false;

		btnNovoJogo.addMouseListener(this);
		btnEntrar.addMouseListener(this);

		GridBagConstraints regras = new GridBagConstraints();
		GridBagLayout grid = new GridBagLayout();
		setLayout(grid);

		// Teste
		regras.fill = GridBagConstraints.NONE;
		regras.anchor = GridBagConstraints.CENTER;
		regras.weightx = 10;
		regras.weighty = 10;
		regras.gridx = 1;
		regras.gridy = 1;
		regras.gridwidth = 10;
		regras.gridheight = 10;

		grid.setConstraints(btnNovoJogo, regras);

		GridBagConstraints regras2 = new GridBagConstraints();
		regras2.weightx = 2;
		regras2.weighty = 10;
		regras2.gridx = 8;
		regras2.gridy = 1;
		regras2.gridwidth = 1;
		regras2.gridheight = 1;

		grid.setConstraints(btnEntrar, regras2);
		grid.setConstraints(txtNome, regras);

		add(btnNovoJogo);
		add(btnEntrar);
		add(txtNome);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnNovoJogo) {
			btnNovoJogo.setVisible(false);
			btnEntrar.setVisible(true);
			txtNome.setVisible(true);
		}
		if (e.getSource() == btnEntrar) {
			cadastroFase = true;
			cadastroDAO.save(txtNome.getText());
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

}
