#!/bin/bash

LOG_DIR="./logs"
mkdir -p "$LOG_DIR"

# File with PID
PID_FILE="/tmp/monitoring.pid"

monitoring() {
    timestamp=$(date +"%Y%m%d_%H%M%S")
    log_file="$LOG_DIR/monitoring_${timestamp}.csv"

    echo "Timestamp,Disk_Used,Disk_Available,Free_Inodes" > "$log_file"

    while true; do
        # Check for necessity to create a new file
        current_day=$(date +"%Y%m%d")
        log_day=$(date -r "$log_file" +"%Y%m%d")

        if [[ "$current_day" != "$log_day" ]]; then
            new_timestamp=$(date +"%Y%m%d_%H%M%S")
            log_file="$LOG_DIR/monitoring_${new_timestamp}.csv"
            echo "Timestamp,Disk_Used,Disk_Available,Free_Inodes" > "$log_file"
        fi

        # Collecting data
        current_time=$(date +"%Y-%m-%d %H:%M:%S")
        disk_used=$(df / | awk 'NR==2 {print $3}')
        disk_available=$(df / | awk 'NR==2 {print $4}')
        free_inodes=$(df -i / | awk 'NR==2 {print $4}')

        # Write to CSV
        echo "$current_time,$disk_used,$disk_available,$free_inodes" >> "$log_file"

        sleep 60
    done
}

start() {
    if [[ -f "$PID_FILE" ]]; then
        echo "Process is already running with PID $(cat "$PID_FILE")"
        exit 1
    fi

    monitoring &
    echo $$ > "$PID_FILE"
    echo "Monitoring started with PID $BASHPID"
}

stop() {
    if [[ ! -f "$PID_FILE" ]]; then
        echo "No process is currently running"
        exit 1
    fi

    kill -9 "-$(cat "$PID_FILE")"
    rm -f "$PID_FILE"
    echo "Monitoring stopped"
}

status() {
    if [[ -f "$PID_FILE" ]]; then
        echo "Monitoring is running in group with PGID $(cat "$PID_FILE")"
    else
        echo "Monitoring is not running"
    fi
}

case "$1" in
    START)
        start
        ;;
    STOP)
        stop
        ;;
    STATUS)
        status
        ;;
    *)
        echo "Please, use: $0 {START|STOP|STATUS}"
        exit 1
        ;;
esac