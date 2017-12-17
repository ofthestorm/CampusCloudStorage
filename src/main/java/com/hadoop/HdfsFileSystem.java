package com.hadoop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HdfsFileSystem {
    public static final Configuration configuration=new Configuration();

    static {
        configuration.set("fs.defaultFS","hdfs://111.230.231.91:9000");
        System.setProperty("HADOOP_USER_NAME","hadoop");
    }

    public static void download( String uri ,String remote, String local) throws IOException {
        Path path = new Path(remote);
        FileSystem fs = FileSystem.get(URI.create(uri), configuration);
        fs.copyToLocalFile(path, new Path(local));
        System.out.println("download: from" + remote + " to " + local);
        fs.close();
    }

    public static void createFile(File localPath, String hdfsPath) throws IOException {
        InputStream in = null;
        try {
            FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath), configuration);
            FSDataOutputStream out = fileSystem.create(new Path(hdfsPath));
            in = new BufferedInputStream(new FileInputStream(localPath));
            IOUtils.copyBytes(in, out, 4096, false);
            out.hsync();
            out.close();
            System.out.println("create file in hdfs:" + hdfsPath);
        } finally {
            IOUtils.closeStream(in);
        }
    }

}
