@echo off
echo ===================================
echo    SISTEMA DE NOTAS ESCOLAR
echo ===================================
echo.

echo [1/3] Limpando diretorio target...
if exist target rmdir /s /q target
mkdir target\classes

echo [2/3] Compilando codigo fonte...
javac -d target\classes -cp src\main\java src\main\java\org\teiacoltec\poo\tp3\*.java

if %errorlevel% neq 0 (
    echo ERRO: Falha na compilacao!
    pause
    exit /b 1
)

echo [3/3] Compilacao concluida com sucesso!
echo.
echo Para executar o sistema, use:
echo java -cp target\classes org.teiacoltec.poo.tp3.Main
echo.
echo Ou execute run.bat
echo.
pause