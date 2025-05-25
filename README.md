# Projeto A3 - Refatoração com Clean Code e Testes Automatizados

Este projeto representa a refatoração completa do repositório original [`ProjetoA3`](https://github.com/LipeNascimento/ProjetoA3.git), com foco em aplicar os princípios de Clean Code, boas práticas de arquitetura MVC, além de testes unitários automatizados com JUnit 5 e Mockito.

👉 Repositório Original: [`ProjetoA3`](https://github.com/LipeNascimento/ProjetoA3.git)  
🚀 Repositório Reestruturado: [`ProjetoA3CleanCode`](https://github.com/LipeNascimento/ProjetoA3CleanCode.git)

---

## 📌 Objetivo

Sistema desktop em Java para gerenciamento de fazendas, colheitas e estoques agrícolas, utilizando SQLite como banco de dados local e interface gráfica com Swing.

---

## 🔄 Mudanças Realizadas na Refatoração

| Item                          | Antes (`ProjetoA3`)                          | Depois (`ProjetoA3CleanCode`)                  |
|-------------------------------|----------------------------------------------|------------------------------------------------|
| 📁 Estrutura de diretórios     | Desorganizada, acoplada                     | Padrão Maven (`src/main/java`, `src/test/java`)|
| ⚙️ Build e Execução            | Manual pelo Eclipse                         | Automatizada via Maven                         |
| 🔌 Dependências                | Nenhuma                                     | Gerenciadas no `pom.xml`                       |
| ✅ Testes Unitários            | Ausentes                                    | Presentes com JUnit 5 e Mockito                |
| 🧠 Organização do código       | Alta duplicidade, acoplamento               | Separação por camadas `model`, `controller`, `view` |
| 🧪 Testabilidade               | Inexistente                                 | 100% testável, com mock de repositórios        |
| 🗃️ Banco de Dados              | SQLite sem abstração                        | JDBC com classe `DatabaseConnection` central   |
| 🧼 Nomes e legibilidade        | Nomes genéricos e inconsistentes            | Legíveis, padronizados                         |
| 🧰 Plugins Maven               | Não utilizado                               | `maven-surefire-plugin`, `exec-maven-plugin`  |

---

## 🧪 Testes Automatizados

O projeto agora possui cobertura de testes para:

- ✅ Classes de controller:`FarmController`, `HarvestController`, `InventoryController`
- ✅ Classes de modelo: `Farm`, `Harvest`, `Inventory`

Execute os testes com:

```bash
mvn test
```

---

## ▶️ Como Executar

Após clonar o projeto e instalar as dependências Maven, execute:

```bash
mvn clean install
mvn exec:java
```

O sistema será iniciado com a interface gráfica `MainFrame`.

---

## 💡 Vantagens Obtidas com a Refatoração

- ✅ **Manutenibilidade**: código mais limpo e dividido por responsabilidade
- ✅ **Testabilidade**: suporte a TDD com cobertura automatizada
- ✅ **Modularidade**: facilitando extensões e futuras integrações
- ✅ **Execução padronizada** com `mvn exec:java`
- ✅ **Pronto para CI/CD** com estrutura de testes e build Maven

---

## 📦 Tecnologias Utilizadas

- Java 17
- Maven
- JUnit 5
- Mockito
- SQLite (via `sqlite-jdbc`)
- Swing (para GUI)

---

## 📁 Clonando e Executando

```bash
git clone https://github.com/LipeNascimento/ProjetoA3CleanCode.git
cd ProjetoA3CleanCode
mvn clean install
mvn exec:java
```

---

## 👨‍💻 Autor

Refatorado por:

- Felipe Reis Nascimento – RA: 323210811
- Weslley Bruno Almeida Santos – RA: 32328864
- Charles Miller De Souza Nascimento – RA: 32326802

Com foco em melhorar qualidade, organização e robustez do código do projeto original [`ProjetoA3`](https://github.com/LipeNascimento/ProjetoA3.git), como parte de um trabalho acadêmico sobre Clean Code, JUnit e boas práticas de desenvolvimento Java.

---

## 📸 Vídeo do Projeto

...

---

## 📝 Licença

Este projeto é de uso acadêmico da UC: Gestão e Qualidade de Software da instituição de ensino Ânima Educação, sem fins comerciais.

---

Se você gostou ou aprendeu algo novo com este projeto, ⭐ marque o repositório no GitHub!
