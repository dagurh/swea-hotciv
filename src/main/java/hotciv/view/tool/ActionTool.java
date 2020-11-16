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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.security.Key;

public class ActionTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;


    public ActionTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void keyDown(KeyEvent evt, int key) {
        super.keyDown(evt, key);
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        if(e.isShiftDown()) {
            game.performUnitActionAt(GfxConstants.getPositionFromXY(x, y));
        }
    }
}
