package com.springai.SpringAIDemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.SpringAIDemo.service.DocumentGuidedChatService;

@RestController
public class EmbeddecController {
	
//	private final ChatModel chatModel;
//	private final VectorStore vectorStore;
//	
//	private String prompt = """
//			Your task is to act like a Philippine Handbook guide. All answers to the question from the QUESTION section 
//			must come from the DOCUMENTS section. 
//			Be confident in your answers, and if you are unsure, simply state you don't know the answer.
//			
//			QUESTION:
//			{input}
//			
//			DOCUMENTS:
//			{documents}
//			""";
//	
//	public EmbeddecController(ChatModel chatModel, VectorStore vectorStore) {
//		this.chatModel = chatModel;
//		this.vectorStore = vectorStore;
//	}
//	
//	@GetMapping("/getEmbeddedResponse")
//	public String simplify(@RequestParam("query") String userPrompt) {
//		
//		PromptTemplate template = new PromptTemplate(prompt);
//		
//		Map<String, Object> promptParameters = new HashMap<String, Object>();
//		promptParameters.put("input", userPrompt);
//		promptParameters.put("documents", findAnswers(userPrompt));
//		
//		return chatModel.call(template.create(promptParameters))
//				.getResult()
//				.getOutput()
//				.getText();
//		
//	}
//
//	private String findAnswers(String userPrompt) {
//		List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
//				.query(userPrompt)
//				.topK(5)
//				.build());
//		
//		return documents
//				.stream()
//				.map(document -> document.getFormattedContent())
//				.collect(Collectors.joining());
//	}
	
	@Autowired
	private DocumentGuidedChatService embedService;
	
	@GetMapping("/guidedResponse")
	public String getGuidedResponse(@RequestParam("query") String userPrompt) {
		return embedService.processUserQuery(userPrompt);
	}

}
