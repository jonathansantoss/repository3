package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Inimigo {

	private Image imagem; // Imagem do inimigo
	private int x, y; // Coordenadas do inimigo
	private int altura, largura;
	private boolean visivel; // Para sumir inimigo ao ser atingido por um missil
								// ou se chocar com a nave

	private static final int LARGURA_TELA = 1200; // Até onde o inimigo pode
	@SuppressWarnings("unused")
	private static final int ALTURA_TELA = 500;												// correr na tela
	private static final int VELOCIDADE = 2; // Velocidade de movimento do
												// inimigo

	private static int contador = 0;

	public Inimigo(int x, int y, Boolean isChefao) {
		this.x = x;
		this.y = y;

		ImageIcon ref;

		if (isChefao) {
			ref = new ImageIcon("Imagens-Jogo\\chefao.png"); 
		} else {

			if (contador++ % 3 == 0) {
				ref = new ImageIcon("Imagens-Jogo\\inimigo1.gif"); // Adicionar a
																	// imagem do
																	// inimigo 1
			} else {
				ref = new ImageIcon("Imagens-Jogo\\inimigo3.gif"); // Adicionar a
																	// imagem do
																	// inimigo 2
			}
		}

		imagem = ref.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		visivel = true;

	}

	public void movimento() {
		if (this.x < 0) {
			this.x = LARGURA_TELA; // Quando o inimigo chegar ao final da tela,
									// voltar ao começo
		} else {
			this.x -= VELOCIDADE; // Para Que o inimigo venha de frente com a
									// nave
		}
	}
	
	public void movimentoBoss() {
		if (this.y < 0) {
			Random random = new Random();
			this.y = random.nextInt(450);
					
			 // Quando o inimigo chegar ao final da tela,
									// voltar ao começo
		} else {
			//this.y -= VELOCIDADE; // Para Que o inimigo venha de frente com a
			Random random = new Random();
			this.y = random.nextInt(450);						// nave
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
