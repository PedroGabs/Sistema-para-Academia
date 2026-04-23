# 🏋️ Sistema de Academia - CP2

## 👥 Integrantes do Grupo

* Alexsandro Mauricio Silva Vasconcelos - 42037514
* Matheus Henrique Teixeira Santos - 43608124
* Pedro Gabriel Castro da Silva - 428321522
* Tharik Lima da Silva - 43676642
* Vinícius Firmino Souto - 42732972
* Wendel Rodrigues Macedo - 42900905

---

## 📋 Tema

Sistema de gerenciamento para Academia

---

## 🎯 Objetivo do Sistema

O sistema tem como objetivo gerenciar as operações básicas de uma academia, permitindo o cadastro e controle de alunos e instrutores.

Nesta segunda etapa (CP2), o foco principal foi a aplicação de conceitos de **Programação Orientada a Objetos (POO)**, especialmente:

* Herança
* Polimorfismo
* Sobrescrita de métodos
* Sobrecarga de métodos
* Uso de `super` e `@Override`

---

## 🧠 Conceitos de POO Aplicados

### 🔹 Herança

Foi criada uma hierarquia de classes:

```
Pessoa (superclasse)
├── Aluno (subclasse)
└── Instrutor (subclasse)
```

A classe `Pessoa` concentra atributos comuns como nome e idade, enquanto `Aluno` e `Instrutor` possuem comportamentos específicos.

---

### 🔹 Sobrescrita de Métodos (@Override)

O método `exibirInfo()` foi definido na superclasse e sobrescrito nas subclasses:

* `Aluno` adiciona informações de plano e instrutor
* `Instrutor` exibe quantidade de alunos

Também foi utilizado:

* `@Override`
* `super.exibirInfo()`

---

### 🔹 Polimorfismo

Foi utilizado polimorfismo com:

```java
ArrayList<Pessoa> pessoas = new ArrayList<>();
```

Permitindo armazenar objetos de diferentes tipos (`Aluno` e `Instrutor`) na mesma lista.

Durante a execução:

```java
for (Pessoa p : pessoas) {
    p.exibirInfo();
}
```

Cada objeto executa seu comportamento específico.

---

### 🔹 Sobrecarga de Métodos (Overloading)

Na classe `Aluno`, foi implementado:

```java
public void pagarMensalidade()
public void pagarMensalidade(double valor)
```

Mesmo método com diferentes parâmetros.

---

### 🔹 Uso de `super`

Utilizado em:

* Construtores (`super(nome, idade)`)
* Métodos (`super.exibirInfo()`)

---

## 📦 Funcionalidades do Sistema

* Cadastro de alunos
* Listagem de pessoas (polimórfica)
* Definição de instrutor
* Associação de alunos a instrutor
* Exibição de informações detalhadas
* Execução via terminal (CLI)

---

## 🏗️ Estrutura de Classes

* **Pessoa (superclasse)**
  Contém atributos comuns e método `exibirInfo()`

* **Aluno (subclasse)**
  Possui plano e instrutor, além de sobrescrever métodos

* **Instrutor (subclasse)**
  Gerencia lista de alunos

* **Plano**
  Representa o tipo de plano do aluno

* **Main**
  Controla o fluxo do sistema e interação com o usuário

---

## 💻 Tecnologias Utilizadas

* Java
* Programação Orientada a Objetos (POO)
* Estruturas de Dados (ArrayList)

---

## 🚀 Considerações Finais

O projeto demonstra a aplicação prática dos principais conceitos de orientação a objetos, permitindo melhor organização, reutilização e escalabilidade do código.

Como melhorias futuras, o sistema pode evoluir para:

* Integração com banco de dados (PostgreSQL)
* Implementação de API REST (Spring Boot)
* Interface gráfica
* Controle de mensalidades e pagamentos

---
