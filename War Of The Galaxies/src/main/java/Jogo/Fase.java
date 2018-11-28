package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	// Plano de fundo do jogo

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image fundo;
	// Imagem de fundo tela
	private Nave nave;
	// Imagem da nave
	private Timer timer;
	// Atualizar a tela a cada movimento que o usu�rio fazer
	private boolean jogo;
	// Para saber se estamos em jogo ou nao

	private List<Inimigo> inimigos;

	private Inimigo chefao;

	private int[][] coordenadas;

	private int qntInimigos = 10;

	private int qntInimigosAbatidos = 0;

	private Boolean exibeInimigos = true;

	private Sounds sound;

	public int score = 0;

	private JanelaCadastro cadastro;

	public Fase() {
		sound = new Sounds();

		setDoubleBuffered(true);
		// N�o deixar falhas na tela ap�s a movimenta��o
		setFocusable(true);
		// Colocar a nave em foco no plano de fundo do jogo
		addKeyListener(new Teclado());
		// Ler os movimentos do teclado
		ImageIcon ref = new ImageIcon("Imagens-Jogo\\fundo.jpg");
		// Abrir a imagem de fundo do jogo
		fundo = ref.getImage();
		// Ler a imagem
		nave = new Nave();
		// Criar a nave dentro da fase
		jogo = true;

		inicializarInimigos(this.qntInimigos);

		timer = new Timer(5, this);
		// Definir tempo de a��o, atualizando a tela a cada movimento
		timer.start(); // iniar o timer
		sound.setSound("background");
	}

	public int getQntInimigos() {
		return this.qntInimigos;
	}

	public void setQntInimigos() {
		this.qntInimigos = this.qntInimigos + 10;
	}

	public void inicializarInimigos(int qntInimigos) {
		// Iniciar e mostrar todos os inimigos
		// ao decorrer da fase, puchando da
		// matris criada(coordenadas)
		inimigos = new ArrayList<Inimigo>();
		int linhas = qntInimigos;
		int colunas = 2;

		coordenadas = new int[linhas][colunas];

		for (int i = 0; i < coordenadas.length; i++) {
			coordenadas[i][0] = aleatoriar(1200, 6200);
			coordenadas[i][1] = aleatoriar(15, 500);
		}

		for (int i = 0; i < coordenadas.length; i++) {
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1], false));
		}
	}

	// Gera valores aleat�rios dentro do intervalo passado

	public int aleatoriar(int minimo, int maximo) {
		Random random = new Random();
		return random.nextInt((maximo - minimo) + 1) + minimo;
	}

	public void paint(Graphics g) {
		// Metodo para pintar na tela do jogo
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		// Apresentar o fundo no jogo; o 00 para deixar a imagem
		// prenchida na tela toda e null parada nessa posi��o

		if (jogo) {
			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
			// Apresentar a nave e suas posi��es no fundo do jogo

			List<Missil> misseis = nave.getMisseis();
			// Localiza��o da imagem do missil no jogo, e
			// contagem do numero de misseis disparados na
			// partida

			for (int i = 0; i < misseis.size(); i++) {
				Missil m = (Missil) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			// Localiza��o da imagem do inimigo no jogo, e contagem do numero de
			// inimigos eliminados na partida
			for (int i = 0; i < inimigos.size(); i++) {
				Inimigo in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}

			if (!exibeInimigos) {
				graficos.drawImage(chefao.getImagem(), chefao.getX(), chefao.getY(), this);
				chefao.movimentoBoss();
			}

			graficos.setColor(Color.RED);
			graficos.drawString("Inimigos Restantes: " + inimigos.size(), 5, 15);
			graficos.drawString("Inimigos abatidos: " + qntInimigosAbatidos, 5, 30);
			graficos.drawString("Score: " + score, 5, 45);
			graficos.fillRect(10, 10, 10, 10);
			// graficos.drawRect(10, 10, 10, 10);

		} else {
			ImageIcon finaljogo = new ImageIcon("Imagens-Jogo\\final de jogo.jpg");
			// Acrescentar imagem de game over no jogo
			graficos.drawImage(finaljogo.getImage(), 0, 0, null);

		}
		g.dispose();
		// Limpar a imagem anterior para a pr�xima pintura

	}

	public void actionPerformed(ActionEvent arg0) {
		// Movimentar a nave, missil, inimigos no plano
		// de fundo do jogo

		if (inimigos.size() == 0 && exibeInimigos) {
			// Quando acabar os inimigos

			// Adiniona + 10 inimigos
			setQntInimigos();

			// inicializa os Inimigos
			inicializarInimigos(getQntInimigos());

			// Libera o jogo
			jogo = true;

		} else if (!exibeInimigos) {

		}

		List<Missil> misseis = nave.getMisseis();
		for (int i = 0; i < misseis.size(); i++) {

			Missil m = (Missil) misseis.get(i);
			if (m.isVisivel()) {
				// Se o missil for visivel realizar o movimento
				m.movimento();
			} else {
				misseis.remove(i);
				// Remover os misseis do jogo quando ele
				// atingir um inimigo ou correr toda a tela
				// do jogo
			}

		}

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo in = (Inimigo) inimigos.get(i);
			if (in.isVisivel()) {
				// Se o inimigo for visivel realizar o
				// movimento
				in.movimento();
			} else {
				inimigos.remove(i);
				// Remover os inimigos do jogo quando ele
				// for atingido por um missil ou bater na
				// nave
			}
		}

		nave.movimento();
		repaint(); // Repinta a tela a cada atuali��o, para movimentar as
					// imagens
		colisoes();

	}

	public void colisoes() { // Verificar se ouve algum tipo de coliss�o durante
								// o jogo(missil com inimigo ou inimigo com
								// nave)
		Rectangle formaNave = nave.getBounds(); //
		Rectangle formaInimigo;
		Rectangle formaMissil;

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo tempInimigo = inimigos.get(i);
			// Tempo de gera��o de cada

			// inimigo
			formaInimigo = tempInimigo.getBounds();

			if (formaNave.intersects(formaInimigo)) {
				// Para saber quando a sua
				// nave foi destruida e
				// o jogo teve um fim
				nave.setVisivel(false);
				tempInimigo.setVisivel(false);

				jogo = false;
			}

		}

		List<Missil> misseis = nave.getMisseis(); // Verificar se cada missel
													// vai colidir com todas as
													// coordenadas que ser�o
													// geradas inimigos
		for (int i = 0; i < misseis.size(); i++) {
			Missil tempMissil = misseis.get(i);
			formaMissil = tempMissil.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMissil.intersects(formaInimigo)) {
					tempInimigo.setVisivel(false);
					tempMissil.setVisivel(false);
					qntInimigosAbatidos++;
					score++;
					sound.setSound("explosao");
					if (qntInimigosAbatidos == 50) {
						inimigos.clear();
						exibeInimigos = false;
						chefao = new Inimigo(700, 250, true);
					}
				}
			}
		}
	}

	private class Teclado extends KeyAdapter { // Pegar os eventos da tela

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				jogo = true;
				nave = new Nave(); // Reinicia a nave a posi��o inicial ap�s
									// teclar enter
				inicializarInimigos(getQntInimigos()); // Reinicia os inimigos a
														// poisi��o
				// inicial ap�s teclar enter
			}
			nave.keyPressed(e); // Pegar o evento do teclado e chamando a classe
								// nave

		}

		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e); // Pegar o evento do teclado e chamando a
									// classe nave
		}

	}

}
