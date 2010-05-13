package control;

import gui.Graphic;
import gui.LeftPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: jivimberg
 * Date: May 11, 2010
 * Time: 12:46:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActionManager
{
    private AbstractAction showNumberOffsetAction;
    private AbstractAction restartAxisAction;
    private AbstractAction goToAction;


    public AbstractAction getShowNumberOffsetAction(final Graphic g)
    {
        if(showNumberOffsetAction == null)
        {
            showNumberOffsetAction = new AbstractAction("Mostrar posici�n") {
                public void actionPerformed(ActionEvent e) {
        	        g.showNumberOffset(!g.getShowNumberOffset());
                }
            };
        }
        return showNumberOffsetAction;
    }

    public AbstractAction getRestartAxisAction(final Graphic g)
    {
        if(restartAxisAction == null)
        {
            restartAxisAction = new AbstractAction("Centrar ejes") {
                 public void actionPerformed(ActionEvent e) {
        	         g.resetPosition();
                 }
            };
        }
        return restartAxisAction;
    }

    public AbstractAction getGoToAction(final Graphic g, final LeftPanel lPanel)
    {
        if(goToAction == null)
        {
             goToAction = new AbstractAction("Ir") {
    	        public void actionPerformed(ActionEvent e) {
    		        if(lPanel.getGoToX() != null && lPanel.getGoToY() != null)
                    {
    			         g.goTo(lPanel.getGoToX(), lPanel.getGoToY());
    		        }
    	        }
             };
        }
        return goToAction;
    }

}
