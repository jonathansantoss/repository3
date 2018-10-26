package Jogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class Janelas extends JFrame {
	private static final long serialVersionUID = 1L;
	private Cadastro cadastro;

	public Janelas() {
		cadastro = new Cadastro();
		configuracaoMenu();
	}
	
	
	private void configuracaoMenu() {
		JMenuBar barraMenu = new JMenuBar(); // Criar os Menus do Jogo
		JMenu menu = new JMenu("Menu");
		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,
						"Projeto Integrado - Engenharia da Computa��o - Desenvolvido Pelos Alunos: Fabiano Albino, Helton Moura, Valdecir Munhan",
						"Informa��es", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JMenuItem sair = new JMenuItem("Sair"); // Cria��o do menu de saida do
												// jogo
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(sobre);
		menu.add(new JSeparator());
		menu.add(sair);

		barraMenu.add(menu); // Adicionando o menu na barra de menu
		setJMenuBar(barraMenu);
		
		add(cadastro);
		configuracao();
		
		cadastroFase();
	}
	
	// Quando coloca a tela do cadastro e logo depois vai para outra tela, os comandos do teclado para de 
	// responder, então tem  que está clicando fora da tela do jogo e clicando novamente na tela do jogo
	// para voltar a funcionar os comandos do teclado. 
	public void cadastroFase() {
		if (cadastro.isCadastroFase() == true) {
			remove(cadastro);
			add(new Fase());
			configuracao();
		}
		else 
			{
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				cadastroFase();
			}
		
	}

	private void configuracao() {
		setTitle("War Of The Galaxies"); // Apresentar o titulo do jogo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar as janelas do
														// jogo
		setSize(1200, 630);// Resolu��o do jogo. Tamanho da janela.
		setLocationRelativeTo(null); // Janela ficara no meio da tela por causa
										// do null
		setResizable(false); // Impossibilita o usu�rio de alterar a resolu��o
								// do jogo
		setVisible(true); // Visible for true, faz mostrar a janela
	}

	public void executa() { // metodo para
							// execultas(instanciar) as
							// janelas
		new Janelas(); // Criando uma nova janelas para execuls�o do jogo

	}

}
