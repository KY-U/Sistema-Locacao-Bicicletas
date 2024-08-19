# Trabalho 2 - Desenvolvimento de Software para Web 1:

*Caio Ueda Sampaio - RA : 802215*

*Gabriel Pandolfi Correa dos Santos - RA : 769831*

*Pedro Henrique Borges - RA : 804071*

*Vitor Enzo Araujo Costa - RA : 802123* 


# Aplicação Escolhida:

A aplicação se trata de um sistema simples de gerenciamento de locação de biciletas, contendo implementação de CRUD para manipulação de clientes. 

# Sistema-Locacao-Bicicletas

O sistema deve possuir:

1. **Cadastro de clientes**, com os seguintes dados: 
   - e-mail
   - senha
   - CPF
   - nome
   - telefone
   - sexo
   - data de nascimento.

2. **Cadastro de locadoras**, com os seguintes dados:
   - e-mail
   - senha
   - CNPJ
   - nome
   - cidade.

3. **Cadastro de locações**, com os seguintes dados:
   - CPF do cliente
   - CNPJ da locadora
   - dia/horário da locação.
   
   Assume-se que a duração da locação é de 1 hora e sempre inicia-se em "hora cheia" (13h 00min, etc).

## Requisitos do Sistema

1. **R1: CRUD de clientes** (requer login de administrador)
2. **R2: CRUD de locadoras** (requer login de administrador)
3. **R3: Listagem de todas as locadoras** em uma única página (não requer login)
4. **R4: Listagem de todas as locadoras por cidade** (não requer login)
5. **R5: Locação de uma bicicleta em uma locadora** (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode cadastrar uma locação. Para isso, deve escolher uma locadora (escolhendo a partir de uma lista), uma data e deve ser gravada a locação na base de dados. Após a efetivação da locação, o cliente e a locadora devem ser informados (via e-mail) sobre a locação realizada.
6. **R6: Listagem de todas as locações de um cliente** (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode visualizar todas as suas locações gravadas.
7. **R7: O sistema não deve permitir o cadastro de locações de um mesmo cliente ou de uma mesma locadora em um mesmo dia/horário.**
8. **R8: Listagem de todas as locações de uma locadora** (requer login da locadora via e-mail + senha). Depois de fazer login, a locadora pode visualizar todas as suas locações gravadas.
9. **R9: O sistema deve ser internacionalizado em pelo menos dois idiomas: português e outro de sua escolha.**
10. **R10: O sistema deve validar (tamanho, formato, etc) todas as informações (campos nos formulários) cadastradas e/ou editadas.** 


## Tratamento de Erros

O sistema deve tratar todos os erros possíveis (cadastros duplicados, problemas técnicos, etc.), mostrando uma página de erros amigável ao usuário e registrando o erro no console.

## Tecnologias Utilizadas:

   **Lado Servidor:**

   - Spring MVC, Spring Data JPA, Spring Security & Thymeleaf

   **Lado Cliente:**

   - Javascript & CSS

## Arquitetura 

- Modelo-Visão-Controlador (MVC)

## Ambiente de Desenvolvimento

- A compilação e o deployment devem ser obrigatoriamente realizados via Maven.
- Os arquivos fonte do sistema devem estar hospedados obrigatoriamente em um repositório (preferencialmente GitHub).

## Notas

1. CRUD: Create, Read, Update & Delete.
