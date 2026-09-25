@echo off
setlocal EnableDelayedExpansion

REM ============================================================
REM Arguments
REM   %1 = Java source file
REM   %2 = project root
REM ============================================================

set "SOURCE=%~1"
set "PROJECT=%~2"

if not defined SOURCE (
    echo ERROR: No Java source file supplied.
    exit /b 1
)

if not defined PROJECT (
    echo ERROR: No project directory supplied.
    exit /b 1
)

set "OUTPUT=%PROJECT%\target\classes"

echo.
echo ========================================
echo Java Runner
echo ========================================
echo Source : %SOURCE%
echo Project: %PROJECT%
echo Output : %OUTPUT%
echo.

REM ============================================================
REM Compile
REM ============================================================

echo Compiling...
echo.

if not exist "%OUTPUT%" mkdir "%OUTPUT%"

javac -d "%OUTPUT%" "%SOURCE%"

if errorlevel 1 (
    echo.
    echo ========================================
    echo COMPILATION FAILED
    echo ========================================
    exit /b 1
)

REM ============================================================
REM Get package from Java source
REM ============================================================

set "PACKAGE="

for /f "tokens=2" %%A in ('findstr /b /c:"package " "%SOURCE%"') do (
    set "PACKAGE=%%A"
)

if defined PACKAGE (
    set "PACKAGE=!PACKAGE:;=!"
)

REM ============================================================
REM Get class name from filename
REM ============================================================

for %%F in ("%SOURCE%") do (
    set "CLASS=%%~nF"
)

REM ============================================================
REM Build fully qualified class name
REM ============================================================

if defined PACKAGE (
    set "MAINCLASS=!PACKAGE!.!CLASS!"
) else (
    set "MAINCLASS=!CLASS!"
)

echo.
echo ========================================
echo Running
echo ========================================
echo Class: !MAINCLASS!
echo.

java -cp "%OUTPUT%" "!MAINCLASS!"

set "EXITCODE=!ERRORLEVEL!"

echo.
echo ========================================
echo Process exited with code !EXITCODE!
echo ========================================

exit /b !EXITCODE!