package team.gtfm.analize;

import java.io.IOException;
import java.util.Set;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Progressable;

public class ChickenCounter {

	public static class ChickenMapper
		extends Mapper<Object, Text, Text, Text>{
		public static final int INDEX_SITE_WHL_ADDR = 2;
		public static final int INDEX_STATE = 5;
		
		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String[] values = value.toString().split(",");
			//System.out.println(values[INDEX_SITE_WHL_ADDR]);
			Address address;
			String state;
			try{
				address = new Address(values[INDEX_SITE_WHL_ADDR]);
				state = values[INDEX_STATE];
			}catch(Exception e){
				return;
			}
			
			Text outputKey = new Text();
			Text outputValue = new Text(state);
			
			StringBuilder builder = new StringBuilder();
			int length = address.length();
			for(int i=0 ; i<length ; i++){
				if(i > 0)
					builder.append(" ");
				builder.append(address.getAddress(i));
				outputKey.set(builder.toString());
				context.write(outputKey, outputValue);
			}
		}
	}
	
	public static class ChickenReducer
		extends Reducer<Text, Text, Text, Text>{
		public static final String STATE_OPEN = "운영중";
		public static final String STATE_CLOSE = "폐업 등";
		public static final String KEY_OPEN = "open";
		public static final String KEY_CLOSE = "close";
		
		@Override
		protected void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			int openCount=0;
			int closeCount=0;
			
			for(Text text : values){
				String value = text.toString();
				if(value.equals(STATE_OPEN))
					openCount++;
				else
					closeCount++;
			}
			
//			MapWritable map = new MapWritable();
//			map.put(
//					new Text(KEY_OPEN),
//					new IntWritable(openCount));
//			map.put(
//					new Text(KEY_CLOSE),
//					new IntWritable(closeCount));
			
			
			StringBuilder builder = new StringBuilder();
			builder.append(openCount).append("\t").append(closeCount);
			context.write(key, new Text(builder.toString()));
		}
		
	}
	

	public static void main(String[] args) throws Exception{
	      JobConf conf = new JobConf();
	      String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
	      if (otherArgs.length != 2) {
	        System.err.println("Usage: wordcount <in> <out>");
	        System.exit(2);
	      }
	          
	      Job job = new Job(conf, "word count");
	      job.setJarByClass(ChickenCounter.class);
	      job.setMapperClass(ChickenMapper.class);
	      //Uncomment this to 
	      //job.setCombinerClass(IntSumReducer.class);
	      job.setReducerClass(ChickenReducer.class);
	      job.setOutputKeyClass(Text.class);
	      job.setOutputValueClass(Text.class);
	      FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
	      FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
	      System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
