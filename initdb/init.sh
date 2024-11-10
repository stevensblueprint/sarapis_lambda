#!/bin/bash

# Create tables
for f in /tmp/dynamo/*.json; do
  echo "Creating table for schema file: $f"
  aws dynamodb create-table --endpoint-url http://dynamodb-local:8000 --cli-input-json file://"$f"
done

# Insert data into organization
for d in /tmp/initdb/*.json; do
  echo "Inserting data into organization: $d"
  aws dynamodb put-item --table-name organizations --item file://"$d" --endpoint-url http://dynamodb-local:8000
done

echo "DynamoDB initialization complete."
