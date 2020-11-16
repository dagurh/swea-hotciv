package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.CivDrawing;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import hotciv.view.figure.TextFigure;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;
import minidraw.framework.Tool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;


    public SetFocusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        game.setTileFocus(GfxConstants.getPositionFromXY(x,y));
        }
    }

