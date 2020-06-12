ECHO ON

CALL color B9
CALL Powershell.exe -executionpolicy remotesigned -File  allure-results-folder-deletion.ps1

IF EXIST target\allure-reports\CHROME\history (
                IF EXIST history\CHROME\history (
                                RMDIR history\CHROME\history /S /Q
                ) 
                  MOVE target\allure-reports\CHROME\history history\CHROME\history
                ) 

CALL mvn clean verify -P grid -Dbrowser=CHROME -Dremote=true -Dmaven.clean.failOnError=false
              
IF EXIST history\CHROME\history (
                IF EXIST target\allure-reports\CHROME\history (
                                RMDIR target\allure-reports\CHROME\history /S /Q
                ) 
                  MOVE history\CHROME\history target\allure-reports\CHROME\history
                ) 

CALL mvn allure:report -Dbrowser=CHROME
PAUSE