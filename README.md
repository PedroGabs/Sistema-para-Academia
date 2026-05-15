# 🏋️ Sistema de Gerenciamento de Academia

Sistema de gerenciamento de academia desenvolvido em Java com interface de linha de comando (CLI), persistência em banco de dados PostgreSQL (Supabase) via JDBC, e arquitetura orientada a objetos.

---

## 👥 Integrantes do Grupo

| Nome | RM |
|---|---|
| Alexsandro Mauricio Silva Vasconcelos | 42037514 |
| Matheus Henrique Teixeira Santos | 43608124 |
| Pedro Gabriel Castro da Silva | 428321522 |
| Tharik Lima da Silva | 43676642 |
| Vinícius Firmino Souto | 42732972 |
| Wendel Rodrigues Macedo | 42900905 |

---

## 📋 Descrição

O sistema permite gerenciar as operações básicas de uma academia: cadastro e controle de alunos, instrutores, planos de assinatura e aulas, com inscrição de alunos em turmas. Todos os dados são persistidos em um banco de dados PostgreSQL hospedado no Supabase.

---

## 🚀 Funcionalidades

### 👤 Alunos
- Cadastrar novo aluno (com validação de CPF)
- Listar todos os alunos com plano vinculado
- Atualizar dados de um aluno
- Remover aluno

### 🎓 Instrutores
- Cadastrar novo instrutor
- Listar instrutores e quantidade de alunos

### 💳 Planos
- Cadastrar plano com nome e valor
- Listar planos disponíveis

### 📅 Aulas
- Criar aula com data, horário, capacidade e instrutor responsável
- Inscrever aluno em aula (com validação de vaga e conflito de horário)

---

## 🧠 Conceitos de POO Aplicados

### Herança
```
Pessoa (classe abstrata)
├── Aluno
├── Instrutor
└── Usuario
```

### Polimorfismo e Sobrescrita
- `Aluno` e `Instrutor` sobrescrevem `exibirInfo()` e `exibirPermissoes()` de `Pessoa`
- Uso de `@Override` e `super.exibirInfo()` nas subclasses

### Interfaces
- `Auditavel` — implementada por `Aluno` e `Instrutor`: `registrarLog()` e `obterHistorico()`
- `Relatorio` — implementada por `Academia` e `Plano`: `gerarResumo()` e `imprimirDetalhes()`

### Sobrecarga
- `Aluno.pagarMensalidade()` e `Aluno.pagarMensalidade(double valor)`

### Encapsulamento
- Todos os atributos são privados com getters/setters públicos em todas as entidades

---

## 🗂️ Estrutura do Projeto

```
Sistema-para-Academia-cp4/
├── lib/
│   └── postgresql-42.7.11.jar       # Driver JDBC do PostgreSQL
└── src/
    ├── Main.java                    # Ponto de entrada e menu interativo
    ├── db.properties                # Configuração de conexão com o banco
    ├── dao/
    │   ├── AlunoDao.java            # CRUD de alunos
    │   ├── InstrutorDao.java        # CRUD de instrutores
    │   ├── PlanoDAO.java            # CRUD de planos
    │   └── ConexaoBD.java           # (stub)
    ├── database/
    │   └── Conexao.java             # Fábrica de conexão JDBC via db.properties
    ├── model/
    │   ├── Pessoa.java              # Superclasse abstrata
    │   ├── Aluno.java               # Subclasse: aluno
    │   ├── Instrutor.java           # Subclasse: instrutor
    │   ├── Usuario.java             # Subclasse: usuário do sistema
    │   ├── Plano.java               # Plano de assinatura
    │   ├── Aula.java                # Aula com vagas e inscritos
    │   ├── Academia.java            # Agregador da academia
    │   ├── Presenca.java            # Registro de presença
    │   ├── Auditavel.java           # Interface de log
    │   ├── Relatorio.java           # Interface de relatório
    │   └── TipoPlanos.java          # Enum: BASICO, COMPLETO, ZUMBA
    ├── service/
    │   └── InscricaoService.java    # Regras de negócio para inscrição em aulas
    └── util/
        ├── ValidadorCPF.java        # Validação completa de CPF (dígitos verificadores)
        └── Formatador.java          # Formatação de datas e valores monetários
```

---

## 🗄️ Banco de Dados

O projeto utiliza **PostgreSQL** hospedado no **Supabase**, conectado via JDBC.

### Tabelas esperadas

**`alunos`**
| Coluna | Tipo |
|---|---|
| id | SERIAL PK |
| nome | VARCHAR |
| cpf | VARCHAR |
| endereco | VARCHAR |
| data_nascimento | DATE |
| telefone | VARCHAR |
| email | VARCHAR |
| id_plano | INTEGER (FK → planos) |

**`instrutores`**
| Coluna | Tipo |
|---|---|
| id | SERIAL PK |
| nome | VARCHAR |
| cpf | VARCHAR |
| endereco | VARCHAR |
| data_nascimento | DATE |
| telefone | VARCHAR |
| email | VARCHAR |

**`planos`**
| Coluna | Tipo |
|---|---|
| id | SERIAL PK |
| nome | VARCHAR |
| preco | NUMERIC |
| tipo_plano | VARCHAR |

---

## ⚙️ Configuração e Execução

### Pré-requisitos

- Java 11 ou superior
- IDE com suporte a projetos Java (IntelliJ IDEA recomendado)
- Acesso à internet (conexão com Supabase)

### Configuração do banco

Edite o arquivo `src/db.properties` com as credenciais do seu banco:

```properties
db.url=jdbc:postgresql://<host>:<porta>/<banco>?sslmode=require
user=<usuario>
password=<senha>
```

> ⚠️ **Nunca versione credenciais reais.** Adicione `db.properties` ao `.gitignore`.

### Adicionando o driver JDBC

O driver já está em `lib/postgresql-42.7.11.jar`. Na sua IDE, adicione-o como dependência de biblioteca do projeto.

**IntelliJ IDEA:**
1. `File` → `Project Structure` → `Libraries`
2. Clique em `+` → `Java` → selecione `lib/postgresql-42.7.11.jar`
3. Confirme e aplique

### Executando

Execute a classe `Main.java`. O sistema verificará a conexão com o banco ao iniciar e exibirá o menu interativo no terminal.

---

## 🔒 Validações implementadas

- **CPF:** validação completa com cálculo dos dígitos verificadores (`ValidadorCPF`)
- **Data:** parse com tratamento de exceção para o formato `AAAA-MM-DD`
- **Inscrição em aula:** verificação de plano ativo, vagas disponíveis e conflito de horário
- **Log de ações:** todas as operações relevantes são registradas no histórico do objeto (`Auditavel`)

---

## 📌 Observações

- As aulas criadas ficam em memória (lista `aulasDisponiveis` em `Main`); não são persistidas no banco nesta versão.
- A classe `ConexaoBD` em `dao/` está vazia; a conexão é gerenciada por `database/Conexao`.
- A classe `Usuario` possui `exibirPermissoes()` lançando `UnsupportedOperationException`, indicando que a autenticação ainda não foi implementada.

## Link video

https://youtu.be/qHZE45mbflY
