package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Template for the CompositionTool exercise (FRS 36.44).
 * Composition tool is basically a State Pattern, similar
 * to MiniDraw's SelectionTool. That is, upon mouse-down
 * it must determine what the user wants (from analyzing
 * what graphical elements have been clicked - unit?
 * city? tile? turn-shield? etc.) and then set its
 * internal tool state to the appropriate tool - and
 * then delegate the mouse down request to that tool.
 *
 * @author Henrik Bærbak Christensen, Aarhus University
 */
public class CompositionTool extends NullTool {
  private final DrawingEditor editor;
  private final Game game;
  private HotCivFigure figureBelowClickPoint;

  private Tool state;

  public CompositionTool(DrawingEditor editor, Game game) {
    state = new NullTool();
    this.editor = editor;
    this.game = game;
    state = null;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    // Find the figure (if any) just below the mouse click position
    figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);
    // Next determine the state of tool to use

      state = new SetFocusTool(editor, game);
      state.mouseDown(e, x, y);

    if (figureBelowClickPoint.getTypeString().equals(GfxConstants.TURN_SHIELD_TYPE_STRING)) {
      state = new EndOfTurnTool(editor, game);
    }
    if (figureBelowClickPoint.getTypeString().equals(GfxConstants.UNIT_TYPE_STRING)) {
      state = new MoveUnitTool(editor, game);

      if (e.isShiftDown()) {
        state = new ActionTool(editor, game);
      }
    }
    state.mouseDown(e,x,y);
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {
    state.mouseDrag(e, x, y);
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    state.mouseUp(e, x, y);
  }
}
