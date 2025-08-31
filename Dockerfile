# Estágio 1: Build da aplicação
FROM eclipse-temurin:21-jdk-jammy AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia TODOS os arquivos da raiz do repositório para o container
COPY . .

# Adiciona permissão de execução
RUN chmod +x ./mvnw

# Baixa as dependências e compila
RUN ./mvnw clean package -DskipTests

# Estágio 2: Criação da imagem final (para execução)
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar do estágio de build
COPY --from=build /app/target/*.jar ./app.jar

# Define o comando de inicialização
CMD ["java", "-jar", "app.jar"]