package com.svgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.log4j.Logger;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import com.appiancorp.suiteapi.expression.annotations.Function;

/*This class represents an SVG Drawing */
public class SVGDrawing {
	private static final Logger LOG = Logger.getLogger(SVGDrawing.class);
	/*w3c namespace for SVG documents */
	private static final String SVGNS = "http://www.w3.org/2000/svg";
	
	/*This makes some calls to SVG, draws some shapes 
	 * and returns a reference to SVGGraphics2D */
	@Function
	public SVGGraphics2D draw(){
		DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
		LOG.debug("Got DomImpl");
		// Create an instance of org.w3c.dom.Document.
		SVGDocument document = (SVGDocument) impl.createDocument(SVGNS, "svg",
				null);

		LOG.debug("Got instance of SVGDocument");
		// Create an instance of the SVG Generator.
		SVGGraphics2D g = new SVGGraphics2D(document);
		Shape circle = new Ellipse2D.Double(0, 0, 50, 50);
		g.setSVGCanvasSize(new Dimension(180, 50));

		// Populate the document root with the generated SVG content.
		Element root = document.getRootElement();
		g.getRoot(root); // the root is set

		g.setPaint(Color.red);
		g.fill(circle);
		g.draw(circle);

		g.translate(60, 0);
		g.setPaint(Color.blue);
		g.fill(circle);
		g.draw(circle);

		g.translate(60, 0);
		g.setPaint(Color.green);
		g.fill(circle);
		g.draw(circle);
		
		return g;
		
	}
	

}
