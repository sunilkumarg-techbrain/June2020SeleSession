ECHO ON

CALL color B9
CALL Powershell.exe -executionpolicy remotesigned -File  allure-results-folder-deletion.ps1

IF EXIST target\allure-reports\chrome\history (
                IF EXIST history\chrome\history (
                                RMDIR history\chrome\history /S /Q
                ) 
                  MOVE target\allure-reports\chrome\history history\chrome\history
                ) 

CALL mvn clean verify -P local -Dbrowser=CHROME -Dremote=false -Dmaven.clean.failOnError=false
              
IF EXIST history\chrome\history (
                IF EXIST target\allure-reports\chrome\history (
                                RMDIR target\allure-reports\chrome\history /S /Q
                ) 
                  MOVE history\chrome\history target\allure-reports\chrome\history
                ) 

CALL mvn allure:report -Dbrowser=CHROME
PAUSE