package Jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missil {

	private Image imagem; // Imagem do missil
	private int x, y; // Coordenadas do missil
	private int largura, altura;
	private boolean visivel; // Para sumir o missil ao tocar no inimigo

	private static final int LARGURA_TELA = 1200; // Até onde o missel pode
													// correr na tela
	private static final int VELOCIDADE = 3; // Velocidade de movimento do
												// missil

	public Missil(int x, int y) {
		this.x = x;
		this.y = y;

		ImageIcon ref = new ImageIcon("Imagens-Jogo\\missil.png"); // Adicionar
																	// a imagem
																	// do missil
																	// atirado
																	// pela nave
		imagem = ref.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		visivel = true;

	}

	public void movimento() {
		this.x += VELOCIDADE; // Definir a velocidade do missil
		if (this.x > LARGURA_TELA) { // Definir até onde o missil aparecera na
										// tela do jogo
			visivel = false;
		}
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() { // Metodo criado para quando o missil ou a
									// nave colidirem com o inimigo
		return new Rectangle(x, y, largura, altura);
	}

}
