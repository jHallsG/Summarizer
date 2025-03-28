package com.springai.SpringAIDemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
	
	Logger logger = LoggerFactory.getLogger(DataLoader.class);
	
	private final VectorStore vectorStore;
	
	private final JdbcTemplate jdbcTemplate;
	
	@Value("classpath:/docs/PH_Handbook.pdf")
	private Resource pdfResource;

	public DataLoader(VectorStore vectorStore, JdbcTemplate jdbcTemplate) {
		this.vectorStore = vectorStore;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@PostConstruct
	public void init() {
		
		// check the PG Vector Store database for contents
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM public.vector_store", Integer.class);
		
		// Read PDF contents, extract text, convert to numerical embeddings and upload if vector_store db is empty
		if (count == 0) {
			logger.info("Uploading vector embeddings...");
			
			// initialize the document reader to read on the file
			TikaDocumentReader reader = new TikaDocumentReader(pdfResource);
			
			// initialize splitter. this will split the text to separate chunks
			TextSplitter splitter = new TokenTextSplitter();

			// split the texts to chunks
			var chunks = splitter.apply(reader.get());
			
			logger.info("Uploading chunks to Vector Database. Please wait...");
			
			// feed the chunks to the vector_database
			vectorStore.accept(chunks);

			logger.info("Vector embeddings successfully uploaded.");
		}
	}
	
	// this code instantiates the PDF Document reader
	// this is no longer in use. just keeping the code for any future use.
	/*
	public PagePdfDocumentReader docReaderUsingPDFReader(Resource resource) {
		// configure pdf reader to read each page
		PdfDocumentReaderConfig config = PdfDocumentReaderConfig.builder()
							.withPagesPerDocument(1)
							.build();
		
		return new PagePdfDocumentReader(resource);
	} */
}
