# 🚀 Padrões de Commits e GitHub

Os repositórios do projeto estão na organização do GitHub: [Grupo-6-2SISA](https://github.com/Grupo-6-2SISA)

## 📌 Formato do Commit

```bash
<tipo>(<escopo opcional>): <descrição curta no presente e imperativo>

[Corpo opcional explicando o motivo da mudança, o que foi alterado e impactos]

[Issue relacionada, se houver]
```

## 📊 Tipos de Commits (Conventional Commits)

| Prefixo        | Significado                                         |
| -------------- | --------------------------------------------------- |
| 🎉 `feat`      | Adição de nova funcionalidade                       |
| 🐛 `fix`       | Correção de bugs                                    |
| 🏗️ `refactor` | Refatoração do código (sem mudar funcionalidade)    |
| 🛠️ `chore`    | Mudanças na configuração, dependências              |
| 🎨 `style`     | Alterações de formatação, lint, espaços, indentação |
| 🧪 `test`      | Adição ou correção de testes                        |
| 📖 `docs`      | Alterações na documentação                          |
| 🔧 `ci`        | Mudanças na configuração de CI/CD                   |
| ⚡ `perf`       | Melhorias de performance                            |
| ⏪ `revert`     | Reversão de commit                                  |

## 🏆 Exemplos de Commits Bem Escritos

### ✅ Commit Simples

```bash
feat(user): adicionar validação de e-mail no cadastro
```

### 📜 Commit com Corpo Explicativo

```bash
fix(auth): corrigir erro na geração de token JWT

O erro acontecia porque o tempo de expiração estava sendo passado como string 
ao invés de um número inteiro. Agora, a conversão para `Long` foi corrigida.

Fixes #42
```

### 🔄 Commit para Atualização de Dependências

```bash
chore(deps): atualizar Spring Boot para versão 3.1.0
```

# 🚦Padrão de branches

| Branch                                        | Descrição                               |
| --------------------------------------------- | --------------------------------------- |
| 🌍 `main`                                     | versão estável do projeto.             |
| ⚙ `develop`                                  | branch principal para desenvolvimento. |
| 🔛 `feature/nome-da-feature`                  | novas funcionalidades.                 |
| 🐞 `bugfix/nome-do-bug`                       | correções de bugs.                     |
| 🔥 `hotfix/nome-do-hotfix`                    | correções urgentes na produção.        |
| 🔖 `release/versao`                           | preparação de novas versões.          |



## Criar uma nova branch
Para criar uma nova branch e mudar para ela, use o comando:
```bash 
git checkout -b nome-da-branch
```
Ou, se você estiver usando uma versão mais recente do Git, pode usar:
```bash
git switch -c nome-da-branch
```
Exemplo: Se você quiser criar uma branch chamada feature/nova-funcionalidade:
```bash
git checkout -b feature/nova-funcionalidade
```
Ou:
```bash
git switch -c feature/nova-funcionalidade
```
Isso cria a branch e muda automaticamente para ela.

## Dar push para o repositório remoto
Depois de fazer o commit, envie (push) a nova branch para o repositório remoto com o comando:
```bash
git push -u origin nome-da-branch
```
Exemplo:
```bash
git push -u origin feature/nova-funcionalidade
```
O -u (ou --set-upstream) faz com que a branch local seja associada à branch remota, então nas próximas vezes, você pode apenas rodar git push ou git pull sem precisar especificar o nome da branch.

## Verificar se o push foi bem-sucedido
Para verificar se a branch foi enviada corretamente para o repositório remoto, você pode rodar:
```bash
git branch -r
```
Isso mostrará todas as branches remotas.