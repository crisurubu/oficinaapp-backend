# Usa a imagem base do JDK 21
FROM eclipse-temurin:21-jdk-jammy

# Define o diretório de trabalho no container
WORKDIR /app

# Copia os arquivos necessários para o Maven Wrapper
# Garante que eles estejam no mesmo nível que o pom.xml
COPY mvnw .mvn/ ./

# Adiciona permissão de execução ao mvnw
RUN chmod +x ./mvnw

# Copia o arquivo de build
COPY pom.xml ./

# Baixa as dependências do Maven para otimizar o build
RUN ./mvnw dependency:go-offline

# Copia o código-fonte da aplicação
COPY src ./src

# Compila e empacota a aplicação em um JAR
RUN ./mvnw clean package -DskipTests

# Define o comando para rodar a aplicação
CMD ["java", "-jar", "target/*.jar"]