package com.svgui;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.content.ContentService;
import com.appiancorp.suiteapi.expression.annotations.Function;

@com.appiancorp.suiteapi.expression.annotations.AppianScriptingFunctionsCategory
public class SVGFunctions  {
	private static final Logger LOG = Logger.getLogger(SVGFunctions.class);
	/* Currently we will hardcode the parentFolderId */
	private static final Long parentFolderId = 6031L;
	
	@Function
	public Long getSVGDoc(ContentService cs)  {
		
		SVGDrawing drawing = new SVGDrawing();
		SVGGraphics2D g = drawing.draw() ;
		// Finally, stream out SVG to a new Appian document
		boolean useCSS = true; // we want to use CSS style attributes

		CreateSVGDocumentService svgCreatorService = new CreateSVGDocumentService(
				cs, g);
		Long docId = svgCreatorService.createSVGDocument(useCSS, "TestSVG", parentFolderId);
		
		LOG.debug("Got docId "+docId);
        return docId ;
	}

}
