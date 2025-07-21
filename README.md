MS Error Tracker

Este microserviço Error Tracker captura eventos de erro enviados via Kafka, persiste-os em banco MySQL e expõe uma API REST para consulta.

📝 Descrição

Consome mensagens do tópico Kafka fhir-error-topic (eventos de erro).

Valida e desserializa o payload JSON para ErrorRecordDTO.

Registra no banco de dados (MySQL) informações como: registro original, mensagem de erro, data, origem.

Exponha um endpoint REST para consultar todos os eventos salvos.

🚧 Pré-requisitos

Docker & Docker Compose

Acesso ao Docker Hub para baixar a imagem luskafer/ms-error-tracker

Tópicos Kafka criados: fhir-success-topic, fhir-error-topic

Banco MySQL disponível e configurado para o schema ms_error_tracker

⚙️ Configuração

As configurações ficam em application.properties. Principais parâmetros:

# Geral
spring.application.name=ms-error-tracker
server.port=8084

# Kafka
spring.kafka.bootstrap-servers=kafka:9092
spring.kafka.consumer.group-id=ms-error-tracker-group
spring.kafka.listener.missing-topics-fatal=false

# Banco de dados
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/ms_error_tracker
spring.datasource.username=${DB_USER:root}
spring.datasource.password=${DB_PASS:password}

# Flyway
spring.flyway.locations=classpath:db/migration
spring.flyway.enabled=true
spring.flyway.connect-retries=60
spring.flyway.connect-retries-interval=5s

# Tópicos de interesse
event.tracker.success-topic=fhir-success-topic
event.tracker.error-topic=fhir-error-topic

Ajuste as variáveis de ambiente (DB_HOST, DB_USER, etc.) conforme seu ambiente.

🐳 Executando com Docker Compose

Clone o repositório e navegue até a pasta que contém o docker-compose.yml:

git clone <repo-url>
cd ms-error-tracker

Inicie todos os serviços:

docker compose up -d zookeeper kafka mysql error-tracker

Verifique os logs do error-tracker:

docker logs -f error-tracker

🔄 Tópicos Kafka

Erro: fhir-error-topic — mensagens de erro que serão capturadas.

Sucesso: fhir-success-topic — para eventos de sucesso (não consumido por este serviço, mas configurado).

Para produzir uma mensagem de teste no tópico de erro:

# Exemplo de payload JSON válido
PAYLOAD='{"record":"RecordDTO(id_registro=XYZ,...)","error":"Descrição do erro"}'

# Via CLI do Kafka
echo $PAYLOAD \
  | docker exec -i kafka /opt/kafka/bin/kafka-console-producer.sh \
      --broker-list kafka:9092 --topic fhir-error-topic

🚀 Endpoints REST

GET /errors

Retorna todos os eventos de erro persistidos.

Response: 200 OK

[
  {
    "id": 1,
    "occurredAt": "2025-07-18T21:38:27Z",
    "message": "Descrição do erro",
    "details": "Conteúdo do registro",
    "origin": "FileProcessor",
    "processed": false
  },
  ...
]

🧪 Testes

A suíte de testes unitários usa JUnit 5 + Mockito.

Para rodar localmente:

mvn clean test

📚 Referências

Spring Boot 3.5

Spring Kafka

Spring Data JPA

Flyway

