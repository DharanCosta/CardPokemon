# Desafio SomoS - Jogo de Cards Pokémon

Linguagem: `Java 17`

Framework: `Spring Boot`

## Descrição

O projeto consiste em um jogo de cartas Pokémon que compara os status de duas cartas e retorna o resultado de cada rodada. 

-🔨 `Funcionalidade 1`: Comparar cartas de Pokémon e retornar os resultados de cada rodada;</br>
-🔨 `Funcionalidade 2`: Fazer a gestão completa do  banco de dados de cartas de Pokémon que serão utilizadas no jogo (CRUD);</br>
-🔨 `Funcionalidade 3`: Apresentar uma página HTML dinâmica para consumir o banco de dados e realizar novas rodadas;</br>

## Pré-requisitos

Antes de iniciar, se assegure que possui os requisitos abaixo:

- JDK 17;
- Gerenciador de dependências Maven;
- IDE (Recomendado IntelliJ ou Eclipse)
- MySQL Driver;
- Postman (Recomendado para realizar os testes de endpoints)
- Navegador Web (Da sua preferência)

## Compilação/Configuração

Para compilar/configurar este projeto, siga as seguintes etapas:

- Preparando o ambiente Java <a href="https://www.devmedia.com.br/preparacao-do-ambiente-para-desenvolvimento-em-java/25188">Link de apoio</a>

- Instalando a Maven <a href="https://www.devmedia.com.br/introducao-ao-maven/25128#2">Link de apoio</a>

- Instalando e configurando MySQL  <a href="https://www.devmedia.com.br/instalando-e-configurando-a-nova-versao-do-mysql/25813">Link de apoio</a>

- Download Postman <a href="https://www.postman.com/downloads/">Link para Download</a>

- Download a IDE InteliJ <a href="https://www.jetbrains.com/idea/download/#section=windows">Link para Download</a>

- Abrir o projeto na IDE;

![OpenProject](https://user-images.githubusercontent.com/41304141/184695951-39687b32-858b-4ee2-9bce-8401df756557.jpg)


## Instalação/Execução

Para instalar/executar este projeto, siga as seguintes etapas:

- Clonar o projeto <a href="https://docs.github.com/pt/repositories/creating-and-managing-repositories/cloning-a-repository">Link de apoio</a> ; 

![Cloning](https://user-images.githubusercontent.com/41304141/184696967-1305b084-a805-4849-aa1a-be25ba6a3575.jpg)

- Abrir o projeto na IDE que preferir;
- Carregar o projeto Maven (Automático ao carregar o projeto);

- Configure sua conexão com o banco de dados (MySQL) no application.properties;

![ApplicationProperties](https://user-images.githubusercontent.com/41304141/184696377-c13c9003-7f6e-4e2c-9e9a-bbb2eac2f36e.jpg)

![ApplicationPropertiesBD](https://user-images.githubusercontent.com/41304141/184689201-a62c8549-4812-453a-9173-35e5169c3a07.jpg)

- Importe o banco de dados com todos os Pokémon e seus status base;
- Na main classe `CardGamePokemonApplication.java` descomente da linha 27 à 41 e execute o projeto para subir os dados para o banco de dados;
- <i>OBS. Apenas faça esse procedimento uma vez, depois comente as linhas mencionadas para garantir que os dados não sejam duplicados toda vez que iniciar o projeto </i>

![GeneratingDB](https://user-images.githubusercontent.com/41304141/184691483-1c11c031-7d9e-4f98-a4ed-e4f1adc3a072.jpg)

- Execute a classe main `CardGamePokemonApplication.java`; 

![Run](https://user-images.githubusercontent.com/41304141/184703263-c738fe91-648a-4161-b0a1-05da94721bef.jpg)

- Importar coleção do projeto no Postman;

![ImportPostman](https://user-images.githubusercontent.com/41304141/184702066-7addfce5-1236-4bb9-8e7e-97634b3f801e.jpg)

- Realizar os testes de endpoint pelo Postman;

![TestarEndpoints](https://user-images.githubusercontent.com/41304141/184702127-f62930da-b28a-425e-8bb3-c0efe101ca51.jpg)

- Abrir e testar o projeto em um navegador <a href="http://localhost:8080/">Link localhost</a>;




<h1 align="center">🌎 Onde você me encontra? 🌍</h1>

                                    
<div align="center" > 
  <a href="https://discord.gg/4j9jynF3" target="_blank"><img src="https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white" target="_blank"></a> 
  <a href = "mailto:dharancosta@gmail.com"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
  <a href="https://www.linkedin.com/in/dharancosta/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> 
</div>
