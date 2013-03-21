<?php

	include_once "SimpleRecord.php";
	
	class Category extends SimpleRecord
	{		
		public static function model($className=__CLASS__)
		{
			return parent::model($className);
		}
	
		public static function tableName()
		{
			return "kategori";
		}
		
		public function checkValidity()
		{
			$error = array();
			if (!preg_match("/^.{5,}$/", $this->data['username']))
			{
				$error["username"] = "Username harus minimal 5 karakter.";
			}
			return $error;
		}
		
		public function save()
		{
			// check same category name
			if ($this->id==null)
			{
				// new category
				DBConnection::openDBconnection();
				
				$insert = "INSERT INTO %s (id_kategori, nama_kategori, id_user) VALUES (0, '%s', '%d')";
				$insert = sprintf($insert, $this->tableName(), addslashes($this->nama_kategori), $this->id_user);

				$success = DBConnection::DBquery($insert);

				if ($success)
					$this->id_kategori = DBConnection::insertID();
				else
					return false;

				// $result = DBConnection::DBquery("INSERT into ".tableName()."");

				//DBConnection::closeDBconnection();

				return $result;
			}
			else
			{
				// existing category
			}
		}
	}
?>