# Usa a imagem base do JDK 21
FROM eclipse-temurin:21-jdk-jammy

# Define o diretório de trabalho no container
WORKDIR /app

# Copia os arquivos necessários para o Maven Wrapper
COPY mvnw ./
COPY .mvn/ ./
COPY pom.xml ./

# Adiciona permissão de execução ao mvnw
RUN chmod +x ./mvnw

# Baixa as dependências do Maven para otimizar o build
RUN ./mvnw dependency:go-offline

# Copia o código-fonte da aplicação
COPY src ./src

# Compila e empacota a aplicação em um JAR
RUN ./mvnw clean package -DskipTests

# Define o comando para rodar a aplicação
CMD ["java", "-jar", "target/*.jar"]