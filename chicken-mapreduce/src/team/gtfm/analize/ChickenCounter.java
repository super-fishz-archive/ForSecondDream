package team.gtfm.analize;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class ChickenCounter {

	public static class ChickenMapper
		extends Mapper<Object, Text, Text, Text>{
		public static final int INDEX_SITE_WHL_ADDR = 2;
		public static final int INDEX_STATE = 5;
		
		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String[] values = value.toString().split(" ");
			
			Address address = new Address(values[INDEX_SITE_WHL_ADDR]);
			String state = values[INDEX_STATE];
			
			Text outputKey = new Text();
			Text outputValue = new Text(state);
			
			outputKey.set(address.getFirstAddress());
			context.write(outputKey, outputValue);
			outputKey.set(address.getSecondAddress());					
			context.write(outputKey, outputValue);
			outputKey.set(address.getThirdAddress());
			context.write(outputKey, outputValue);
		}
		

		
	}
	
	public static class ChickenReducer
		extends Reducer<Text, Text, Text, IntWritable>{

	}
	
	private MapWritable map = new MapWritable();
}
