#!/usr/bin/env bash
cd /home/ec2-user/server
sudo java -jar -DSQL_DB_PORT="5432" -DSQL_DB_USERNAME="postgres" -DSQL_DB_PASSWORD="rootroot" -DSQL_DB_HOST="jdbc:postgresql://postgres-1.cem8pqnfuddw.us-east-1.rds.amazonaws.com" \
    *.jar > /dev/null 2> /dev/null < /dev/null &