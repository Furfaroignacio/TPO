# Script de prueba para la API de Grafos
# Asegúrate de que la aplicación esté corriendo en http://localhost:8080

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "🧪 PRUEBAS DE LA API DE GRAFOS" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

$baseUrl = "http://localhost:8080/graph"

# Función auxiliar para hacer requests
function Test-Endpoint {
    param($Method, $Url, $Description)
    Write-Host "📍 $Description" -ForegroundColor Yellow
    Write-Host "   $Method $Url" -ForegroundColor Gray
    try {
        $response = Invoke-RestMethod -Uri $Url -Method $Method -ErrorAction Stop
        Write-Host "   ✅ Respuesta:" -ForegroundColor Green
        $response | ConvertTo-Json -Depth 5 | Write-Host
    } catch {
        Write-Host "   ❌ Error: $($_.Exception.Message)" -ForegroundColor Red
    }
    Write-Host ""
}

# 1. Cargar datos iniciales
Write-Host "1️⃣  CARGANDO DATOS INICIALES" -ForegroundColor Magenta
Test-Endpoint -Method POST -Url "$baseUrl/seed/basic" -Description "Cargar ciudades y rutas en Neo4j"

# Esperar un momento para que se carguen los datos
Start-Sleep -Seconds 2

# 2. Listar todas las ciudades
Write-Host "2️⃣  LISTANDO TODAS LAS CIUDADES" -ForegroundColor Magenta
Test-Endpoint -Method GET -Url "$baseUrl/cities" -Description "Obtener lista de ciudades"

# 3. Búsqueda BFS desde CABA
Write-Host "3️⃣  BÚSQUEDA EN AMPLITUD (BFS)" -ForegroundColor Magenta
Test-Endpoint -Method GET -Url "$baseUrl/bfs?start=CABA" -Description "BFS desde CABA"
Test-Endpoint -Method GET -Url "$baseUrl/bfs?start=Rosario" -Description "BFS desde Rosario"

# 4. Búsqueda DFS desde CABA
Write-Host "4️⃣  BÚSQUEDA EN PROFUNDIDAD (DFS)" -ForegroundColor Magenta
Test-Endpoint -Method GET -Url "$baseUrl/dfs?start=CABA" -Description "DFS desde CABA"
Test-Endpoint -Method GET -Url "$baseUrl/dfs?start=Mendoza" -Description "DFS desde Mendoza"

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "✅ PRUEBAS COMPLETADAS" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan
