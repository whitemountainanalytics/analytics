<?php

// DB Connect params
$servername = "10.0.2.113";
$username = "web_user";
$password = "web_user";
$database = "webappdatabase";

function setSessionAndCookies() {

session_start();
// echo session_id();
$sessionID = session_id();

header("x_sessionid: $sessionID");

if (empty ($_COOKIE["var_cookie"])) {
$cookieID = uniqid('', true);
setcookie("var_cookie", $cookieID, time()+(3600*24*10));
} else
{
$cookieID = $_COOKIE["var_cookie"];
setcookie("var_cookie", $cookieID, time()+(3600*24*10));
}

header("x_cookieid: $cookieID");

} 

?>
