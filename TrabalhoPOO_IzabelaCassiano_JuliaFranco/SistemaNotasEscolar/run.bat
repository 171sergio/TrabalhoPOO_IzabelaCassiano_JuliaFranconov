@echo off
echo ===================================
echo    SISTEMA DE NOTAS ESCOLAR
echo ===================================
echo.

if not exist target\classes (
    echo Codigo nao compilado. Executando build...
    call build.bat
    if %errorlevel% neq 0 (
        echo ERRO: Falha no build!
        pause
        exit /b 1
    )
)

echo Iniciando o sistema (TP4)...
echo.
:: Alterado de tp3 para tp4
java -cp target\classes;lib/* org.teiacoltec.poo.tp4.Main

echo.
echo Sistema encerrado.
pause