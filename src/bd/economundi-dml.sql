\c economundi;

BEGIN;

/*Inserindo Dados na Tabela Usuario*/
insert into usuario (email, nome, sobrenome, senha, data_nasc, perfil_economico) values ('Alexandresilva58@gmail.com', 'Alexandre', 'Ribeiro', '123456789', '1991-07-01', 'Moderado-agressivo');
insert into usuario (email, nome, sobrenome, senha, data_nasc, perfil_economico) values ('joaovitorteixeira10.JVT@gmail.com', 'João Vitor', 'Teixeira', '123456789', '1999-03-09', 'Conservador');
insert into usuario (email, nome, sobrenome, senha, data_nasc, perfil_economico) values ('dev.mateusbarbosa@gmail.com', 'Mateus José', 'Barbosa', '123456789', '1999-03-10', 'Moderado');

/*Inserindo Dados na Tabela Noticia*/

insert into noticia (manchete, descricao, conteudo, fonte, link_imagem, link, localidade, engajamento) values( 
    'Beto Rourkes unnecessary Spanish monologue is offensive pandering at its worst',
    'Beto O’Rourke was asked a straightforward question by MSNBC.',
    'Beto O’Rourke was asked a straightforward question by MSNBC host Savannah Guthrie during the Democratic debate about what top marginal tax rate he would support. Naturally, O’Rourke ignored it entirely.
    In his usual pretentious and hand-wavy style, the former Texas congressman sidestepped the question, instead launching into a long monologue about wealth inequality and an "economy that is rigged for corporations and the very wealthy." Yet he knew he couldn’t get away with avoiding the question entirely and offering zero specifics so he … switched to Spanish?
    He completed his answer half in Spanish, offering similar sentiments',
    'Google News',
    'https://bit.ly/2X1Q2Ql',
    'https://washex.am/2Ngoo2t',
    'Mundo',
    50
);

insert into noticia (manchete, descricao, conteudo, fonte, link_imagem, link, localidade, engajamento) values(
    'Bitcoin’s Parabolic Move Shows Why It’s a Sucker’s Bet',
    'Bitcoin has been on a tear lately, exploding from $3,000 to over $13,850.',
    'Bitcoin has been on a tear lately, exploding from $3,000 to over $13,850.While pundits try and ascribe reasons for the move, bitcoin traders who have managed to ride the wave are cheering.
     For now.
     If anything, this wacky move only serves to support the long-term bearish argument on bitcoin.
     What Goes Up…
     One of my primary objections to holding, or even trading, bitcoin is its volatility.
     Most of the time, when a stock is riding a wave higher, there’s a reason for it.
     Not so with BTC. It moves for one reason: emotion.
     As an investment commodity with no hard asset behind it, its value in any given moment is dependent on what one trader thinks another trader thinks bitcoin is worth, who depends on what he thinks another trader thinks bitcoin is worth, and so on.
     Pure speculation.',
    'Crypto Coins News',
    'https://www.ccn.com/wp-content/uploads/2019/06/bitcoin-broken-ss.jpg',
    'https://www.ccn.com/op-ed/bitcoins-parabolic-move-shows-why-its-a-suckers-bet/2019/06/26/',
    'Mundo',
    85
);
insert into noticia (manchete, descricao, conteudo, fonte, link_imagem, link, localidade, engajamento) values(
    'Bolsa sobe; dólar cai após duas altas e fecha valendo R$ 3,847',
    'O Ibovespa, principal índice da Bolsa brasileira, fechou o dia em alta de 0,6%',
    'O Ibovespa, principal índice da Bolsa brasileira, fechou o dia em alta de 0,6%, a 100.688,63 pontos. 
     Na semana passada, o Ibovespa fechou acima dos 100 mil pontos pela primeira vez na história. Apesar do marco, o recorde é apenas nominal. 
     Considerando a inflação, a Bolsa ainda está longe do nível registrado em 2008. O dólar comercial fechou em leve queda de 0,16%, cotado a R$ 3,847 na venda, após duas altas seguidas. O valor do dólar divulgado diariamente pela imprensa, inclusive o UOL, refere-se ao dólar comercial. Para turistas, o valor sempre é maior.',
    'www.uol.com.br',
     null,
    'https://economia.uol.com.br/cotacoes/noticias/redacao/2019/06/26/bolsa-dolar.htm',
    'Brasil',
    77
);

/*Inserindo Dados na Tabela Investimento*/

insert into investimento (nome, descricao, grupo, periodo, rendimento) values(
    'LCI e LCA',
    'Considerados investimentos de baixo risco, LCI (Letra de Crédito Imobiliário) e LCA (Letra de Crédito do Agronegócio) são emitidas por instituições financeiras. Com o objetivo de captar recursos para os setores imobiliário e agronegócio, respectivamente.
     São dois tipos de investimentos em Renda Fixa de curto prazo e isentos de Imposto de Renda, que dão ao investidor duas opções: saber na hora da compra quanto seu dinheiro vai render ou optar por acompanhar as taxas de juros do mercado.',
    'LCI/LCA',
     1080,
     28.13
);

