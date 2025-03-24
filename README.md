# 📓 Summarizer
A simple notes summarizer built with a combination of Spring AI, Ollama and OpenAI. We will use Spring AI and Ollama to create
a local vector database that will serve as OpenAI's knowledge pool. The AI's knowledge will be strictly limited to this knowledge pool.

#  📌 Pre-requisites
Have these two programs installed on your computer before using the program.
1. Ollama
2. Docker
3. Your very own OPENAI API key.

# 🦙 Using Ollama
Ollama is an open-sourced tool to simplify working with LLM's. We will use it to download our embedding model.
You can head on to [Ollama](https://ollama.com/search?c=embedding) to search for your desired embedding.
Instructions to download an embedding model is also provided in the site.

# 🗒️ application.properties file.
```properties
spring.ai.openai.api-key                            --> provide your own api key
spring.ai.ollama.embedding.enabled                  --> used to enable/disable ollama's embedding models. set it to true
spring.ai.ollama.embedding.model                    --> used to specify your desired embedding model
spring.ai.openai.embedding.enabled                  --> set this to false else you will be charged per token of OpenAI. Ollama will take care of our embeddings
spring.ai.ollama.chat.enabled                       --> set this to false. OpenAI will take care of our Chat Model. In case you want to use your own Chat Model, set this to true.
spring.ai.vectorstore.pgvector.initialize-schema    --> this will auto-create our PGVector Database.
spring.ai.vectorstore.pgvector.dimensions           --> please refer to your downloaded embedding model for their dimensions (768 for nomic, 1024 for mxbai...)
```
### 🔠 API Endpoints
You can use Postman to check the following REST API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/guidedResponse?query=<user query>` | Generates a response based on the supplied pdf file and the user's restrictions. This is a completely restricted response. |
| `GET` | `/askAI?query=<user query>` | Generates a response based on OpenAi's general knowledge. This is for an unrestricted query. |
| `GET`  | `/promptAI?query=<user query>` | Generates a response based on both OpenAi's general knowledge and user restrictions. You can play around the AI restrictions using getAIResponseWithSystemPrompts method. |

# 🔍 Sample Outputs
Completely restricted response. The AI generates answers only based on the provided file. ![Restricted](https://github.com/user-attachments/assets/ff35f296-7ce5-4455-8cde-5aae9e7356ab)

Unrestricted response. The AI answers based on general knowledge. ![Unrestricted](https://github.com/user-attachments/assets/a00923e3-e76e-4482-a4cc-46b65957149b)


This is silly but this is also an unrestricted response, except there is a user restriction that tells the AI to behave like a cat. ![Neko](https://github.com/user-attachments/assets/7cdd715b-0279-406c-bcb9-ec5d03bf73af)


