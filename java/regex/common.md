# tomcat log regex

    ^(?<time>\d{4}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}:\d{1,2},\d{1,3}) \[(?<thread>[\w-]+)\] (?<level>(INFO|ERROR|WARN|DEBUG))(?<className>.*) - (?<message>.*)