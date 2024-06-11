# Prova-Backend-Studio-Sol

Em resumo, a prova se consiste em criar uma API web para calcular o maior número de combinações de pontuações possíveis em um jogo de futebol americano. As pontuações podem ser: Touchdown (6 pontos), Extra touchdown (0, 1 ou 2 pontos, só após um touchdown) e Field goal (3 pontos). Por exemplo, para um placar de 3 x 15, existem quatro combinações possíveis para o time com 15 pontos.

Para uma descrição mais detalhada sobre a prova em si, acesse o documento https://docs.google.com/document/d/1FgllH0esa6a__qM7LbwD-HD17wwOLr2Hm25JjkJ_U14/edit

# Result Combinator Service

Para a resolução do problema suposto, foi criado o Result Combinator Service.

## Tecnologias usadas

- Java Spring
- Docker
- Scripts de automação

## Lógica aplicada

Depois de horas e mais horas de pesquisas para descobrir qual a maneira mais eficiente de resolver o problema proposto, chaguei a conglusão de que utilizar os princípios da programação dinâmica seria o ideal para implementar minha solução.

Resumidamente, a programação dinâmica se consiste em quebrar o problema em subproblemas mais simples e a utilização de memória para armazenar e reutilizar soluções já calculadas.

## Instruções de uso

### Clone o repositório:
- Abra um terminal e execute o seguinte comando para clonar o repositório:
	```bash
	git clone https://github.com/ThalesMattos/Email-service.git
	```
- Alternativamente, você pode fazer download do projeto na página deste repositório no GitHub. Para isso, clique em `Code > Download ZIP`
- Rode a aplicação

### Cliente HTTPS

- Para inserir um placar, devemos abrir um **cliente https** (como o **Insomnia**, utilizado no exemplo abaixo), criar uma HTTP Request e selecionar a opção **POST**:

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/3c8eb5ee-1b2a-4eba-82c0-baf4f20c3588)

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/bb99cb7d-72cc-4aa5-a018-42bc91f5157d)

- Insira a URL padrão do Spring + o "/verify" `localhost:8080/verify`:

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/d1c899c3-eefb-413c-8179-7080935e803a)

- Agora precisamos mandar um JSON para a aplicação:
```bash
{ 
	"score": "`pontuação que desejar`x`pontuação que desejar`"
}
```
![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/e47b587d-01a2-41d8-a2dd-220bcc8e1a0f)
- Pronto! Ao lado você receberá o número de combinações referente ao placar inserido.

## Links

Estes são alguns links de sites/documentações que me auxiliaram na criação do Result Combinator Service:

- https://gurselgazii.medium.com/dockerizing-your-maven-spring-boot-application-a-step-by-step-guide-e267c2d9e8e1
- https://www.geeksforgeeks.org/dynamic-programming/
