package guava;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

// https://code.google.com/p/guava-libraries/wiki/IOExplained
public class IOExplained {

    public static void main(String[] args) throws IOException {
        IOExplained explained = new IOExplained();

        File from = explained.create("D:\\feri\\file.txt", "one", "two", "three");
        File to = new File("D:\\feri\\folder", "copied.txt");
        explained.copy(from, to);
    }

    public void copy(File from, File to) throws IOException {
        Files.createParentDirs(to);

        ByteSource source = Files.asByteSource(from);
        ByteSink target = Files.asByteSink(to);
        source.copyTo(target);
    }

    public File create(String path, String... strings) throws IOException {
        File file = new File(path);
        Files.createParentDirs(file);
        Files.asCharSink(file, Charsets.UTF_8).writeLines(Arrays.asList(strings));
        return file;
    }

}
