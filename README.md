# Prova-Backend-Studio-Sol

Em resumo, a prova se consiste em criar uma **API web** para calcular o **maior número de combinações** de pontuações possíveis em um jogo de futebol americano. As pontuações podem ser: **Touchdown** (6 pontos), **Extra touchdown** (0, 1 ou 2 pontos, só após um touchdown) e **Field goal** (3 pontos). Por exemplo, para um placar de 3 x 15, existem quatro combinações possíveis para o time com 15 pontos.

Para uma descrição mais detalhada sobre a prova em si, acesse o documento https://docs.google.com/document/d/1FgllH0esa6a__qM7LbwD-HD17wwOLr2Hm25JjkJ_U14/edit

# Result Combinator Service

Para a resolução do problema suposto, foi criado o **Result Combinator Service**.

## Tecnologias usadas

- **Java Spring**
- **Docker**
- **Scripts de automação**

## Lógica aplicada no código

Nesta aplicação foram necessárias apenas 3 camadas:
- **Controller**
- **Dto (Data Transfer Object)**
- **Service**

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/9375573a-f9ba-4db1-af98-8397353d2134)

### Service
É aqui que a mágica de verdade acontece, no `CalcularCombinacoesService` é implementada toda a lógica direcionada a resolver o problema proposto.

Depois de horas e horas de pesquisas para descobrir qual a maneira mais eficiente de resolver o problema proposto, chaguei a conglusão de que utilizar os princípios da programação dinâmica seria o ideal para implementar minha solução.

Resumidamente, a programação dinâmica se consiste em quebrar o problema em subproblemas mais simples e a utilização de memória para armazenar e reutilizar soluções já calculadas.

### Controller
O `CombinacoesController` fica responsavel por receber uma **Requisição HTTP** através do `ScoreRecordDto`, ou seja, ele recebe um placar e o manda por parâmetro para o `CalcularCombinacoesService` calcular o máximo de combinações e retorna para o `CombinacoesController` que logo em seguida retorna através uma **Response Entity** (uma resposta HTTP) o número de combinações.

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/fc43726e-32da-4f82-a4c1-386b08ba098a)

### Dto

Podemos enxergar o **Dto** como um mensageiro de dados. Nesta aplicação o `ScoreRecordDto` fica responsável por receber dados em uma **Requisição HTTP POST**.

## Instruções de uso

### Clone o repositório:
- Abra um terminal e execute o seguinte comando para clonar o repositório:
	```bash
	git clone https://github.com/ThalesMattos/Prova-Backend-Studio-Sol.git
	```
- Alternativamente, você pode fazer download do projeto na página deste repositório no GitHub. Para isso, clique em `Code > Download ZIP`
- Rode a aplicação

### Cliente HTTPS

- Para inserir um placar, devemos abrir um **Cliente HTTPS** (como o **Insomnia**, utilizado no exemplo abaixo), criar uma **HTTP Request** e selecionar a opção **POST**:

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/3c8eb5ee-1b2a-4eba-82c0-baf4f20c3588)

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/bb99cb7d-72cc-4aa5-a018-42bc91f5157d)

- Insira a **URL** padrão do Spring + o "/verify" `localhost:8080/verify`:

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/d1c899c3-eefb-413c-8179-7080935e803a)

- Agora precisamos mandar um **JSON** para a aplicação:
```bash
{ 
	"score": "`pontuação que desejar`x`pontuação que desejar`"
}
```
![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/e47b587d-01a2-41d8-a2dd-220bcc8e1a0f)
- Pronto! Ao lado você receberá o **número de combinações** referente ao placar inserido.

## Links

Estes são alguns **links** de sites/documentações que me auxiliaram na criação do **Result Combinator Service**:

- https://gurselgazii.medium.com/dockerizing-your-maven-spring-boot-application-a-step-by-step-guide-e267c2d9e8e1
- https://www.geeksforgeeks.org/dynamic-programming/
