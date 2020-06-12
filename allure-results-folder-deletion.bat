ECHO ON
CALL color B9
CALL Powershell.exe -executionpolicy remotesigned -File  allure-results-folder-deletion.ps1
PAUSE
