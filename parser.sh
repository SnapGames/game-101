#!/usr/bin/env bash
declare input_file=$1
declare output_file=$2

source <(grep -v '^ *#' ./project.properties | grep '[^ ] *=' | awk '{split($0,a,"="); print toupper(gensub(/\./, "_", "g", a[1])) "=" a[2]}')
eval "echo \"$(< $input_file)\"" > $output_file
