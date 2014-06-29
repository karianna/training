package org.jclarity.training;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.nio.file.StandardWatchEventKinds.*;

public class FilesExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        FilesExample fx = new FilesExample();
        fx.run();
    }

    private boolean shutdown = false;

    private void watch() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();

            Path dir = FileSystems.getDefault().getPath("/home/ben");
            WatchKey key = dir.register(watcher, ENTRY_MODIFY);

            while (!shutdown) {
                key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_MODIFY) {
                        System.out.println("Home dir changed!");
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void asynchFuture() {
        try {
            Path file = Paths.get("/home/ben/foobar.txt");

            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            ByteBuffer buffer = ByteBuffer.allocate(100_000);
            Future<Integer> result = channel.read(buffer, 0);

            while (!result.isDone()) {
                // Do some work....
            }

            Integer bytesRead = result.get();
            System.out.println("Bytes read [" + bytesRead + "]");
        } catch (IOException | ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void channel() {
        FileInputStream fis = getSomeStream();
        boolean fileOK = true;

        try (FileChannel fchan = fis.getChannel()) {
            ByteBuffer buffy = ByteBuffer.allocateDirect(16 * 1024 * 1024);
            while (fchan.read(buffy) != -1 || buffy.position() > 0 || fileOK) {
                fileOK = computeChecksum(buffy);
                buffy.compact();
            }
        } catch (IOException e) {
            System.out.println("Exception in I/O");
        }

    }

    private boolean computeChecksum(ByteBuffer buffy) {
        // TODO Auto-generated method stub
        return false;
    }

    private FileInputStream getSomeStream() {
        // TODO Auto-generated method stub
        return null;
    }

    private void buffer() {
        ByteBuffer b = ByteBuffer.allocate(4096);
        ByteBuffer b2 = ByteBuffer.allocateDirect(65536);

        byte[] data = { 1, 2, 3 };
        ByteBuffer b3 = ByteBuffer.wrap(data);

        b.put(data);
        b.put((byte) 42);
        b.put(0, (byte) 9);

        b.order(ByteOrder.BIG_ENDIAN);
        b.putChar('x');
        b.putInt(0xcafebabe);

        int capacity = b.capacity();
        int position = b.position();

        // A buffer's limit specifies how many bytes of the buffer can be used.
        // When writing into a buffer, this should be the capacity. When reading
        // data
        // from a buffer, it should be the number of bytes that were previously
        // written.
        int limit = b.limit(); // How many should be used?
        int remaining = b.remaining(); // How many left? Return limit-position.
        boolean more = b.hasRemaining(); // Test if there is still room in the
                                         // buffer
    }

    private void run() {
        File homedir = new File(System.getProperty("user.home"));
        File f = new File(homedir, ".configfile");

        if (f.exists() && f.isFile() && f.canRead()) {
            File configdir = new File(homedir, ".configdir");
            configdir.mkdir();
            f.renameTo(new File(configdir, ".config"));
        }

        String[] allfiles = homedir.list();

        String filename = System.getProperty("user.home") + File.separator + ".cshrc";
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (IOException e) {
            // Handle FileNotFoundException, etc. here
        }

        Path logFile = Paths.get("/tmp/app.log");
        try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
            writer.write("Hello World!");
            // ...
        } catch (IOException e) {
            // ...
        }
    }
}
