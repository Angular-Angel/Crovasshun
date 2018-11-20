package processingdisplay;

import crovasshun.Game;
import geomerative.RShape;

public class RegularASCIITexture extends ASCIITexture {
	public final char[] chars;
    public final int[] colors;
    public int charIndex = 0;
    public int colorIndex = 0;

	public RegularASCIITexture(String name, Game game, char[] chars, int[] colors, int backgroundColor) {
		super(name, game, backgroundColor);
		this.chars = chars;
		this.colors = colors;
	}

	@Override
	public char getNextChar() {
		char character = chars[charIndex++];
		if (charIndex >= chars.length) charIndex = 0;
		return character;
	}

	@Override
	public int getNextColor() {
		int color = colors[colorIndex++];
		if (colorIndex >= colors.length) colorIndex = 0;
		return color;
	}

	@Override
	public void fill(RShape shape) {
		basicColor(shape);
		fillRect(shape, 5);
	}
}
