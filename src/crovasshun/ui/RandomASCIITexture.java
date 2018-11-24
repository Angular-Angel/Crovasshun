package crovasshun.ui;

import java.util.Random;

import crovasshun.Game;
import geomerative.RShape;

public class RandomASCIITexture extends ASCIITexture {
	
	public final char[] chars;
    public final int[] colors;
    public Random random = new Random();

	public RandomASCIITexture(String name, Game game, char[] chars, int[] colors, int backgroundColor) {
		super(name, game, backgroundColor);
		this.chars = chars;
		this.colors = colors;
	}

	@Override
	public char getNextChar() {
		return chars[random.nextInt(chars.length)];
	}

	@Override
	public int getNextColor() {
		return colors[random.nextInt(colors.length)];
	}

	@Override
	public void fill(RShape shape) {
		basicColor(shape);
		fillRect(shape, 5);
	}

}
