package com.svgui;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.apache.log4j.Logger;

/*Let us have an interface 
 * which produces its own content
 * and writes by itself.
 * We should not be concerned about how it produces its contents
 * or the way in which it writes to the outputstream */
public class SVGDocumentWriter implements DocumentWriter {
	
	private static final Logger LOG = Logger.getLogger(SVGDocumentWriter.class);
	
	private SVGGraphics2D g;
	private boolean useCSS ;
	
	public SVGDocumentWriter(SVGGraphics2D g, boolean useCSS){
		if(g == null){
			String errorMsg = "No instance of SVGGraphics2D passed" ;
			LOG.debug(errorMsg);
			throw new NullPointerException(errorMsg) ;
		}
		this.g = g;
		this.useCSS = useCSS ;
	}
	
	public void write(OutputStreamWriter writer){
		if(writer== null){
			LOG.debug("No instance of writer given");
		}
		
		if(g == null){
			LOG.debug("No instance of SVGGraphics2D given");
		}
		
		try {
			g.stream(writer, useCSS);
			LOG.debug("Streamed SVG to writer");
			writer.flush();
			writer.close();
		} catch (SVGGraphics2DIOException e) {
			LOG.debug("Error while streaming SVGGraphics2D to writer");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
