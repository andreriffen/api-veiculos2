#!/bin/bash

# ==============================================================================
# Script de Testes da API de Veículos
# ==============================================================================

BASE_URL="http://localhost:8080"
AUTH_USER="${AUTH_USER:-admin}"
AUTH_PASS="${AUTH_PASS:-admin123}"
AUTH_CREDENTIALS="$AUTH_USER:$AUTH_PASS"
RUN_ID=$(date +%s)
UNIQUE_SUFFIX="Teste-$RUN_ID"
PLATE_SUFFIX=$(printf "%02d" $((RUN_ID % 100)))
PLATE_SUFFIX_2=$(printf "%02d" $(((RUN_ID + 1) % 100)))
PLATE_SUFFIX_3=$(printf "%02d" $(((RUN_ID + 2) % 100)))
PLATE_SUFFIX_4=$(printf "%02d" $(((RUN_ID + 3) % 100)))
PLACA_CIVIC="TRK1A$PLATE_SUFFIX"
PLACA_ONIX="UVX2B$PLATE_SUFFIX_2"

auth_curl() {
  # Sim, dá para digitar curl -u "$AUTH_CREDENTIALS" na unha, mas esta função é o atalho oficial do clã dos preguiçosos.
  curl -s -u "$AUTH_CREDENTIALS" "$@"
}

pretty_print() {
  echo "$1" | jq '.'
}

extract_id() {
  echo "$1" | jq -r '.id'
}

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║           🔐 Camada de Segurança HTTP Basic Ativa             ║"
echo "╠════════════════════════════════════════════════════════════════╣"
echo "║   👤 Usuário........: $AUTH_USER"
echo "║   🔑 Senha..........: $AUTH_PASS"
echo "║   📡 Todas as requisições usam: curl -u $AUTH_CREDENTIALS      ║"
echo "║   🆔 Execução.......: $UNIQUE_SUFFIX"
echo "║   💡 Exporte AUTH_USER/AUTH_PASS para testar outras contas.    ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""
echo "🚀 Iniciando testes da API de Veículos com autenticação..."
echo ""

# ==============================================================================
# TESTES DE MARCAS
# ==============================================================================

echo "🏭 ========== MARCAS =========="
echo ""

echo "➤ 1. POST /marcas - Criando Honda"
RESP=$(auth_curl -X POST "$BASE_URL/marcas" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Honda $UNIQUE_SUFFIX\"
}")
pretty_print "$RESP"
MARCA_HONDA=$(extract_id "$RESP")
echo ""

echo "➤ 2. POST /marcas - Criando Chevrolet"
RESP=$(auth_curl -X POST "$BASE_URL/marcas" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Chevrolet $UNIQUE_SUFFIX\"
}")
pretty_print "$RESP"
MARCA_CHEVROLET=$(extract_id "$RESP")
echo ""

echo "➤ 3. GET /marcas - Listando todas as marcas"
auth_curl "$BASE_URL/marcas" | jq '.'
echo ""

echo "➤ 4. GET /marcas/{id} - Buscando marca por ID"
auth_curl "$BASE_URL/marcas/$MARCA_HONDA" | jq '.'
echo ""

echo "➤ 5. PUT /marcas/{id} - Atualizando marca"
auth_curl -X PUT "$BASE_URL/marcas/$MARCA_HONDA" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Honda Atualizada $UNIQUE_SUFFIX\"
}" | jq '.'
echo ""

# ==============================================================================
# TESTES DE CORES
# ==============================================================================

echo "🎨 ========== CORES =========="
echo ""

echo "➤ 6. POST /cores - Criando Azul"
RESP=$(auth_curl -X POST "$BASE_URL/cores" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Azul $UNIQUE_SUFFIX\"
}")
pretty_print "$RESP"
COR_AZUL=$(extract_id "$RESP")
echo ""

echo "➤ 7. POST /cores - Criando Verde"
RESP=$(auth_curl -X POST "$BASE_URL/cores" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Verde $UNIQUE_SUFFIX\"
}")
pretty_print "$RESP"
COR_VERDE=$(extract_id "$RESP")
echo ""

