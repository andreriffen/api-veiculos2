#!/bin/bash
#
  # ==============================================================================
  # Script para Popular a API de Veículos com Dados Iniciais
  # ==============================================================================
  # Este script cria marcas, cores, clientes, modelos e veículos de exemplo
  # ==============================================================================

BASE_URL="http://localhost:8080"
AUTH_USER="${AUTH_USER:-admin}"
AUTH_PASS="${AUTH_PASS:-admin123}"
AUTH_CREDENTIALS="$AUTH_USER:$AUTH_PASS"

# Manual de sobrevivência (dobrável): recolha o bloco AUTH_TIPS se estiver com pressa.
: <<'AUTH_TIPS'
╔════════════════════════════════════════════════════════════════╗
║  📚 MANUAL DE SOBREVIVÊNCIA DA API - EDIÇÃO HTTP BASIC 🔐       ║
║  Para quem decidiu encarar a API no braço, sem rodinhas. 💪     ║
╚════════════════════════════════════════════════════════════════╝

⚡ 4 FORMAS SECRETAS DE PASSAR PELA PORTARIA ⚡

🎯 MÉTODO 1 — Clássico Discreto
  export AUTH_USER=alice AUTH_PASS=segredo
  curl -s -u "$AUTH_CREDENTIALS" "$BASE_URL/marcas"
  ✔️ Sem drama, sem spoiler.

🎬 MÉTODO 2 — Filme dos anos 90
  curl -s "http://$AUTH_CREDENTIALS@localhost:8080/marcas"
  🕶️ Hacker de cinema faria exatamente isso.

🔬 MÉTODO 3 — Liquidificador Base64
  curl -s -H "Authorization: Basic $(printf '%s' "$AUTH_CREDENTIALS" | base64)" \
     "$BASE_URL/marcas"
  🧪 user:pass vira smoothie e ninguém vê a mistura.

🦇 MÉTODO 4 — Cavaleiro das Trevas da API
  curl -s -u "$AUTH_CREDENTIALS" "$BASE_URL/marcas" \
     --silent --show-error --fail
  🌃 Stealth mode on, Alfred orgulhoso.

⚠️ Aviso da segurança: faça do seu jeito, mas não diga que faltou dica.
AUTH_TIPS

RUN_ID=$(date +%s)
UNIQUE_SUFFIX="Lote-$RUN_ID"
PLATE_SUFFIX_1=$(printf "%02d" $((RUN_ID % 100)))
PLATE_SUFFIX_2=$(printf "%02d" $(((RUN_ID + 1) % 100)))
PLATE_SUFFIX_3=$(printf "%02d" $(((RUN_ID + 2) % 100)))
PLATE_SUFFIX_4=$(printf "%02d" $(((RUN_ID + 3) % 100)))
PLACA1="ABC1A$PLATE_SUFFIX_1"
PLACA2="DEF2B$PLATE_SUFFIX_2"
PLACA3="GHI3C$PLATE_SUFFIX_3"
PLACA4="JKL4D$PLATE_SUFFIX_4"

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║      🚗 Populando API de Veículos - Dados Iniciais            ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║           🔐 Camada de Segurança HTTP Basic Ativa             ║"
echo "╠════════════════════════════════════════════════════════════════╣"
echo "║   👤 Usuário........: $AUTH_USER"
echo "║   🔑 Senha..........: $AUTH_PASS"
echo "║   💡 Dica: exporte AUTH_USER e AUTH_PASS antes de executar     ║"
echo "║       o script caso deseje outros credenciais temporários.     ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""
echo "📡 Todas as requisições usarão autenticação básica (-u $AUTH_CREDENTIALS)"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "🆔 Sufixo único desta execução: $UNIQUE_SUFFIX"
echo ""

# ==============================================================================
# CRIANDO MARCAS
# ==============================================================================

echo "🏭 Criando Marcas..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "   🔐 Endpoint protegido: POST /marcas"
echo ""

# ==============================================================================
# CRIANDO MARCA FIAT
# ==============================================================================

echo "📝 Criando marca: Fiat"
MARCA1=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/marcas" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Fiat $UNIQUE_SUFFIX\"
}" | jq -r '.id')
echo "   ✅ Marca criada com ID: $MARCA1"
echo ""

# ==============================================================================
# CRIANDO MARCA VOLKSWAGEN
# ==============================================================================

echo "📝 Criando marca: Volkswagen"
MARCA2=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/marcas" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Volkswagen $UNIQUE_SUFFIX\"
}" | jq -r '.id')
echo "   ✅ Marca criada com ID: $MARCA2"
echo ""

