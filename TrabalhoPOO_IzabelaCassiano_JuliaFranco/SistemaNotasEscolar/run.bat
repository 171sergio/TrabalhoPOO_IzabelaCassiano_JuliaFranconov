@echo off
echo ===================================
echo    SISTEMA DE NOTAS ESCOLAR
echo ===================================
echo.

:: Verifica se a pasta lib existe
if not exist "lib" (
    echo [ERRO] A pasta 'lib' nao existe! Crie a pasta e coloque o driver .jar nela.
    pause
    exit /b 1
)

:: Verifica se o projeto foi compilado
if not exist target\classes (
    echo Codigo nao compilado. Executando build...
    call build.bat
)

echo Iniciando o sistema...
echo.

:: Executa o Main incluindo o JAR do SQLite e as classes compiladas
:: O comando "lib\*" carrega qualquer .jar dentro da pasta lib
java -cp "target\classes;lib\*" org.teiacoltec.poo.tp4.Main

echo.
echo Sistema encerrado.
pause