echo "➤ 8. GET /cores - Listando todas as cores"
auth_curl "$BASE_URL/cores" | jq '.'
echo ""

echo "➤ 9. GET /cores/{id} - Buscando cor por ID"
auth_curl "$BASE_URL/cores/$COR_AZUL" | jq '.'
echo ""

echo "➤ 10. PUT /cores/{id} - Atualizando cor"
auth_curl -X PUT "$BASE_URL/cores/$COR_AZUL" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Azul Noite $UNIQUE_SUFFIX\"
}" | jq '.'
echo ""

# ==============================================================================
# TESTES DE CLIENTES
# ==============================================================================

echo "👥 ========== CLIENTES =========="
echo ""

echo "➤ 11. POST /clientes - Criando Pedro Henrique"
RESP=$(auth_curl -X POST "$BASE_URL/clientes" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Pedro Henrique $UNIQUE_SUFFIX\",
  \"celular\": \"(48) 9${PLATE_SUFFIX}1-${PLATE_SUFFIX_2}00\",
  \"email\": \"pedro+$RUN_ID@example.com\",
  \"dataCadastro\": \"2025-12-01\"
}")
pretty_print "$RESP"
CLIENTE_PEDRO=$(extract_id "$RESP")
echo ""

echo "➤ 12. POST /clientes - Criando Ana Paula"
RESP=$(auth_curl -X POST "$BASE_URL/clientes" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Ana Paula $UNIQUE_SUFFIX\",
  \"celular\": \"(48) 9${PLATE_SUFFIX_2}2-${PLATE_SUFFIX_3}11\",
  \"email\": \"ana+$RUN_ID@example.com\",
  \"dataCadastro\": \"2025-12-01\"
}")
pretty_print "$RESP"
CLIENTE_ANA=$(extract_id "$RESP")
echo ""

echo "➤ 13. GET /clientes - Listando todos os clientes"
auth_curl "$BASE_URL/clientes" | jq '.'
echo ""

echo "➤ 14. GET /clientes/{id} - Buscando cliente por ID"
auth_curl "$BASE_URL/clientes/$CLIENTE_PEDRO" | jq '.'
echo ""

echo "➤ 15. PUT /clientes/{id} - Atualizando cliente"
auth_curl -X PUT "$BASE_URL/clientes/$CLIENTE_PEDRO" \
  -H 'Content-Type: application/json' \
  -d "{
  \"nome\": \"Pedro Henrique Atualizado $UNIQUE_SUFFIX\",
  \"celular\": \"(48) 90000-0000\",
  \"email\": \"pedro.att+$RUN_ID@example.com\",
  \"dataCadastro\": \"2025-12-01\"
}" | jq '.'
echo ""

# ==============================================================================
# TESTES DE MODELOS
# ==============================================================================

echo "🚙 ========== MODELOS =========="
echo ""

echo "➤ 16. POST /modelos - Criando Civic (Honda)"
RESP=$(auth_curl -X POST "$BASE_URL/modelos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"descricao\": \"Civic $UNIQUE_SUFFIX\",
  \"marcaId\": $MARCA_HONDA
}")
pretty_print "$RESP"
MODELO_CIVIC=$(extract_id "$RESP")
echo ""

echo "➤ 17. POST /modelos - Criando Onix (Chevrolet)"
RESP=$(auth_curl -X POST "$BASE_URL/modelos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"descricao\": \"Onix $UNIQUE_SUFFIX\",
  \"marcaId\": $MARCA_CHEVROLET
}")
pretty_print "$RESP"
MODELO_ONIX=$(extract_id "$RESP")
echo ""

echo "➤ 18. GET /modelos - Listando todos os modelos"
auth_curl "$BASE_URL/modelos" | jq '.'
echo ""

echo "➤ 19. GET /modelos/{id} - Buscando modelo por ID"
auth_curl "$BASE_URL/modelos/$MODELO_CIVIC" | jq '.'
echo ""

