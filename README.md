# AgendaProject
Projeto AgendaAPI

# Agenda de Serviços com Validação de CEP e Controle de Usuários

## Descrição do Projeto

Este é um projeto de **Agenda de Serviços**, desenvolvido em Java usando o **Spring Boot** e **Thymeleaf** para o front-end, com integração ao **ViaCEP** para validação e preenchimento automático de endereços. O projeto tem como principal objetivo gerenciar o agendamento de serviços entre clientes e funcionários, permitindo o cadastro e gerenciamento de serviços, validação de horários e controle de acesso de usuários.

## Funcionalidades Principais

- **Cadastro e Gerenciamento de Serviços**:
  - Todo serviço é cadastrado com o status padrão "False".
  - Após o serviço ser realizado, o status é alterado para "True".
  - Serviços com status "True" não podem ser editados ou excluídos.
  - Serviços com status "False" podem ser editados e excluídos.

- **Validação de Agendamentos**:
  - O sistema valida a inserção de novos serviços verificando se já existe um cliente agendado para o mesmo dia e horário.
  - Caso já exista, um novo agendamento para o mesmo cliente e horário não será inserido.

- **Validação de Endereço**:
  - O sistema consome a API dos Correios (ViaCEP) para validar o CEP inserido e preencher automaticamente os campos de endereço.

- **Controle de Usuários**:
  - Os usuários possuem diferentes tipos de acesso, como administrador e cliente, definidos pela entidade `TipoAcesso`.

O projeto segue o seguinte diagrama de classes, com entidades como `Agenda`, `Cliente`, `Funcionário`, `Serviço`, `Endereço`, `Usuário`, e `TipoAcesso`. 

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA** (para o gerenciamento de repositórios)
- **Thymeleaf** (para o front-end)
- **H2 Database** (banco de dados em memória)
- **ViaCEP API** (para a consulta de CEPs)
- **Maven** (gerenciamento de dependências)

## Requisitos para Execução

- **JDK 17+**
- **Maven** instalado
- **Git** para controle de versão


## Endpoints Principais

- **/agenda**: Gerenciamento de agendamentos de serviços.
- **/cliente**: Cadastro e gerenciamento de clientes.
- **/funcionario**: Cadastro e gerenciamento de funcionários.
- **/usuario**: Cadastro e controle de usuários.
- **/servico**: Cadastro de novos serviços.
  
## Exemplo de Cadastro de Serviço

Para cadastrar um novo serviço:
1. Acesse a página de **Cadastro de Serviço** via `/servico/cadastro`.
2. Preencha os dados do serviço, incluindo **cliente**, **funcionário** e **data do agendamento**.
3. Clique em **Salvar**. O status do serviço será automaticamente marcado como **False** até sua conclusão.

## Configuração do Banco de Dados (H2)

Por padrão, o projeto está configurado para usar o banco de dados em memória **H2**. Para acessar o console de administração do H2, utilize a URL: http://localhost:8080/h2-console 

Futuras Melhorias
Implementação de relatórios de agendamento.
Integração com outros serviços de pagamento.
Implementação de autenticação e autorização usando Spring Security.
Integração com serviços de envio de e-mails.



