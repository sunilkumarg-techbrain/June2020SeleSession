$currentLocation = (Get-Location).Path

$screenshotsFolderLocation1 = $currentLocation+ '\Screenshots'
$allureResultsFolderLocation1 = $currentLocation+ '\allure-results'

if ((Test-Path $screenshotsFolderLocation1)) {
Remove-Item -Path $screenshotsFolderLocation1 -Force -Recurse -ErrorAction SilentlyContinue
}

if ((Test-Path $allureResultsFolderLocation1)) {
Remove-Item -Path $allureResultsFolderLocation1 -Force -Recurse -ErrorAction SilentlyContinue
}