# ==============================================================================
# CRIANDO MARCA TOYOTA
# ==============================================================================

echo "📝 Criando marca: Toyota"
MARCA3=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/marcas" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Toyota $UNIQUE_SUFFIX\"
}" | jq -r '.id')
echo "   ✅ Marca criada com ID: $MARCA3"
echo ""

# ==============================================================================
# CRIANDO CORES
# ==============================================================================

echo "🎨 Criando Cores..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "   🔐 Endpoint protegido: POST /cores"
echo ""

# ==============================================================================
# CRIANDO COR PRETA
# ==============================================================================

echo "📝 Criando cor: Preto"
COR1=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/cores" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Preto $UNIQUE_SUFFIX\"
}" | jq -r '.id')
echo "   ✅ Cor criada com ID: $COR1"
echo ""

# ==============================================================================
# CRIANDO COR BRANCA
# ==============================================================================

echo "📝 Criando cor: Branco"
COR2=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/cores" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Branco $UNIQUE_SUFFIX\"
}" | jq -r '.id')
echo "   ✅ Cor criada com ID: $COR2"
echo ""

# ==============================================================================
# CRIANDO COR PRATA
# ==============================================================================

echo "📝 Criando cor: Prata"
COR3=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/cores" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Prata $UNIQUE_SUFFIX\"
}" | jq -r '.id')
echo "   ✅ Cor criada com ID: $COR3"
echo ""

# ==============================================================================
# CRIANDO COR VERMELHA
# ==============================================================================

echo "📝 Criando cor: Vermelho"
COR4=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/cores" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Vermelho $UNIQUE_SUFFIX\"
}" | jq -r '.id')
echo "   ✅ Cor criada com ID: $COR4"
echo ""

# ==============================================================================
# CRIANDO CLIENTES
# ==============================================================================

echo "👥 Criando Clientes..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "   🔐 Endpoint protegido: POST /clientes"
echo ""

# ==============================================================================
# CRIANDO CLIENTE 'JOÃO SILVA'
# ==============================================================================

echo "📝 Criando cliente: João Silva"
CLIENTE1=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/clientes" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"João Silva $UNIQUE_SUFFIX\",
  \"celular\": \"(48) 9$PLATE_SUFFIX_1$PLATE_SUFFIX_2-$PLATE_SUFFIX_3$PLATE_SUFFIX_4\",
  \"email\": \"joao+$RUN_ID@example.com\",
  \"dataCadastro\": \"2025-12-01\"
}" | jq -r '.id')
echo "   ✅ Cliente criado com ID: $CLIENTE1"
echo ""

# ==============================================================================
# CRIANDO CLIENTE 'MARIA SANTOS'
# ==============================================================================

echo "📝 Criando cliente: Maria Santos"
CLIENTE2=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/clientes" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Maria Santos $UNIQUE_SUFFIX\",
  \"celular\": \"(48) 9$PLATE_SUFFIX_2$PLATE_SUFFIX_3-$PLATE_SUFFIX_4$PLATE_SUFFIX_1\",
  \"email\": \"maria+$RUN_ID@example.com\",
  \"dataCadastro\": \"2025-12-01\"
}" | jq -r '.id')
echo "   ✅ Cliente criada com ID: $CLIENTE2"
echo ""

# ==============================================================================
# CRIANDO CLIENTE 'CARLOS OLIVEIRA'
# ==============================================================================

echo "📝 Criando cliente: Carlos Oliveira"
CLIENTE3=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/clientes" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Carlos Oliveira $UNIQUE_SUFFIX\",
  \"celular\": \"(48) 9$PLATE_SUFFIX_3$PLATE_SUFFIX_4-$PLATE_SUFFIX_1$PLATE_SUFFIX_2\",
  \"email\": \"carlos+$RUN_ID@example.com\",
  \"dataCadastro\": \"2025-12-01\"
}" | jq -r '.id')
echo "   ✅ Cliente criado com ID: $CLIENTE3"
echo ""

# ==============================================================================
# CRIANDO MODELOS
# ==============================================================================

echo "🚙 Criando Modelos..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "   🔐 Endpoint protegido: POST /modelos"
echo ""

# ==============================================================================
# CRIANDO MODELO 'FIAT UNO'
# ==============================================================================

echo "📝 Criando modelo: Uno (Fiat)"
MODELO1=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/modelos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"descricao\": \"Uno $UNIQUE_SUFFIX\",
  \"motor\": {
    \"potencia\": 75,
    \"tipoCombustivel\": \"FLEX\"
  },
  \"marcaId\": $MARCA1
}" | jq -r '.id')
echo "   ✅ Modelo criado com ID: $MODELO1"
echo ""

