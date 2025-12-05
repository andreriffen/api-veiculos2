# SUMÁRIO: API Veículos 2 - O Ataque dos Clones

### 📽️ [Vídeo executando ↗](https://drive.google.com/drive/u/1/folders/1ck8xFWNUwzbvIK7b63seBT5IxmwkpVju)

Porque literalmente esse projeto é um clone de [**API Veículos 1 - Mão na Massa 1**](https://github.com/andreriffen/api-veiculos1)

> **"AAAAARRRRGGGHHHH WRRHWRWWHW HUURH!"** 
> — *Chewbacca 👹*

![Java 17+](https://img.shields.io/badge/Java%2017%2B-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot 3.4.0](https://img.shields.io/badge/Spring%20Boot%203.4.0-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Maven 3.6+](https://img.shields.io/badge/Maven%203.6%2B-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![Alpine Linux](https://img.shields.io/badge/Alpine%20Linux-0D597F?style=flat-square&logo=alpinelinux&logoColor=white)
![Codespaces Ready](https://img.shields.io/badge/Codespaces-Ready-success?style=flat-square&logo=github&logoColor=white)


        🔴 🟩🟩    __/\\\\\\\\\\\___/\\\\\\\\\\\\\\\_____/\\\\\\\\\\\__________/\\\\\\\\\_        
        🟩 🟩        _\/////\\\///___\/\\\///////////____/\\\/////////\\\_____/\\\////////__       
        🟩 🟩🟩      _____\/\\\______\/\\\______________\//\\\______\///____/\\\/___________      
        🟩 🟩          _____\/\\\______\/\\\\\\\\\\\_______\////\\\__________/\\\_____________     
                         _____\/\\\______\/\\\///////___________\////\\\______\/\\\_____________    
                          _____\/\\\______\/\\\_____________________\////\\\___\//\\\____________   
                            ____\/\\\______\/\\\______________/\\\______\//\\\___\///\\\__________  
                             __/\\\\\\\\\\\_\/\\\_____________\///\\\\\\\\\\\/______\////\\\\\\\\\_ 
                              _\///////////__\///________________\///////////___________\/////////__



> Evolução direta do [api-veiculos1](https://github.com/andreriffen/api-veiculos1). Se você precisa do passo a passo completo, diagramas e prints detalhados, consulte o projeto original. Aqui vamos direto ao que importa.

## 🚀 Início Rápido

### 😺 Codespaces Github (Recomendado)

- Clone e abra com Codespace `Code ▾` → `Codespaces +`).
- Aguarde a tarefa `🚀 Startup API`
- Abra o terminal Bash e execute:

    ```bash
    ./populate-data.sh  # inserir dados com credenciais no H2
    ./test-api.sh       # testar interações CRUD POST/GET/PUT/DELETE
    ```

    ---
🔎 Se quiser acessar o terminal H2, acesse a sua URL:

    > ✅ Correto: https ://nome-aletorio-123457890-8080.app.github.dev/h2-console ✅ (sem :8080 injetado)

    > ❌ Errado: https ://nome-aletorio-1234567890-8080.app.github.dev:8080/h2-console ❌ (com :8080 injetado)

(JDBC `jdbc:h2:mem:apiveiculos2`).

---

### Outras IDEs

NetBeans, Sublime ou VS-Code local + terminal (Windows, macOS, Linux):

- Requisitos: JDK 17+
- Clone o projeto, descompacte e execute:

```bash
./mvnw clean package -DskipTests        # compilar a build Maven
./mvnw spring-boot:run                  # executar o server API
```

Compilou? Rode isso:

```bash
./populate-data.sh      # insere dados de exemplo já com credenciais
./test-api.sh           # testar interações CRUD POST/GET/PUT/DELETE
```

Opcional: Experimente rodar `./startup.sh` pra compilar e rodar o server da API na sequência.

---

### 🔐 Interagir direto no terminal - Como os Maias e Aztecas faziam

<details>
<summary>Clique para abrir o manual</summary>

> ⚠️ A API agora tem porteiro. Sem credenciais, nada feito.

- **Credenciais padrão**: `admin` / `admin123` (criados no `SecurityConfig`).
- **Export no shell atual** (garante que `curl` enxergue as variáveis):
    ```bash
    export AUTH_USER=admin
    export AUTH_PASS=admin123
    ```

- **Modo clássico** – `curl -s -u "$AUTH_USER:$AUTH_PASS" http://localhost:8080/marcas`
- **Modo filme dos anos 90** – `curl -s "http://$AUTH_USER:$AUTH_PASS@localhost:8080/marcas"`
- **Modo cientista** – `curl -s -H "Authorization: Basic $(printf '%s' "$AUTH_USER:$AUTH_PASS" | base64)" http://localhost:8080/marcas`
- **Modo ninja** – `curl -s -u "$AUTH_USER:$AUTH_PASS" http://localhost:8080/marcas --show-error --fail`

Quer deixar o terminal apresentável sem virar roteirista de shell?

- **Tem Python? roda isso aqui:**
    ```bash
    curl -s -u "$AUTH_USER:$AUTH_PASS" http://localhost:8080/marcas | python -m json.tool
    ```
    Não tem? Instala rapidinho ➡️ https://www.python.org/downloads/
- **Quer JSON colorido?**
    ```bash
    curl -s -u "$AUTH_USER:$AUTH_PASS" http://localhost:8080/marcas | jq '.'
    ```
    Precisa do `jq`: https://stedolan.github.io/jq/download/
- **Só os nomes, por favor:**
    ```bash
    curl -s -u "$AUTH_USER:$AUTH_PASS" http://localhost:8080/marcas | jq -r '.[].nome'
    ```
- **Json bonito em modo scroll:**
    ```bash
    curl -s -u "$AUTH_USER:$AUTH_PASS" http://localhost:8080/marcas | jq '.' | less -R
    ```
    (pressione `q` para sair)

Use qualquer uma antes/ao invés dos scripts `populate-data.sh` ou `test-api.sh` para entender o fluxo manualmente.

</details>

---

## 🌀 Mão na Massa 1 - Projeto Anterior

- **[Instruções de Execução](https://github.com/andreriffen/api-veiculos1/blob/main/INSTRUCTIONS.md)** (Api-veiculos1) - Guia passo a passo para executar o projeto
- **[Repositório GitHub](https://github.com/andreriffen/api-veiculos1)** (Api-veiculos1 ) - Código fonte completo

---

## 📜 Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

---

## ☕ Autor

- 2025 ©️ - [**Andre Riffen**](https://andreriffen.github.io/resume) | [@andreriffen (Github)](https://github.com/andreriffen)


