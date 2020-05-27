Informações MariaDB:
	-Database: recomendacao_invest_collector
	-Usuário: lc
	-Senha: 123

Criar investidor:
	-POST
	-http://localhost:8080/investidores
	{
	"nome": "Daniel Torquato",
	"email": "daniel.torquato@hotmail",
	"senha": "aviao11",
	"perfilDeInvestidor": "MODERADO"
	
	}

Login investidor:
	-post
	-http://localhost:8080/login 
	{	
	"email": "daniel.torquato@hotmail",
	"senha": "aviao11"
	}

Cadastrar investimento:
	-post
	-http://localhost:8080/investimentos 
	{
	"nome": "FUNDO MULTI",
	"descricao": "FUNDO MULTIMERCADO",
	"riscoInvestimento": "ALTO",
	"tipoDeInvestimento": "FUNDO_MULTIMERCADO",
	"rentabilidade": 0.6,
	"vlr_min_aplicacao": 100.0
	}

Gerar Recomendação:
    -post
    -http://localhost:8080/recomendacoes 
    { "investidor": {
    "id": 2
    },
    "investimentos":[
    {
    }
    ]
    } 