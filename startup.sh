#!/bin/bash

##############################################################################
# 🚀 SCRIPT DE INICIALIZAÇÃO - API DE VEÍCULOS
# Autor: Andre Guilherme Barreto de Farias - IFSC
##############################################################################

clear

# Por padrão o servidor sobe quietinho; exporte QUIET_LOGS=0 para ver o tail ao vivo.
QUIET_LOGS="${QUIET_LOGS:-1}"

echo "╔═══════════════════════════════════════════════════════════════════╗"
echo "║     🔴🔵  API DE VEÍCULOS - SISTEMA DE LAVAÇÃO  🔵🔴              ║"
echo "╚═══════════════════════════════════════════════════════════════════╝"
echo ""

# Verificar Java disponível e configurar JAVA_HOME automaticamente
if ! command -v java >/dev/null 2>&1; then
    echo "❌ Nenhuma instalação Java encontrada no PATH."
    echo "   Dica: Rebuild do Codespace (Ctrl+Shift+P → Codespaces: Rebuild Container)."
    exit 1
fi

if [ -z "$JAVA_HOME" ] || [ ! -x "$JAVA_HOME/bin/java" ]; then
    JAVA_BIN_PATH=$(command -v java)
    JAVA_REAL_PATH=$(readlink -f "$JAVA_BIN_PATH")
    JAVA_HOME_DETECTED=$(dirname "${JAVA_REAL_PATH}")
    JAVA_HOME_DETECTED=$(dirname "${JAVA_HOME_DETECTED}")

    if [ -x "$JAVA_HOME_DETECTED/bin/java" ]; then
        export JAVA_HOME="$JAVA_HOME_DETECTED"
        echo "ℹ️  JAVA_HOME ajustado para: $JAVA_HOME"
    else
        echo "❌ JAVA_HOME não pôde ser determinado automaticamente."
        echo "   Dica: Rebuild do Codespace (Ctrl+Shift+P → Codespaces: Rebuild Container)."
        exit 1
    fi
fi

echo "✅ Java detectado em: $(command -v java)"

# Verificar se precisa compilar
if [ ! -d "target" ] || [ ! -f "target/classes/com/example/api_veiculos2/ApiVeiculos2Application.class" ]; then
    echo "⚙️  Compilando projeto..."
    ./mvnw clean compile
    [ $? -eq 0 ] && echo "✅ Compilação OK" || { echo "❌ Erro na compilação"; exit 1; }
fi

# Verificar porta 8080
if command -v lsof >/dev/null 2>&1 && lsof -Pi :8080 -sTCP:LISTEN -t >/dev/null 2>&1; then
    echo "⚠️  Matando processo na porta 8080..."
    kill -9 $(lsof -t -i:8080) 2>/dev/null
    sleep 2
fi

# Iniciar aplicação
echo ""
echo "🚀 Iniciando Spring Boot..."
echo ""

LOG_FILE="/tmp/api-veiculos-startup.log"
./mvnw spring-boot:run > "$LOG_FILE" 2>&1 &
SPRING_PID=$!

# Aguardar inicialização
echo "⏳ Aguardando aplicação..."
TIMEOUT=60
ELAPSED=0

while [ $ELAPSED -lt $TIMEOUT ]; do
    if grep -q "Started ApiVeiculos2Application" "$LOG_FILE" 2>/dev/null; then
        echo ""
        echo "✅ API INICIADA COM SUCESSO!"
        echo ""
        echo "📊 INFORMAÇÕES:"
        echo "   🌐 API: http://localhost:8080"
        echo "   🗄️  H2 Console: http://localhost:8080/h2-console"
        echo "   📝 JDBC: jdbc:h2:mem:apiveiculos2 (user: sa, pass: vazio)"
        echo "   🔧 PID: $SPRING_PID"
        echo ""
        echo "🎮 COMANDOS:"
        echo "   ./populate-data.sh  - Popular dados"
        echo "   ./test-api.sh       - Testar API"
        echo "   kill $SPRING_PID    - Parar servidor"
        echo ""
        if [ "$QUIET_LOGS" = "1" ]; then
            echo "🪵 Logs salvos em: $LOG_FILE"
            echo "   (export QUIET_LOGS=0 ./startup.sh  # para assistir ao vivo)"
            wait $SPRING_PID
        else
            echo "🪵 Logs ao vivo (Ctrl+C para parar de acompanhar, servidor continua rodando):"
            echo "   export QUIET_LOGS=1 ./startup.sh  # para iniciar sem tail seguido"
            echo ""
            tail -f "$LOG_FILE" \
                | grep --line-buffered -Ev 'Autocommit mode|Isolation level|Minimum pool size|Maximum pool size' \
                | sed -u \
                    -e 's/ INFO / [INFO] /g' \
                    -e 's/ WARN / [WARN] /g' \
                    -e 's/ ERROR / [ERROR] /g'
        fi
        exit 0
    fi
    
    if ! kill -0 $SPRING_PID 2>/dev/null; then
        echo ""
        echo "❌ Erro ao iniciar!"
        tail -20 "$LOG_FILE"
        exit 1
    fi
    
    echo -n "."
    sleep 2
    ELAPSED=$((ELAPSED + 2))
done

echo ""
echo "❌ Timeout!"
kill $SPRING_PID 2>/dev/null
exit 1
