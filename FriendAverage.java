import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FriendAverage{

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private Text word = new Text();
    private int age = 0;
    

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {


        String line = value.toString();
        String[] LineDatas = line.split(";");
        try{
             age = Integer.parseInt(LineDatas[2]);
			 IntWritable friends = new IntWritable(Integer.parseInt(LineDatas[4]));
			 if(age <= 5)
			 {
			 context.write(new Text("0-5"), friends);
			 }else if(age <=12)
			 {
			  context.write(new Text("6-12"), friends);
			 }else if(age <= 17){
			 	context.write(new Text("13-17"), friends);
			 }else if(age <= 25){
			 	context.write(new Text("18-25"), friends);
			 }else if(age <= 35){
			 	context.write(new Text("26-35"), friends);
			 }else if(age <= 45){
			 	context.write(new Text("36-45"), friends);
			 }else if(age <= 60){
			 	context.write(new Text("46-60"), friends);
			 }else{
			 	context.write(new Text("61+"), friends);
			 }
             
}catch(Exception ex){
}


    }
  }
   public static class IntAverageReducer 
	extends Reducer<Text, IntWritable, Text, DoubleWritable> {
	private DoubleWritable result = new DoubleWritable();
	
	public void reduce(Text key, Iterable<IntWritable> values, 
			   Context context
			   ) throws IOException, InterruptedException {
	    double sum = 0;
	    int total = 0;
	    for (IntWritable val : values) {
		total ++;
		sum += val.get();
	    }
	    double average = sum / total;
	    result.set(average);
	    context.write(key, result);
	}
    }
 public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "FriendAverage");
    job.setJarByClass(FriendAverage.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(IntAverageReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