# ==============================================================================
# CRIANDO MODELO 'VOLKSWAGEN GOL'
# ==============================================================================

echo "📝 Criando modelo: Gol (Volkswagen)"
MODELO2=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/modelos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"descricao\": \"Gol $UNIQUE_SUFFIX\",
  \"motor\": {
    \"potencia\": 80,
    \"tipoCombustivel\": \"GASOLINA\"
  },
  \"marcaId\": $MARCA2
}" | jq -r '.id')
echo "   ✅ Modelo criado com ID: $MODELO2"
echo ""

# ==============================================================================
# CRIANDO MODELO 'TOYOTA COROLLA'
# ==============================================================================

echo "📝 Criando modelo: Corolla (Toyota)"
MODELO3=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/modelos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"descricao\": \"Corolla $UNIQUE_SUFFIX\",
  \"motor\": {
    \"potencia\": 154,
    \"tipoCombustivel\": \"FLEX\"
  },
  \"marcaId\": $MARCA3
}" | jq -r '.id')
echo "   ✅ Modelo criado com ID: $MODELO3"
echo ""

# ==============================================================================
# CRIANDO MODELO 'FIAT PALIO'
# ==============================================================================

echo "📝 Criando modelo: Palio (Fiat)"
MODELO4=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/modelos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"descricao\": \"Palio $UNIQUE_SUFFIX\",
  \"motor\": {
    \"potencia\": 85,
    \"tipoCombustivel\": \"GASOLINA\"
  },
  \"marcaId\": $MARCA1
}" | jq -r '.id')
echo "   ✅ Modelo criado com ID: $MODELO4"
echo ""

# ==============================================================================
# CRIANDO VEÍCULOS
# ==============================================================================

echo "🚗 Criando Veículos..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "   🔐 Endpoint protegido: POST /veiculos"
echo ""

# ==============================================================================
# CRIANDO VEICULO 'UNO PRETO DE JOÃO SILVA'
# ==============================================================================

echo "📝 Criando veículo: $PLACA1 (Uno Preto)"
VEICULO1=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/veiculos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"placa\": \"$PLACA1\",\
  \"observacoes\": \"Veículo em bom estado\",\
  \"corId\": $COR1,\
  \"modeloId\": $MODELO1,\
  \"proprietarioId\": $CLIENTE1\
}" | jq -r '.id')
echo "   ✅ Veículo criado com ID: $VEICULO1"
echo "   🔧 Motor: 75cv FLEX"
echo ""

# ==============================================================================
# CRIANDO VEICULO 'GOL BRANCO DE MARIA SANTOS'
# ==============================================================================

echo "📝 Criando veículo: $PLACA2 (Gol Branco)"
VEICULO2=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/veiculos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"placa\": \"$PLACA2\",\
  \"observacoes\": \"Revisão em dia\",\
  \"corId\": $COR2,\
  \"modeloId\": $MODELO2,\
  \"proprietarioId\": $CLIENTE2\
}" | jq -r '.id')
echo "   ✅ Veículo criado com ID: $VEICULO2"
echo "   🔧 Motor: 80cv GASOLINA"
echo ""

# ==============================================================================  
# CRIANDO VEICULO 'COROLLA PRATA DE CARLOS OLIVEIRA'
# ==============================================================================

echo "📝 Criando veículo: $PLACA3 (Corolla Prata)"
VEICULO3=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/veiculos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"placa\": \"$PLACA3\",\
  \"observacoes\": \"Carro seminovo\",\
  \"corId\": $COR3,\
  \"modeloId\": $MODELO3,\
  \"proprietarioId\": $CLIENTE3\
}" | jq -r '.id')
echo "   ✅ Veículo criado com ID: $VEICULO3"
echo "   🔧 Motor: 154cv FLEX"
echo ""

# ==============================================================================
# CRIANDO VEICULO 'PALIO VERMELHO DE JOÃO SILVA'
# ==============================================================================

echo "📝 Criando veículo: $PLACA4 (Palio Vermelho)"
VEICULO4=$(curl -s -u "$AUTH_CREDENTIALS" -X POST "$BASE_URL/veiculos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"placa\": \"$PLACA4\",\
  \"observacoes\": \"Primeiro veículo\",\
  \"corId\": $COR4,\
  \"modeloId\": $MODELO4,\
  \"proprietarioId\": $CLIENTE1\
}" | jq -r '.id')
echo "   ✅ Veículo criado com ID: $VEICULO4"
echo "   🔧 Motor: 85cv GASOLINA"
echo ""

