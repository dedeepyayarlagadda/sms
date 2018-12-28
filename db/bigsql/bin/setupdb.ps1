# Initilaizing the data folder with user postgres
function init-DB {
    param($pgHome, $dataFolder)
    if(-not (Test-path "$datafodler\logs")){
       New-Item -Path "$datafodler\logs" -ItemType "directory"
    }
    . $("$pgHome\initdb") -D $dataFolder -U postgres --auth-local=md5 -E UTF8 --no-locale
}

# function will return true if the port is avilable else false.
function Check-ServerStatus {
    param($port, $ipaddress = "localhost")

    $status = $false
    
    $counter = 0;
    while($true){
        
        try {
            $connection = New-Object System.Net.Sockets.TcpClient($ipaddress,$port)

            if($connection.Connected) {
                $status = $true
				Write-Host ((Get-Date).toString("HH:MM:ss.ms"),"postgres server is started successfully on this port: $port")
            }
        }catch{
            $status = $false
        }
 
        if(($status -eq $true) -or ($counter -eq 20)){
            break   
        }

        $counter += 1
    }
    
    return $status
}


function Start-PostgreServer {
    param($pgHome, $dataFolder)
    
    if(-not (Test-Path $dataFolder)) {
        init-DB -pgHome $pgHome -dataFolder $dataFolder
    }

    $processName = 'postgres'
    $service = Get-Process -Name $processName -ErrorAction SilentlyContinue

    if([string]::IsNullOrEmpty($service.Name)) {
       # Starting the postgre server.
       . $("$pgHome\pg_ctl") -D $dataFolder start
    } else {
         # If postgre server is already running we are stopping it and atrating again.
         Stop-Process -Name $processName -ErrorAction SilentlyContinue
         $service = Get-Process -Name $processName -ErrorAction SilentlyContinue
         Start-PostgreServer -pgHome $pgHome -dataFolder $dataFolder
    }
}

# Setup-Db will create database objects.
function Setup-DB {
    param($pgHome, $dataFolder)
    
    # Creating the user, tablespace, db, schema.
    . "$pgHome\psql" -U postgres -f "$pgHome\..\..\..\conf\setupdb.txt"
    
    # Creating the tables.
    . "$pgHome\psql" -U smsuser1 -d smsdb -f "$pgHome\..\..\..\dbscripts\smsschema.sql"
}

$dataFolder = "$PWD\..\bigsql\data"
$pgHome = "$PWD\..\bigsql\pg11\bin"

Start-PostgreServer -pgHome $pgHome -dataFolder $dataFolder

if($(Check-ServerStatus -port 5432)) {
    Setup-DB -pgHome $pgHome -dataFolder $dataFolder
} else {
    Write-Host ((Get-Date).toString("HH:MM:ss.ms"),"postgres failed to start. Something went wrong")
}