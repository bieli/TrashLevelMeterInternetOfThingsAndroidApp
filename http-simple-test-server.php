<?php
$url = $_SERVER["REQUEST_URI"];

$pu = parse_url($url);

//var_dump($_GET, parse_url($url));
error_log(var_export($pu, true) . "\n", 3, "/tmp/simple-http-server.log");

if (!empty($pu['path']) && $pu['path'] == '/net.bieli.trashlevel/trash/home') {
    echo rand(1, 5);
} else {
    echo "";
}

