package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave {

	private int x, y;
	private int dx, dy; // Coordenadas da nave
	private int altura, largura;
	private Image imagem; // Imagem da nave
	private boolean visivel; // Se a nave estara visivel ou nao(apos colidir ou
								// nao)
	private List<Missil> misseis;
	private Sounds sound;
	public Nave() { // Construir a nave
		sound = new Sounds();
		ImageIcon ref = new ImageIcon("Imagens-Jogo\\nave.gif"); // adicionar a
																	// imagem da
																	// nave
		imagem = ref.getImage(); // passando a imagem para o atributo criado
									// para ela
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);

		misseis = new ArrayList<Missil>(); // Usado para fazer a maquina atirar
											// mais de uma vez

		this.x = 100; // Posição da nave na tela
		this.y = 100; // Posição da nave na tela

	}

	public void movimento() { // Movimentação da nave
		x += dx; // 0 e 1060 - Definir os limites de movimentação na tela do
					// jogo no sentido horizontal
		y += dy; // 5 e 541 - Definir os limites de movimentação na tela do jogo
					// no sentido vertical
		if (this.x < 0) {
			x = 0;
		}
		if (this.x > 1060) {
			x = 1060;
		}
		if (this.y < 5) {
			y = 5;
		}
		if (this.y > 541) {
			y = 541;
		}

	}

	public List<Missil> getMisseis() {
		return misseis;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public void atirar() { // Adicionar um novo missil após o missil atirado
		this.misseis.add(new Missil(x + largura, y + altura / 2)); // Localização
																	// de onde
																	// vai sair
																	// o tiro da
																	// nave

	}

	public Rectangle getBounds() { // Metodo criado para quando o missil ou a
									// nave colidirem com o inimigo
		return new Rectangle(x, y, largura, altura);
	}

	public void keyPressed(KeyEvent tecla) { // Ler as teclas digitadas pelo
												// usuário
		int codigo = tecla.getKeyCode(); // Retorna a tecla digitada pelo
											// usuário

		if (codigo == KeyEvent.VK_SPACE) { // Definir tecla que o usuário vai
											// usar para atirar(tecla espaço)
			atirar();
			sound.setSound("tiro");

		}

		if (codigo == KeyEvent.VK_UP) { // Movimentar a nave para cima
			dy = -5; // Decide a velocidade de movimento da nave para cima
		}

		if (codigo == KeyEvent.VK_DOWN) { // Movimentar a nave para baixo
			dy = 5; // Decide a velocidade de movimento da nave para baixo
		}
		if (codigo == KeyEvent.VK_LEFT) { // Movimentar a nave para esquerda
			dx = -5; // Decide a velocidade de movimento da nave para esquerda
		}
		if (codigo == KeyEvent.VK_RIGHT) { // Movimentar a nave para direita
			dx = 5; // Decide a velocidade de movimento da nave para direita
		}

	}

	public void keyReleased(KeyEvent tecla) { // Metodo criado para a nave não
												// se mover, se o usuario clicar
												// em uma tecla errada ou nao
												// clicar em nada
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

	}

}
