#!/usr/bin/env bash

echo $1
if [[ -d "${1}" ]]; then
    echo "ok";
else
    echo "fail";
fi