echo "➤ 20. PUT /modelos/{id} - Atualizando modelo"
auth_curl -X PUT "$BASE_URL/modelos/$MODELO_CIVIC" \
  -H 'Content-Type: application/json' \
  -d "{
  \"descricao\": \"Civic Touring $UNIQUE_SUFFIX\",
  \"marcaId\": $MARCA_HONDA
}" | jq '.'
echo ""

# ==============================================================================
# TESTES DE VEÍCULOS
# ==============================================================================

echo "🚗 ========== VEÍCULOS =========="
echo ""

echo "➤ 21. POST /veiculos - Criando $PLACA_CIVIC (Civic Azul)"
RESP=$(auth_curl -X POST "$BASE_URL/veiculos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"placa\": \"$PLACA_CIVIC\",
  \"observacoes\": \"Veículo importado\",
  \"motor\": {
    \"potencia\": 150,
    \"tipoCombustivel\": \"GASOLINA\"
  },
  \"corId\": $COR_AZUL,
  \"modeloId\": $MODELO_CIVIC,
  \"proprietarioId\": $CLIENTE_PEDRO
}")
pretty_print "$RESP"
VEICULO_CIVIC=$(extract_id "$RESP")
echo ""

echo "➤ 22. POST /veiculos - Criando $PLACA_ONIX (Onix Verde)"
RESP=$(auth_curl -X POST "$BASE_URL/veiculos" \
  -H 'Content-Type: application/json' \
  -d "{
  \"placa\": \"$PLACA_ONIX\",
  \"observacoes\": \"Carro zero km\",
  \"motor\": {
    \"potencia\": 116,
    \"tipoCombustivel\": \"FLEX\"
  },
  \"corId\": $COR_VERDE,
  \"modeloId\": $MODELO_ONIX,
  \"proprietarioId\": $CLIENTE_ANA
}")
pretty_print "$RESP"
VEICULO_ONIX=$(extract_id "$RESP")
echo ""

echo "➤ 23. GET /veiculos - Listando todos os veículos"
auth_curl "$BASE_URL/veiculos" | jq '.'
echo ""

echo "➤ 24. GET /veiculos/{id} - Buscando veículo por ID"
auth_curl "$BASE_URL/veiculos/$VEICULO_CIVIC" | jq '.'
echo ""

echo "➤ 25. PUT /veiculos/{id} - Atualizando observações do veículo"
auth_curl -X PUT "$BASE_URL/veiculos/$VEICULO_CIVIC" \
  -H 'Content-Type: application/json' \
  -d "{
  \"placa\": \"$PLACA_CIVIC\",
  \"observacoes\": \"Veículo com manutenção recente\",
  \"motor\": {
    \"potencia\": 150,
    \"tipoCombustivel\": \"GASOLINA\"
  },
  \"corId\": $COR_AZUL,
  \"modeloId\": $MODELO_CIVIC,
  \"proprietarioId\": $CLIENTE_PEDRO
}" | jq '.'
echo ""

# ==============================================================================
# TESTES DE VALIDAÇÃO E ERROS
# ==============================================================================

echo "⚠️  ========== TESTES DE VALIDAÇÃO =========="
echo ""

echo "➤ 26. GET /marcas/999 - Buscando marca inexistente (deve retornar 404)"
auth_curl "$BASE_URL/marcas/999" | jq '.'
echo ""

# ==============================================================================
# TESTES DE DELEÇÃO
# ==============================================================================

echo "🗑️  ========== TESTES DE DELEÇÃO =========="
echo ""

echo "➤ 27. DELETE /veiculos/{id} - Deletando veículo Onix"
auth_curl -X DELETE "$BASE_URL/veiculos/$VEICULO_ONIX" -w "\nHTTP Status: %{http_code}\n"
echo ""

echo "➤ 28. GET /veiculos - Verificando que o veículo foi deletado"
auth_curl "$BASE_URL/veiculos" | jq '.'
echo ""

echo "✅ Testes concluídos!"
