#!/usr/bin/env bash
cd /home/ec2-user/server
secret=$(aws ssm get-parameters --region us-east-1 --names PSQLSECRET --with-decryption --query Parameters[0].Value)
secret=`echo $secret | sed -e 's/^"//' -e 's/"$//'`
user=$(aws ssm get-parameters --region us-east-1 --names PSQLUSER --with-decryption --query Parameters[0].Value)
user=`echo $user | sed -e 's/^"//' -e 's/"$//'`
host=$(aws ssm get-parameters --region us-east-1 --names PSQLHOST --with-decryption --query Parameters[0].Value)
host=`echo $host | sed -e 's/^"//' -e 's/"$//'`
port=$(aws ssm get-parameters --region us-east-1 --names PSQLPORT --with-decryption --query Parameters[0].Value)
port=`echo $port | sed -e 's/^"//' -e 's/"$//'`
sudo java -jar -DSQL_DB_PASSWORD=$secret -DSQL_DB_USERNAME=$user -DSQL_DB_HOST=$host -DSQL_DB_PORT=$port \
    *.jar > /dev/null 2> /dev/null < /dev/null &