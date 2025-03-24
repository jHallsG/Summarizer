# Summarizer
A simple notes summarizer built with a combination of Spring AI, Ollama and OpenAI. We will use Spring AI and Ollama to create
a local vector database that will serve as OpenAI's knowledge pool. The AI's knowledge will be strictly limited to this knowledge pool.

# Pre-requisites
Have these two programs installed on your computer before using the program.
1. Ollama
2. Docker
3. Your very own OPENAI API key.

#Using Ollama
Ollama is an open-sourced tool to simplify working with LLM's. We will use it to download our embedding model.
You can head on to [Ollama](https://ollama.com/search?c=embedding) to search for your desired embedding.
Instructions to download an embedding model is also provided in the site.

#application.properties file.
```properties
spring.ai.openai.api-key                            --> provide your own api key
spring.ai.ollama.embedding.enabled                  --> used to enable/disable ollama's embedding models. set it to true
spring.ai.ollama.embedding.model                    --> used to specify your desired embedding model
spring.ai.openai.embedding.enabled                  --> set this to false else you will be charged per token of OpenAI. Ollama will take care of our embeddings
spring.ai.ollama.chat.enabled                       --> set this to false. OpenAI will take care of our Chat Model. In case you want to use your own Chat Model, set this to true.
spring.ai.vectorstore.pgvector.initialize-schema    --> this will auto-create our PGVector Database.
spring.ai.vectorstore.pgvector.dimensions           --> please refer to your downloaded embedding model for their dimensions (768 for nomic, 1024 for mxbai...)
```
