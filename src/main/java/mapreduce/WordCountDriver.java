package mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {

    private static final String INPUTPATH = "/var/tmp/mapredmentoring/input";
    private static final String OUTPUTPATH = "/var/tmp/mapredmentoring/output";

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = new Job(conf, "WordCountExample");
        job.setJarByClass(WordCountDriver.class);

        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(INPUTPATH));
        FileOutputFormat.setOutputPath(job, new Path(OUTPUTPATH));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}