<?php

// Business logic here

$q = $_GET['q'];

$terms = str_replace(' ', '%', $q);
$terms = "%$terms%";
$terms = addslashes($terms);

$catf = 'nama_kategori';
$taskf = 'nama_task';
$userf = 'nama_user';

$type = $_GET['type'];
$all = $type == 'all';

$start = ($_GET['page'] - 1) * 10;

// Presentation logic here

if ($type == 'task'):

$tasks = Task::model()->findAllLimit("nama_task LIKE '$terms'", array(), $start, 10);

foreach ($tasks as $task) {
	require dirname(__FILE__) . "/../template/task.php"; 
}

elseif ($type == 'user'):
	$users = User::model()->findAllLimit("username LIKE '$terms' OR fullname LIKE '$terms'", array(), $start, 10);
	foreach ($users as $user) {
		require dirname(__FILE__) . "/../template/user.php";
	}

elseif ($type == 'category' || $all):
	$categories = Category::model()->findAllLimit("nama_kategori LIKE '$terms'", array(), $start, 10);
	
	foreach ($categories as $cat): ?>
				<li id="categoryLi<?php echo $cat->id_kategori ?>"><a href="dashboard.php?cat=<?php echo $cat->id_kategori ?>" data-category-id="<?php echo $cat->id_kategori ?>"><?php echo $cat->nama_kategori ?></a></li>

<?php endforeach;

endif; ?>