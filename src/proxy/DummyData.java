package proxy;

import proxy.Data.Video;

import java.util.HashMap;
import java.util.Map;

import static lib.Printer.println;

public class DummyData {

    public static Map<String, Video> getRandomVideos() {
        Map<String, Video> res = new HashMap<>();
        res.put("catzzzzzzzzz", new Video("sadgahasgdas", "Catzzzz.avi"));
        res.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        res.put("dancesvideoo", new Video("asdfas3ffasd", "Dancing video.mpq"));
        res.put("dlsdk5jfslaf", new Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"));
        res.put("3sdfgsd1j333", new Video("3sdfgsd1j333", "Programing lesson#1.avi"));
        return res;
    }

    public static class DummyNetworkLatency {

        public static DummyNetworkLatency sharedInstance = new DummyNetworkLatency();
        private DummyNetworkLatency() {}

        public void connectToServerWithNetworkLatency(String server) {

            println("Connecting to " + server + "... ");

            experienceNetworkLatency();

            println("Connected!" + "\n");
        }

        public Video downloadVideoWithNetworkLatency(String videoId) {

            println("Downloading video ... ");

            experienceNetworkLatency();
            Video video = new Video(videoId, "Some video title");

            println("Done!" + "\n");
            return video;
        }

        public Map<String, Video> downloadPopularVideosWithNetworkLatency() {

            println("Downloading popular videos ... ");

            experienceNetworkLatency();
            Map<String, Video> popularVideos = getRandomVideos();

            println("Done!\n");
            return popularVideos;
        }

        private void experienceNetworkLatency() {
            int randomLatency = random(5, 10);
            sleep(randomLatency * 100L);
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        private int random(int min, int max) {
            return min + (int) (Math.random() * ((max - min) + 1));
        }
    }
}
