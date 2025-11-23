@echo off
echo ===================================
echo    SISTEMA DE NOTAS ESCOLAR (FIX)
echo ===================================
echo.

echo [1/3] Limpando versao anterior...
if exist target rmdir /s /q target
mkdir target\classes

echo [2/3] Compilando TUDO (incluindo subpastas)...
:: O comando abaixo lista todos os arquivos .java recursivamente e salva em sources.txt
dir /s /B src\main\java\org\teiacoltec\poo\tp4\*.java > sources.txt

:: Compila usando a lista gerada
javac -d target\classes -cp src\main\java @sources.txt

:: Limpa o arquivo temporário
del sources.txt

echo [2.5/3] Compilando testes...
dir /s /B src\test\java\org\teiacoltec\poo\tp4\*.java > testsources.txt
javac -d target\test-classes -cp target\classes;src\test\java @testsources.txt
del testsources.txt

if %errorlevel% neq 0 (
    echo.
    echo ERRO CRITICO: A compilacao falhou.
    echo Verifique as mensagens acima.
    pause
    exit /b 1
)

echo.
echo [3/3] SUCESSO! Tudo compilado.
echo Agora execute o run.bat
echo.
pause
