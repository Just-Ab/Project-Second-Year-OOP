package Game.UI;

import org.joml.Vector3f;

import Game.Core.Node2D;
import Game.Visuals.Nodes.Tilemap2D;
import Game.Visuals.Resources.TilesetResource;

public class Label2D extends Node2D {

    private Tilemap2D tilemap = null;
    private final TilesetResource tileset =
            new TilesetResource("Assets/Textures/ttf.png", 16, 16);

    private String text = "PlaceHolder";

    private static final int COLS = 16;
    private static final int ROWS = 16;

    private static final String TILESET =
        " ☺☻♥♦♣♠•◘○◙♂♀♪♫☼" +
        "►◄↕‼¶§▬↨↑↓→←∟↔▲▼" +
        " !\"#$%&'()*+,-./" +
        "0123456789:;<=>?" +
        "@ABCDEFGHIJKLMNO" +
        "PQRSTUVWXYZ[\\]^_" +
        "`abcdefghijklmno" +
        "pqrstuvwxyz{|}~⌂" +
        "ÇüéâäàåçêëèïîìÄÅ" +
        "ÉæÆôöòûùÿÖÜ¢£¥₧ƒ" +
        "áíóúñÑªº¿⌐¬½¼¡«»" +
        "░▒▓│┤╡╢╖╕╣║╗╝╜╛┐" +
        "└┴┬├─┼╞╟╚╔╩╦╠═╬╧" +
        "╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀" +
        "αßΓπΣσµτΦΘΩδ∞φε∩" +
        "≡±≥≤⌠⌡÷≈°∙·√ⁿ²■";

    public void setText(String _text) {
        text = _text;
        buildTilemap();
    }

    private void buildTilemap() {
        if (tilemap != null) {
            tilemap.queueFree();
        }

        tilemap = new Tilemap2D(text.length(), 1);
        tilemap.setTileset(tileset);

        int x = 0;
        for (char c : text.toCharArray()) {
            tilemap.setCell(x, 0, charToIndex(c));
            x++;
        }
        if(isInTree)
        addChild(tilemap);
    }

    private int charToIndex(char c) {
        int indexTopLeft = TILESET.indexOf(c);
        if (indexTopLeft == -1) {
            indexTopLeft = TILESET.indexOf(' ');
        }

        int col = indexTopLeft % COLS;
        int rowFromTop = indexTopLeft / COLS;
        int rowFromBottom = (ROWS - 1) - rowFromTop;

        return rowFromBottom * COLS + col;
    }

    // @Override
    // public void setLocalPosition(Vector3f position) {
    //     super.setLocalPosition(position);
    //     if (tilemap != null) {
    //         tilemap.setLocalPosition(new Vector3f(0, 0, 0));
    //     }
    // }

    @Override
    public void setLocalScale(Vector3f scale) {
        super.setLocalScale(scale);
        if (tilemap != null) {
            tilemap.setLocalScale(scale);
        }
    }



    @Override
    protected void _enterTree() {
        super._enterTree();
        buildTilemap();
    }
}
