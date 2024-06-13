# Prova-Backend-Studio-Sol

Em resumo, a prova se consiste em criar uma **API web** para calcular o **maior número de combinações** de pontuações possíveis em um jogo de futebol americano. As pontuações podem ser: **Touchdown** (6 pontos), **Extra touchdown** (0, 1 ou 2 pontos, só após um touchdown) e **Field goal** (3 pontos). Por exemplo, para um placar de 3 x 15, existem quatro combinações possíveis para o time com 15 pontos.

Para uma descrição mais detalhada sobre a prova em si, acesse o documento https://docs.google.com/document/d/1FgllH0esa6a__qM7LbwD-HD17wwOLr2Hm25JjkJ_U14/edit

# Result Combinator Service

Para a resolução do problema suposto, foi criado o **Result Combinator Service**.

## Tecnologias usadas

- **Java Spring**
- **Docker**

## Instruções de uso
### Docker
- Certifique-se de ter o **Docker** instalado em sua máquina, caso não tenha, aqui está o link para a instalação https://www.docker.com/products/docker-desktop/

### Clone o repositório:
- Abra um terminal e execute o seguinte comando para clonar o repositório:
	```bash
	git clone https://github.com/ThalesMattos/Prova-Backend-Studio-Sol.git
	```
- Alternativamente, você pode fazer download do projeto na página deste repositório no GitHub. Para isso, clique em `Code > Download ZIP`
- No terminal da sua IDE, execute o seguinte comando:
```bash
ls target/*.jar
```
- Caso o comando acima não encontre um arquivo **.jar**, execute o comando abaixo:
```bash
mvn package
```
- Se o `ls target/*.jar` encontrou um arquivo **.jar** ou o `mvn package` criou com sucesso um arquivo **.jar**, execute os comandos abaixo na ordem que se encontram:
```bash
docker build -t spring-boot-app .
```
- Em caso de erro no comando acima referente 
```bash
docker run -p 8080:8080 spring-boot-app
```

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

## Fragmentando o código

Nesta aplicação foram necessárias apenas 3 camadas:
- **Controller**
- **Dto (Data Transfer Object)**
- **Service**

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/9375573a-f9ba-4db1-af98-8397353d2134)

### Service
É aqui que a mágica de verdade acontece, no `CalcularCombinacoesService` é implementada toda a lógica direcionada a resolver o problema proposto.

Inicialmente, é necessário converter a String recebida por parâmetro (`3x15` por exemplo) para dois inteiros(`3` e `15`), a respectiva pontuação de cada time. E para isto, chamamos o método `conversaoPlacarParaInteiro`:

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/e791482c-d256-4e99-87b7-b79ca299d51b)

Existem 4 casos em que o numero de combinações é igual a 0, quando o placar de algum time é igual a 1, 2, 4 e 5. Pois não há maneira de marcar estas pontuações, afinal, só é possível fazer:
- Touchdown: 6 pontos
- Extra touchdown: 0, 1 ou 2 pontos (só pode ser marcado após um touchdown)
- Field goal: 3 pontos

Então é preciso chamar o método `combinacaoIgualAZero`, que justamente verifica se algum dos dois placares se encaixam nos 4 casos citados acima. Se algum dos casos acima ocorrerem, é retornado imediatamente uma **Resposta HTTP** contendo o número de combinações igual a 0.

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/054b689e-61f1-458b-a194-c54eb1e002db)

Se o método `combinacaoIgualAZero` retornar `false`, ou seja, o placar não se enquadrou em um dos 4 casos acima, significa que o placar pode gerar um número de combinações diferente de 0 e será aplicada a lógica para calcular o numero maximo de combunações.

Depois de horas e horas de pesquisas para descobrir qual a maneira mais eficiente de resolver o problema proposto, chaguei a conclusão de que utilizar os princípios da programação dinâmica seria o ideal para implementar minha solução.

Em resumo, minha solução usa programação dinâmica para calcular o número de combinações possíveis de pontuações que podem levar a uma determinada pontuação. Ela faz isso construindo progressivamente a solução, começando de 0 e indo até a pontuação desejada.

Suponha que estamos tentando calcular as combinações para uma `pontuacaoDoTime` de 10.

Inicialmente, o array combinacoes é inicializado com zeros, exceto `combinacoes[0]` que é definido como 1, pois há uma única maneira de marcar 0 pontos (não marcando nenhum ponto).

Agora, vamos considerar a primeira pontuacaoFixa em SCORES, que é 3. Para cada pontuação i de 3 a 10 (a `pontuacaoDoTime`), atualizamos `combinacoes[i]` adicionando `combinacoes[i - pontuacaoFixa]`. Isso significa que estamos adicionando o número de maneiras de marcar `i - 3` pontos ao número de maneiras de marcar `i` pontos.

Por exemplo, quando `i` é 5, `combinacoes[5]` é atualizado para `combinacoes[5] + combinacoes[5 - 3]` (ou seja, `combinacoes[5] + combinacoes[2]`). Isso significa que estamos adicionando o número de maneiras de marcar 2 pontos ao número de maneiras de marcar 5 pontos. Isso ocorre porque podemos marcar 5 pontos marcando 2 pontos e depois marcando 3 pontos (a `pontuacaoFixa`).

Esse processo é repetido para cada pontuacaoFixa em `SCORES`. No final, `combinacoes[pontuacaoDoTime]` dará o número total de maneiras de marcar `pontuacaoDoTime` pontos usando as pontuações em `SCORES`.

Após o calculo de combinações dos dois placares, é retornado o que tiver o maior número de combinações.

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/435e6a21-f8d6-4abf-bf04-1a78e766460b)

### Controller
O `CombinacoesController` fica responsavel por receber uma **Requisição HTTP** através do `ScoreRecordDto`, ou seja, ele recebe um placar e o manda por parâmetro para o `CalcularCombinacoesService` calcular o máximo de combinações e retorna para o `CombinacoesController` que logo em seguida retorna através uma **Response Entity** (uma resposta HTTP) o número de combinações.

![image](https://github.com/ThalesMattos/Prova-Backend-Studio-Sol/assets/103903195/fc43726e-32da-4f82-a4c1-386b08ba098a)

### Dto

Podemos enxergar o **Dto** como um mensageiro de dados. Nesta aplicação o `ScoreRecordDto` fica responsável por receber dados em uma **Requisição HTTP POST**.

## Links

Estes são alguns **links** de sites/documentações que me auxiliaram na criação do **Result Combinator Service**:

- https://gurselgazii.medium.com/dockerizing-your-maven-spring-boot-application-a-step-by-step-guide-e267c2d9e8e1
- https://www.geeksforgeeks.org/dynamic-programming/
