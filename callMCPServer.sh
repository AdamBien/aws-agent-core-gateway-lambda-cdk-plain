#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ]; then
    echo "Usage: $0 <gateway-url> <jwt-token> [payload-file]"
    exit 1
fi

GATEWAY_URL=$1
TOKEN=$2
PAYLOAD_FILE=$3

if [ -n "$PAYLOAD_FILE" ] && [ -f "$PAYLOAD_FILE" ]; then
    curl -X POST "$GATEWAY_URL" \
        -H "Authorization: Bearer $TOKEN" \
        -H "Content-Type: application/json" \
        -d @"$PAYLOAD_FILE" \
        -w "\n\nHTTP Status: %{http_code}\n"
else
    curl -X POST "$GATEWAY_URL" \
        -H "Authorization: Bearer $TOKEN" \
        -H "Content-Type: application/json" \
        -d '{
  "jsonrpc": "2.0",
  "id": 1,
  "method": "initialize",
  "params": {
    "protocolVersion": "2025-06-18",
    "capabilities": {},
    "clientInfo": {
      "name": "test-client",
      "version": "1.0.0"
    }
  }
}' \
        -w "\n\nHTTP Status: %{http_code}\n"
fi
