## Java

- `ps -ef | grep java` - To list all java process

### JVM options

- `-Xms512M` - Minimum memory needed for Heap
- `-Xmx512M` - Maximum memory needed for Heap
- `-XX:NewRatio=2` - Sets the ratio between Young and Old Generation. A ratio of 2 means Young:Old is 1:2 (Young Gen is 33% of total heap).
- `-XX:SurvivorRatio=8` - Sets the ratio between Eden and a single Survivor space (e.g., 8 means Eden is 8x the size of S0/S1).
- `-XX:MaxTenuringThreshold=15` - Sets the maximum age before an object is promoted to the Old Generation.
- `-XX:+HeapDumpOnOutOfMemoryError` - Creates heap profile file when there is OOM crash.

## Docker

- `docker compose up` - Run docker compose file.
- `docker compose down` - Bring down all instances of docker.
- `docker compose down -v` - Remove the entire docker instances with its volume.

## Ubuntu

- `exec "$SHELL"` - Restart shell

## Powershell

- `$env:${ENV_NAME}` - Get environment variable
- `$env:${ENV_NAME} = "${ENV_VALUE}"` - Set environment variable
- `$env:PATH += ";C:\path"` - Add path to environment variable
- `Remove-Item $env:${ENV_NAME}` - Remove environment variable
- `New-Item -Path "C:\Path\To\NewFolder" -ItemType Directory` - Create new folder
- `New-Item -Path "C:\Path\To\NewFolder\file.txt" -ItemType File` - Create new file
- `ni -Path "C:\Path\To\NewFolder" -ItemType Directory` - Shorthand for creating new item
- `Rename-Item -Path "C:\Path\To\OldFolder" -NewName "NewFolder"` - Rename folder
- `ren -Path "C:\Path\To\OldFolder" -NewName "NewFolder"` - Rename folder shorthand
- `Move-Item -Path "C:\Path\To\file.txt" -Destination "C:\NewLocation\"` - Move file to new location
- `move -Path "C:\Path\To\file.txt" -Destination "C:\NewLocation\"` - Move file to new location shorthand
- `Copy-Item -Path "C:\Path\To\file.txt" -Destination "C:\NewLocation\"` - Copy file to new location
- `cp -Path "C:\Path\To\file.txt" -Destination "C:\NewLocation\"` - Copy file to new location shorthand
- `Remove-Item -Path "C:\Path\To\Folder" -Recurse -Force` - Remove folder and its contents
- `rm -Path "C:\Path\To\Folder" -Recurse -Force` - Remove folder and its contents shorthand

### Powershell Aliases

- `ls` - Alias for `Get-ChildItem`
- `cd` - Alias for `Set-Location`
- `pwd` - Alias for `Get-Location`
- `cat` - Alias for `Get-Content`
- `rm` - Alias for `Remove-Item`
- `cp` - Alias for `Copy-Item`
- `mv` - Alias for `Move-Item`
- `ni` - Alias for `New-Item`
- `ri` - Alias for `Remove-Item`
- `ren` - Alias for `Rename-Item`
- `echo` - Alias for `Write-Output`
- `clear` - Alias for `Clear-Host`
- `gcb` - Alias for `Get-Clipboard`
- `scb` - Alias for `Set-Clipboard`
- `-r` - Alias for `-Recurse`
- `-f` - Alias for `-Force`
- `-t` - Alias for `-Type` or `-ItemType`
- `-ea` - Alias for `-ErrorAction`
- `-eq` - Alias for `-eq`
- `-ne` - Alias for `-ne`
- `-lt` - Alias for `-lt`
- `-le` - Alias for `-le`
- `-gt` - Alias for `-gt`
- `-ge` - Alias for `-ge`