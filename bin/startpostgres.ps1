# function will return true if the port is used else false.
function Check-ServerStatus {
    param(
        $Port,
        $IPAddress = "localhost"
    )

    $status = $false    
    $counter = 0

    while($true){
        
        try {
            $connection = New-Object System.Net.Sockets.TcpClient($IPAddress,$Port)

            if($connection.Connected) {
                $status = $true
				Write-Host ((Get-Date).toString("hh:mm:ss.ms"),"postgres server is started successfully on this port: $Port")
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

function Start-PostgresServer {
    $processName = 'postgres'
    $service = Get-Process -Name $processName -ErrorAction SilentlyContinue

    if([string]::IsNullOrEmpty($service.Name)) {
        # Starting the postgres server.
        . "$env:PG_HOME\bin\pg_ctl" start
    } else {
         # If postgres server is already running we are stopping it and starting it again.
         Stop-Process -Name $processName -ErrorAction SilentlyContinue
         $service = Get-Process -Name $processName -ErrorAction SilentlyContinue
         Start-PostgresServer -pgHome $env:PG_HOME\bin -dataFolder $env:PGDATA
    }
}

function Main {
    # starting postgres server.
    Start-PostgresServer
    
    if(-not $(Check-ServerStatus -Port 5432)) {
        Write-Error ((Get-Date).toString("hh:mm:ss.ms"),"Postgres failed to start. Something went wrong.")
        exit
    }

}

# script startup
Main
