@echo off
echo ========================================
echo    INICIANDO SPRING BOOT
echo ========================================
echo.
echo La aplicacion se esta iniciando...
echo NO CIERRES ESTA VENTANA
echo.
echo Una vez que veas "Started DemoApplication"
echo ya puedes usar el HTML o hacer pruebas.
echo.
echo ========================================
echo.

cd /d "%~dp0"
call mvnw.cmd spring-boot:run

pause
