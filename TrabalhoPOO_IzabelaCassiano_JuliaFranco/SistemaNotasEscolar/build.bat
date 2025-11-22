@echo off
echo ===================================
echo    SISTEMA DE NOTAS ESCOLAR (TP4)
echo ===================================
echo.
echo [1/3] Limpando diretorio target...
if exist target rmdir /s /q target
mkdir target\classes

echo [2/3] Compilando codigo fonte (TP4 e subpacotes)...
:: O comando abaixo pega todos os arquivos .java dentro de tp4 e subpastas recursivamente
dir /s /B "src\main\java\org\teiacoltec\poo\tp4\*.java" > sources.txt
javac -d target\classes -cp src\main\java @sources.txt
del sources.txt

if %errorlevel% neq 0 (
    echo.
    echo ERRO: Falha na compilacao! Verifique os erros acima.
    pause
    exit /b 1
)

echo [3/3] Compilacao concluida com sucesso!
echo.
echo Para executar o sistema, use run.bat
echo.
pause