insert into investimento (nome, descricao, grupo, periodo, rendimento) values(
    'CDB e LC',
    'Considerados investimentos de Renda Fixa com baixo risco, o CDB (Certificado de Depósito Bancário) e o LC (Letras de Câmbio) são títulos emitidos para que bancos e instituições financeiras consigam financiar suas atividades de crédito. Ideal para qualquer perfil de investidor.
     Com o CDB e LC, quem empresta dinheiro para o banco em troca de juros é você! São investimentos de Renda Fixa mais rentáveis, onde você pode acompanhar as taxas de juros do mercado possibilitando a opção de saber exatamente quanto seu dinheiro irá render.',
    'CDB/LC',
     1080,
     44.18
);

insert into investimento (nome, descricao, grupo, periodo, rendimento) values(
    'Tesouro IPCA',
    'Considerado a porta de entrada para o mundo dos investimentos, no Tesouro Direto você investe com pouco dinheiro e com o menor risco entre as aplicações do mercado. Ideal para quem quer começar a investir.
     Investir no Tesouro Direto é uma maneira acessível para fazer seu dinheiro render mais. Nele você investe em títulos públicos com rentabilidade variável.',
    'CDB/LC',
     1080,
     38.87
);

/*Inserindo Dados na Tabela Simulação*/
/*insert into simulacao (valor_inicial, valor_final, data_hora, usuario_id, investimento_id) values(

);*/

/*Inserindo Dados na Tabela Palavra*/
insert into palavra (nome, descricao) values ('INVESTIMENTO', 'Emprego da poupança em atividade produtiva, com o objetivo de auferir ganhos a médio ou longo prazo. É utilizado, também, para designar a aplicação de recursos em algum tipo de ativo financeiro.');
insert into palavra (nome, descricao) values ('BOLSA DE VALORES', 'Lugar onde se negociam títulos e valores mobiliários (sobretudo ações, opções, direitos e debêntures). A BM&FBOVESPA é a principal instituição brasileira de intermediação para operações do mercado de capitais e a única bolsa de valores, mercadorias e futuros em operação no Brasil.');
insert into palavra (nome, descricao) values ('GARANTIA', 'O termo garantia, ou colateral, se refere aos itens usados por uma empresa ou indivíduo para sustentar o crédito quando levanta um financiamento. Assim, a garantia de um empréstimo pode ser qualquer ativo sobre o qual o credor (quem emprestou o dinheiro) tem um direito legal, que pode ser exercido caso o tomador do empréstimo não cumpra alguma das cláusulas do contrato.');
insert into palavra (nome, descricao) values ('VOLATILIDADE', 'Indica o grau médio de variação da cotação de um título ou determinado mercado de subir ou cair intensamente em um curto período de tempo. A relação da volatilidade de uma ação em relação à volatilidade do mercado acionário como um todo pode ser medida através do seu coeficiente beta. Quando se afirma que uma aplicação é extremamente volátil, entende-se que esta aplicação está sujeita a fortes oscilações, o que pode ser decorrência das perspectivas para a companhia, falta de liquidez (bastante comum entre algumas ações no Brasil), ou outras razões.');
insert into palavra (nome, descricao) values ('MARCA', 'Determina todo sinal visualmente perceptível, que identifica e distingue produtos e serviços de outros análogos, de procedência diversa, e certifica sua conformidade com as normas e especificações técnicas pertinentes. O registro de uma marca é obtido, no Brasil, por intermédio do INPI - Instituto Nacional da Propriedade Industrial, que pode ser acessado pelo endereço eletrônico (www.inpi.gov.br).');
insert into palavra (nome, descricao) values ('FORNECEDOR', 'Qualquer organização que forneça bens e serviços, sendo que o uso destes bens e serviços pode acontecer em qualquer estágio da produção. Podem ser incluídos como fornecedores os distribuidores, revendedores, bem como os indivíduos que suprem a empresa com materiais e componentes.');
insert into palavra (nome, descricao) values ('BNDES', 'Sigla que designa o Banco Nacional de Desenvolvimento Econômico Social, que é o órgão governamental responsável pela implementação de políticas de investimento empresarial de longo prazo. Dentre as principais responsabilidades do BNDES estão: impulsionar o crescimento da economia, atuando como instituição de fomento; fortalecer o empresariado nacional; promover a diversificação e o crescimento das exportações; criar pólos de produção para diminuir possíveis desequilíbrios regionais. As atividades de fomento da instituição são conduzidas através de programas especiais como, por exemplo, o Finame, Finem e Finac.');
insert into palavra (nome, descricao) values ('AÇÃO', 'Valor mobiliário emitido pelas sociedades anônimas, representando a menor fração do capital destas empresas. As empresas emitem ações para aumentar o capital social, e os recursos levantados podem ser utilizados para vários fins, sobretudo futuros investimentos');
insert into palavra (nome, descricao) values ('PIB', 'Sigla que significa Produto Interno Bruto. Ver definição em PRODUTO INTERNO BRUTO.');
insert into palavra (nome, descricao) values ('PREGÃO', 'A tristeza emana do povo!');

COMMIT;

