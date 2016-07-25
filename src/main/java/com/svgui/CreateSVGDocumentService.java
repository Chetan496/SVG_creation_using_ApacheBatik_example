package com.svgui;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.content.ContentService;

public class CreateSVGDocumentService {
	
	private static final Logger LOG = Logger.getLogger(CreateSVGDocumentService.class);
	private SVGGraphics2D g ;
	private ContentService cs;
	
	public  CreateSVGDocumentService(ContentService cs, SVGGraphics2D g){
		if(cs == null){
			String errorMsg = "No instance of ContentService passed" ;
			LOG.debug(errorMsg);
			throw new NullPointerException(errorMsg) ;
		}
		
		if(g == null){
			String errorMsg = "No instance of SVGGraphics2D passed" ;
			LOG.debug(errorMsg);
			throw new NullPointerException(errorMsg) ;
		}
		this.g = g;
		this.cs = cs;
	}
	
	public Long createSVGDocument(boolean useCSS, String documentName, Long parentFolderId){
		Long docId;
		if(documentName == null || documentName.trim() == ""){
			String errorMsg = "No documentName passed" ;
			throw new NullPointerException(errorMsg) ;
		}
		
		if(parentFolderId == null){
			String errorMsg = "No parentFolderId passed" ;
			throw new NullPointerException(errorMsg) ;
		}
		
		
		SVGDocumentWriter svgWriter = new SVGDocumentWriter(g, useCSS) ;
		CreateDocumentService docServiceCreator = new CreateDocumentService() ;
		
		docId = docServiceCreator.createDoc(documentName, parentFolderId, "svg", svgWriter, cs) ;
		LOG.debug("DocId of the new document is "+docId);
		return docId;
	}
}
