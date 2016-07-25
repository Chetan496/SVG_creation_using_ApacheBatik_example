package com.svgui;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.common.exceptions.PrivilegeException;
import com.appiancorp.suiteapi.common.exceptions.StorageLimitException;
import com.appiancorp.suiteapi.content.ContentConstants;
import com.appiancorp.suiteapi.content.ContentOutputStream;
import com.appiancorp.suiteapi.content.ContentService;
import com.appiancorp.suiteapi.content.exceptions.DuplicateUuidException;
import com.appiancorp.suiteapi.content.exceptions.InsufficientNameUniquenessException;
import com.appiancorp.suiteapi.content.exceptions.InvalidContentException;
import com.appiancorp.suiteapi.knowledge.Document;

public class CreateDocumentService {
	
	private static final Logger LOG = Logger.getLogger(CreateDocumentService.class);
	/* 
	 * This method creates a new document and 
	 * returns the documentId
	 */
	public Long createDoc(String documentName, Long parentFolderId, String ext, DocumentWriter dw,
			ContentService cs){
		
		if(documentName == null || documentName.trim() == ""){
			LOG.debug("No documentName given");
			return null;
		}
		
		if(parentFolderId == null){
			LOG.debug("No parentFolder Id given");
			return null;
		}
		
		if(ext == null){
			LOG.debug("No document extension specified");
			return null;
		}
		
		if(cs == null) {
			LOG.debug("No instance of ContentService given");
			return null;
		}
		
		if(dw == null){
			LOG.debug("No instance of DocumentWriter given");
			return null;
		}
		
		
		/*Initialize the Document*/
		Document doc = new Document();
		doc.setParent(parentFolderId);
		doc.setExtension(ext);
		doc.setName(documentName);
		
		ContentOutputStream cos=null;
		Long newDocID = null;
		
		LOG.debug("Initialized the Document object");
		
		try {
			/*Create a document and provides an output stream 
			 * to which the document's content should be written */
			cos = cs.upload(doc,
					ContentConstants.UNIQUE_FOR_TYPE); /*Name of document should be unique
					among the content type of document */
			
			LOG.debug("Got instance of ContentOutputStream");
			/*Let us write to the Output Stream */
			OutputStreamWriter writer = new OutputStreamWriter(cos, "UTF8");
			dw.write(writer);
			
			cos.close();
			
			LOG.debug("Wrote data to contentOutput Stream") ;	
			/*Get the docId of the content just created */
			newDocID = cos.getContentId();
			LOG.debug("Got docId: "+newDocID);
			doc.setId(newDocID);
		} catch (InvalidContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientNameUniquenessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateUuidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDocID;
	}
	
	
}
