# 📚 Sistema de Notas Escolar

> Sistema completo de gerenciamento acadêmico desenvolvido em Java com autenticação, controle de acesso e persistência de dados.

## 📁 Estrutura do Projeto

```
SistemaNotasEscolar/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── teiacoltec/
│   │   │           └── poo/
│   │   │               ├── Main.java
│   │   │               ├── Pessoa.java
│   │   │               ├── Professor.java
│   │   │               ├── Monitor.java
│   │   │               ├── Aluno.java
│   │   │               ├── Turma.java
│   │   │               ├── Atividade.java
│   │   │               ├── Tarefa.java
│   │   │               ├── Nota.java
│   │   │               ├── Autenticacao.java
│   │   │               ├── GerenciadorDados.java
│   │   │               └── [exceções personalizadas]
│   │   └── resources/
│   └── test/
│       └── java/
├── target/
│   └── classes/
├── docs/
├── lib/
├── build.bat
├── run.bat
├── .gitignore
└── README.md
```

## 🚀 Como Executar

### Opção 1: Scripts Automatizados (Recomendado)
```bash
# Compilar o projeto
build.bat

# Executar o sistema
run.bat
```

### Opção 2: Comandos Manuais
```bash
# Compilar
javac -d target/classes -cp src/main/java src/main/java/org/teiacoltec/poo/*.java

# Executar
java -cp target/classes org.teiacoltec.poo.Main
```

## Funcionalidades Principais

### 🔐 Sistema de Autenticação
- Login seguro com criptografia SHA-256
- Controle de acesso baseado em perfis (Professor, Monitor, Aluno)
- Sessões de usuário com logout

### 👥 Gerenciamento de Usuários
- **Professores**: Acesso completo ao sistema
- **Monitores**: Visualização de turmas e alunos
- **Alunos**: Visualização de suas próprias informações

### 📚 Gerenciamento de Turmas
- Criação e edição de turmas
- Associação de participantes (professores, monitores, alunos)
- Hierarquia de turmas (turmas pai e filhas)

### 📝 Sistema de Atividades
- Criação de atividades com datas e valores
- Associação de atividades a turmas
- Controle de prazos

### 💾 Persistência de Dados
- Serialização automática dos dados
- Carregamento automático na inicialização
- Backup seguro das informações

## Estrutura do Projeto

```
src/org/teiacoltec/poo/tp3/
├── Pessoa.java                    # Classe abstrata base para usuários
├── Professor.java                 # Classe para professores
├── Monitor.java                   # Classe para monitores
├── Aluno.java                     # Classe para alunos
├── Turma.java                     # Gerenciamento de turmas
├── Atividade.java                 # Gerenciamento de atividades
├── Autenticacao.java              # Sistema de login/logout
├── GerenciadorDados.java          # Persistência de dados
├── Main.java                      # Classe principal
└── Exceções/
    ├── CredenciaisInvalidasException.java
    ├── AcessoNaoAutorizadoException.java
    └── [outras exceções personalizadas]
```

## Tecnologias Utilizadas

- **Java 8+** com recursos modernos:
  - Streams API para processamento de coleções
  - Lambdas para programação funcional
  - Optional para tratamento de valores nulos
- **Serialização Java** para persistência
- **SHA-256** para criptografia de senhas
- **Padrões de Design**:
  - Strategy Pattern (diferentes tipos de usuário)
  - Singleton Pattern (gerenciamento de dados)
  - Exception Handling personalizado

## Como Compilar e Executar

### Pré-requisitos
- Java JDK 8 ou superior
- Terminal/Prompt de comando

### Compilação
```bash
# Navegar até o diretório do projeto
cd Trabalho_POO_java

# Compilar todos os arquivos
javac -d . src\org\teiacoltec\poo\tp3\*.java
```

### Execução
```bash
# Executar o sistema
java org.teiacoltec.poo.tp3.Main
```

## Usuários de Teste

O sistema cria automaticamente os seguintes usuários para teste:

### Professor
- **Login**: prof.silva
- **Senha**: senha123
- **Acesso**: Completo (todas as funcionalidades)

### Monitor
- **Login**: monitor.joao
- **Senha**: senha123
- **Acesso**: Visualização de turmas e alunos

### Aluno
- **Login**: aluno.maria
- **Senha**: senha123
- **Acesso**: Visualização de informações pessoais

## Funcionalidades por Perfil

### 👨‍🏫 Professor
- Visualizar todas as turmas
- Gerenciar participantes
- Criar e editar atividades
- Visualizar relatórios completos
- Acesso a todas as funcionalidades administrativas

### 👨‍💻 Monitor
- Visualizar turmas onde atua como monitor
- Ver lista de alunos das turmas
- Consultar informações básicas

### 👨‍🎓 Aluno
- Visualizar informações pessoais
- Consultar turmas em que está matriculado
- Ver atividades e notas (quando implementado)

## Características Técnicas

### Programação Orientada a Objetos
- **Herança**: Hierarquia de classes Pessoa → Professor/Monitor/Aluno
- **Polimorfismo**: Métodos sobrescritos para diferentes comportamentos
- **Encapsulamento**: Atributos privados com getters/setters
- **Abstração**: Classe abstrata Pessoa e interfaces implícitas

### Programação Funcional
- Uso extensivo de **Streams API** para processamento de dados
- **Lambdas** para operações de filtro e mapeamento
- **Method References** para código mais limpo
- **Optional** para evitar NullPointerException

### Tratamento de Exceções
- Exceções personalizadas para diferentes cenários
- Tratamento robusto de erros de I/O
- Validação de entrada do usuário

### Segurança
- Criptografia SHA-256 para senhas
- Controle de acesso baseado em perfis
- Validação de credenciais

## Autores

- **Izabela Cassiano**
- **Julia Franco**

---

**Disciplina**: Programação Orientada a Objetos  
**Instituição**: TEIAC - COLTEC  
**Ano**: 2024

## Observações

Este projeto demonstra a aplicação prática de conceitos avançados de POO, incluindo herança, polimorfismo, tratamento de exceções, programação funcional com Streams, e persistência de dados. O sistema é totalmente funcional e pode ser expandido com novas funcionalidades conforme necessário.