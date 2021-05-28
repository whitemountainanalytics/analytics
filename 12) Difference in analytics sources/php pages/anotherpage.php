<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Another Page</title>

</head>

<body>
<img src= 'images/doxie.jpg' style="width:100px;height:80px;">

<br>
<br>
<B>Another Page</B>
<br><br>
<a href='/homepage.php'> <b>Home page</b> </a>
<br><br>
<a href='/infopage.php'> <b>Info page</b> </a>
<br><br>
<a href='/anotherpage.php'> <b>Another page</b> </a>
<br><br>

<?php

// DB Connect params
$servername = "LOCALHOST if DATABASE is on same server as WebApplication or INTERNAL IP of DB Server if DATABASE is running on another server";
$username = "WEBAPP_USERNAME";
$password = "WEBAPP_PASSWORD";
$database = "DATABASE_NAME";

// Create page logging insert statement
$page_view_insert="insert into page_viewed (page_id, page_view_date) values (3,now())";

// Create connection
$db_conn = mysqli_connect($servername, $username, $password, $database)
or die('Error connecting to MySQL server.');

// Execute insert statement
mysqli_query($db_conn, $page_view_insert) or die('Error doing page view insert.');

// Close DB connection
mysqli_close($db_conn);

?>
</body>
</html>

