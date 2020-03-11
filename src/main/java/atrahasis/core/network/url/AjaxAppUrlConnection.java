package atrahasis.core.network.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.IOUtils;

import sun.net.www.protocol.http.HttpURLConnection;
import sun.net.www.protocol.http.Handler;

@SuppressWarnings("restriction")
public class AjaxAppUrlConnection extends HttpURLConnection  {

	private PipedInputStream pipedIn;
    private ReentrantLock lock;
    
    protected AjaxAppUrlConnection(URL url, Proxy proxy, Handler handler) throws IOException {
        super(url, proxy, handler);
        this.pipedIn = null;
        this.lock = new ReentrantLock(true);
    }
    
    @Override
    public InputStream getInputStream() throws IOException {

        lock.lock();
        try {

            // Do we have to set up our own input stream?
            if (pipedIn == null) {

                PipedOutputStream pipedOut = new PipedOutputStream();
                pipedIn = new PipedInputStream(pipedOut);

                InputStream in = super.getInputStream();
                /*
                 * Careful here! for some reason, the getInputStream method seems
                 * to be calling itself (no idea why). Therefore, if we haven't set
                 * pipedIn before calling super.getInputStream(), we will run into
                 * a loop or into EOFExceptions!
                 */

                // TODO: timeout?
                new Thread(new Runnable() {
                    public void run() {
                        try {

                            // Pass the original data on to the browser.
                            byte[] data = IOUtils.toByteArray(in);
                            pipedOut.write(data);
                            pipedOut.flush();
                            pipedOut.close();

                            // Do something with the data? Decompress it if it was
                            // gzipped, for example.

                            // Signal that the browser has finished.

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } finally {
            lock.unlock();
        }
        return pipedIn;
    }

}
