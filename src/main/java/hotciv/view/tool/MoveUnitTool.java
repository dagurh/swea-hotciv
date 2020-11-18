package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.handlers.DragTracker;

import java.awt.event.MouseEvent;

/** Template for the EndOfTurn Tool exercise (FRS 36.42)...
 *
 * @author Henrik Bærbak Christensen, Aarhus University
 */
public class MoveUnitTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;
    private Position from, to;


    public MoveUnitTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        from = GfxConstants.getPositionFromXY(x,y);
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        super.mouseDrag(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseUp(e, x, y);
        to = GfxConstants.getPositionFromXY(x,y);
        game.moveUnit(from, to);
    }
}
