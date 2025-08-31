# Estágio 1: Build da aplicação
FROM eclipse-temurin:21-jdk-jammy AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos de build
COPY pom.xml ./
COPY mvnw ./
COPY .mvn/ ./

# Adiciona permissão de execução ao mvnw
RUN chmod +x ./mvnw

# Baixa as dependências do Maven
RUN ./mvnw dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Compila o projeto e cria o arquivo .jar
RUN ./mvnw clean package -DskipTests

# Estágio 2: Criação da imagem final (para execução)
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho para a aplicação final
WORKDIR /app

# Copia o arquivo .jar do estágio de build para o estágio final
COPY --from=build /app/target/*.jar ./app.jar

# Define o comando de inicialização
CMD ["java", "-jar", "app.jar"]