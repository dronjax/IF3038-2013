<?php

	include_once "SimpleRecord.php";
	
	class Tag extends SimpleRecord
	{		
		public static function model($className=__CLASS__)
		{
			return parent::model($className);
		}
	
		public static function tableName()
		{
			return "tag";
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
			// check same tag name
			if ($this->id==null)
			{
				// new tag
				DBConnection::openDBconnection();\
				
				$result = DBConnection::DBquery("INSERT into ".tableName()."");
				
				DBConnection::closeDBconnection();
								
				return $result;
			}
			else
			{
				// existing tag
			}
		}
	}
?>