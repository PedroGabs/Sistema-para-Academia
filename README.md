# [Sistema de Academia] - [Academia de Pilates]
 
## 👥 Integrantes do Grupo
- Alexsandro Mauricio Silva Vasconcelos - 42037514 
- Matheus Henrique Teixiera Santos - 43608124
- Pedro Gabriel Castro da Silva - 428321522 
- Tharik lima da Silva - 43676642
- Viniius Firmino Souto - 42732972
- Wendel Rodrigues Macedo - 42900905
 
## 📋 Tema Escolhido
[Academia]
 
## 🎯 Objetivo do Sistema
[O Sistema para Academia é uma aplicação desenvolvida em Java com o objetivo de gerenciar as principais operações de uma academia, como cadastro de alunos, controle de instrutores e organização das informações. O projeto foi criado com foco em praticar conceitos fundamentais de programação orientada a objetos, como encapsulamento, uso de classes, listas e organização de dados.

A aplicação simula um cenário real, permitindo o gerenciamento básico de uma academia de forma simples e funcional. Além disso, o projeto busca evoluir continuamente, incorporando boas práticas de desenvolvimento, como separação em camadas (model, service, repository), validações de dados e organização do código para facilitar manutenção e escalabilidade.

Como próximos passos, o sistema pode ser expandido com a integração a um banco de dados (como PostgreSQL), implementação de controle de mensalidades, status de alunos e até a transformação em uma API REST utilizando Spring Boot. O objetivo é tornar o projeto cada vez mais próximo de uma aplicação utilizada no mercado profissional.]
 
## 📦 Funcionalidades Principais
- Cadastro de alunos, permitindo adicionar novos alunos ao sistema com seus dados básicos
- Listagem de alunos cadastrados para visualização e controle das informações
- Gerenciamento de instrutor, possibilitando associar um instrutor à academia
- Armazenamento em memória utilizando listas (ArrayList) para manipulação dinâmica dos dados
- Estrutura preparada para expansão, permitindo futuras funcionalidades como edição/remoção de alunos, controle de mensalidades e integração com banco de dados
 
## 🏗️ Estrutura de Classes (Planejada)
- **Classe Academia:** Responsável por gerenciar a academia, mantendo a lista de alunos e o instrutor, além de centralizar as operações principais do sistema
- **Classe Aluno:** Representa os alunos da academia, armazenando dados como nome, idade e outras informações relevantes
- **Classe Instrutor:** Representa o instrutor da academia, contendo dados e responsabilidades relacionadas ao acompanhamento dos alunos
- **Classe Main:** Responsável por executar o sistema, controlar o fluxo principal e interagir com o usuário via terminal
- **Classe (Futura)** Mensalidade: Responsável por gerenciar pagamentos, controle de planos e status financeiro dos alunos
 
## 🔄 Regra de Negócio Complexa
[Cálculo automático do status do aluno com base no pagamento da mensalidade. O sistema deve verificar se o aluno está com a mensalidade em dia, considerando a data de vencimento e a data atual. Caso o pagamento esteja atrasado, o aluno passa automaticamente para o status "inativo" ou "pendente", podendo também gerar uma taxa de atraso após determinado período.

Além disso, a regra pode incluir diferentes tipos de planos (mensal, trimestral, anual), onde cada um possui valores, prazos e possíveis descontos específicos. O sistema deve calcular corretamente o valor a ser pago, aplicar descontos quando necessário e atualizar o status do aluno de forma automática conforme as regras definidas.

Essa lógica garante um controle mais realista e automatizado da academia, simulando o funcionamento de sistemas utilizados no mercado, onde o acesso e a permanência do aluno dependem diretamente da regularidade dos pagamentos.]


