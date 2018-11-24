package view;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

import controller.IController;

public class GraphListener implements ViewerListener, MouseInputListener {

	private ViewerPipe fromViewer;
	private IController controller;

	public GraphListener(Graph graph, Viewer viewer, IController controller) {
		fromViewer = viewer.newViewerPipe();
		fromViewer.addViewerListener(this);
		fromViewer.addSink(graph);
		this.controller = controller;
	}

	public void setListening(boolean listening) {
	}

	@Override
	public void viewClosed(String id) {
	}

	@Override
	public void buttonPushed(String id) {
		controller.nodePressed(id);
	}

	@Override
	public void buttonReleased(String id) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		fromViewer.pump();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}