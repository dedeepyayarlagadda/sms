# function will stop postgres server.
function Stop-PostgresServer {
    . "$env:PG_HOME\bin\pg_ctl" stop 
}

Stop-PostgresServer
