# Initilaizing the data folder with user postgres
function Init-DB {
    . $("$env:PG_HOME\bin\pg_ctl") initdb -o "-U postgres --auth-local=md5 -E UTF8 --no-locale"
}

# Setup-Db will create database objects.
function Setup-DB {
    # Creating the user,roles,tablespace and database using setupdb.sql
    . "$env:PG_HOME\bin\psql" -U postgres -b -f $("$env:SMS_HOME\db\dbscripts\setupdb.sql") -v sms_db_user=$env:SMS_DB_USER -v sms_db_pwd=$env:SMS_DB_PWD -v sms_tablespace_name=$env:SMS_TABLESPACE_NAME -v sms_tablespace_dir=$($(Resolve-Path $env:SMS_TABLESPACE_DIR).Path) -v sms_db_name=$env:SMS_DB_NAME
    
    # Creating the table SMS tables and indexes.
    . "$env:PG_HOME\bin\psql" -U $env:SMS_DB_USER -d $env:SMS_DB_NAME -b -f $("$env:SMS_HOME\db\dbscripts\smscreateschema.sql") -v sms_tablespace_name=$env:SMS_TABLESPACE_NAME

    Write-Host "Database objects are successfully created."
}

function Update-ServerConfig {
    param(
        $ServerConfigInfo,
        $ServerConfigFile
    )

    (Get-Content -Path  $ServerConfigFile) |
    ForEach-Object {
    
        if($_.contains('=')){
            if($_.Trim().StartsWith('#')) {
                $lineToBeChange = $_.Trim().Substring(1)
            }
        
            $lineList = $lineToBeChange.split("=")
            $lineList[0] = $lineList[0].Trim()
        
            if ($serverConfigInfo.ContainsKey($lineList[0]))
            {
                if($lineList[1].Contains("#")){
                    $valueToBeChange = $($lineList[1].SubString(0, $lineList[1].IndexOf('#'))).Trim()
                }
                $lineToBeChange -replace $valueToBeChange, $serverConfigInfo.Item($lineList[0])
            }
            else
            {
                $_
            }        
        } else {
            $_
        }
    } |
    Set-Content $ServerConfigFile
}

function Main {
    if((-not ([string]::IsNullOrEmpty($env:PGDATA))) -and (Test-Path $env:PGDATA)) {
        # Checking the directory is empty . If it is empty then we are initializing the db.
        $directoryInfo = Get-ChildItem $env:PGDATA | Measure-Object
        if($directoryInfo.Count -eq 0){
            Init-DB
        }
    }

    # calling startpostgres.ps1 to start the server
    . "$PWD\startpostgres.ps1"

    Setup-DB

    # stopping postgres server
    . "$PWD\stoppostgres.ps1"

    # serverConfigInfo is a hastable. 
    # key => key
    # value => value
    # value will be replaced if key is already present in the postgressql.conf file else it will be added.
    $serverConfigInfo = New-Object 'system.collections.generic.dictionary[String, String]'

    $serverConfigInfo.Add("log_destination", "'stderr'")
    $serverConfigInfo.Add("logging_collector","'on'")
    $serverConfigInfo.Add("log_statement", "'all'")

    # Enabling and updating the log related configuration to postgresql.conf
    Update-ServerConfig -ServerConfigInfo $serverConfigInfo -ServerConfigFile "$env:PGDATA\postgresql.conf"
}

# script startup
Main
