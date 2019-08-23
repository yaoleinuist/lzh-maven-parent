package com.lzhsite.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 * 通用的load和save操作
 * @author Administrator
 *
 */
public class GenericLoadSave {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf() 
				.setAppName("GenericLoadSave");
		JavaSparkContext sc = new JavaSparkContext(conf);
		SQLContext sqlContext = new SQLContext(sc);
	
		DataFrame usersDF = sqlContext.read().load(
				"hdfs://hadoop.senior02:9000/TestDir/users.parquet");
		usersDF.select("name", "favorite_color").write()
				.save("hdfs://hadoop.senior02:9000/TestDir/namesAndFavColors.parquet");   
	}
	
}