# ==============================================================================
# RESUMO FINAL
# ==============================================================================

echo ""
echo "╔════════════════════════════════════════════════════════════════╗"
echo "║                   ✅ DADOS POPULADOS COM SUCESSO!              ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""
# Conta quantos IDs foram realmente criados (não vazios)
MARCAS_COUNT=$(echo "$MARCA1 $MARCA2 $MARCA3" | tr ' ' '\n' | grep -c '^[0-9]\+$')
CORES_COUNT=$(echo "$COR1 $COR2 $COR3 $COR4" | tr ' ' '\n' | grep -c '^[0-9]\+$')
CLIENTES_COUNT=$(echo "$CLIENTE1 $CLIENTE2 $CLIENTE3" | tr ' ' '\n' | grep -c '^[0-9]\+$')
MODELOS_COUNT=$(echo "$MODELO1 $MODELO2 $MODELO3 $MODELO4" | tr ' ' '\n' | grep -c '^[0-9]\+$')
VEICULOS_COUNT=$(echo "$VEICULO1 $VEICULO2 $VEICULO3 $VEICULO4" | tr ' ' '\n' | grep -c '^[0-9]\+$')

echo "📊 Resumo:"
echo "   • Marcas criadas: $MARCAS_COUNT"
echo "   • Cores criadas: $CORES_COUNT"
echo "   • Clientes criados: $CLIENTES_COUNT"
echo "   • Modelos criados: $MODELOS_COUNT"
echo "   • Veículos criados: $VEICULOS_COUNT"
echo ""
# ==============================================================================
# DICAS PARA VISUALIZAR OS DADOS E ACESSAR O H2 CONSOLE
# ==============================================================================

echo "🔍 Para visualizar os dados via API:"
echo "   curl -s -u \"$AUTH_CREDENTIALS\" \"$BASE_URL/marcas\"   | jq '.'"
echo "   curl -s -u \"$AUTH_CREDENTIALS\" \"$BASE_URL/cores\"    | jq '.'"
echo "   curl -s -u \"$AUTH_CREDENTIALS\" \"$BASE_URL/clientes\" | jq '.'"
echo "   curl -s -u \"$AUTH_CREDENTIALS\" \"$BASE_URL/modelos\"  | jq '.'"
echo "   curl -s -u \"$AUTH_CREDENTIALS\" \"$BASE_URL/veiculos\" | jq '.'"
echo ""

# ------------------------------------------------------------------------------
# TENTATIVA DE DETECTAR URL DO CODESPACES (VS Code Web no GitHub)
#   - Quando estiver rodando em GitHub Codespaces, normalmente existe a
#     variável de ambiente GITHUB_CODESPACE_BASENAME e GITHUB_CODESPACES.
#   - Também podemos construir a URL base usando o padrão:
#       https://<basename>-8080.github.dev
# ------------------------------------------------------------------------------

CODESPACES_URL=""
if [ -n "${GITHUB_CODESPACE_BASENAME:-}" ]; then
  # Exemplo: basename = bug-free-garbanzo-r56pjjg79qwhx9vj
  # Queremos: https://bug-free-garbanzo-r56pjjg79qwhx9vj-8080.github.dev/
  CODESPACES_URL="https://${GITHUB_CODESPACE_BASENAME}-8080.github.dev"
fi

echo "📖 Acessando o H2 Console (Banco de Dados):"
echo ""

if [ -n "$CODESPACES_URL" ]; then
  echo "💻 Modo Codespaces (VS Code Web no GitHub) detectado."
  echo "   Use este endereço no navegador (já com porta 8080 embutida):"
  echo "   $CODESPACES_URL/h2-console/"
  echo ""
  echo "   Dica: se preferir, copie e cole este link direto no navegador."
  echo ""
else
  echo "🖥️ Modo 'raiz' (desktop / IDE local / terminal puro)."
  echo "   Use este endereço padrão no navegador local:"
  echo "   http://localhost:8080/h2-console/"
  echo ""
fi

echo "📌 Endereço base da API usado neste script:"
echo "   $BASE_URL"
echo ""
echo "Fim"
echo ""
echo "Autor: Andre Guilherme Barreto de Farias"
echo "Matrícula: 202111701842"
echo "TÉCNICO EM DESENVOLVIMENTO DE SISTEMAS [3010]/FLN - Técnico - Subsequente - Florianópolis"
echo ""
echo "================================================================="
echo ""
echo "╔════════════════════════════════════════════════════════════════╗"
echo "║    💡 Execute agora o script de testes: ./test-api.sh         ║"
echo "╚════════════════════════════════════════════════════════════════╝"
